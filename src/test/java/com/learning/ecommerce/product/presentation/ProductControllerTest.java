package com.learning.ecommerce.product.presentation;

import com.learning.ecommerce.product.application.usecase.CreateProductUseCase;
import com.learning.ecommerce.product.application.usecase.GetAllProductsUseCase;
import com.learning.ecommerce.product.application.usecase.GetProductUseCase;
import com.learning.ecommerce.product.domain.exception.ProductNotFoundException;
import com.learning.ecommerce.product.domain.model.Product;
import com.learning.ecommerce.product.presentation.dto.ProductResponse;
import com.learning.ecommerce.product.presentation.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateProductUseCase createProductUseCase;
    @MockitoBean
    private GetProductUseCase getProductUseCase;
    @MockitoBean
    private GetAllProductsUseCase getAllProductsUseCase;
    @MockitoBean
    private ProductMapper productMapper;

    @Test
    void should_create_product() throws Exception {
        Product product = Product.create("Laptop", 1000.0);

        when(createProductUseCase.execute(anyString(), anyDouble())).thenReturn(product);

        when(productMapper.toResponse(any(Product.class))).thenReturn(
                new ProductResponse(
                        product.id().value(),
                        product.name(),
                        product.price()
                )
        );
        String requestBody = """
                {
                    "name": "Laptop",
                    "price": 1000
                }
                """;

        mockMvc.perform(
                post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Laptop"))
                .andExpect(jsonPath("$.data.price").value(1000));
    }

    @Test
    void should_get_all_products() throws Exception {

        List<Product> products = List.of(
                Product.create("Laptop", 1000.0),
                Product.create("Phone", 500.0)
        );

        when(getAllProductsUseCase.execute()).thenReturn(products);
        when(productMapper.toResponse(any(Product.class))).thenAnswer(invocation -> {
            Product product = invocation.getArgument(0);
            return new ProductResponse(
                    product.id().value(),
                    product.name(),
                    product.price()
            );
        });

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("Laptop"))
                .andExpect(jsonPath("$.data[0].price").value(1000.0))
                .andExpect(jsonPath("$.data[1].name").value("Phone"))
                .andExpect(jsonPath("$.data[1].price").value(500));
    }

    @Test
    void should_get_product_by_id() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = Product.create("Laptop", 1000.0);

        when(getProductUseCase.execute(id)).thenReturn(product);

        when(productMapper.toResponse(any(Product.class))).thenReturn(
                new ProductResponse(
                        product.id().value(),
                        product.name(),
                        product.price()
                )
        );

        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Laptop"))
                .andExpect(jsonPath("$.data.price").value(1000.0));
    }

    @Test
    void should_return_404_when_product_not_found() throws Exception {
        UUID id = UUID.randomUUID();

        when(getProductUseCase.execute(id)).thenThrow(new ProductNotFoundException(id));

        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("PRODUCT_NOT_FOUND"))
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void should_return_400_when_request_invalid() throws Exception {
        String requestBody = """
                {
                    "name": "",
                    "price": -100
                }
                """;

        mockMvc.perform(
                post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("VALIDATION_ERROR"))
                .andExpect(jsonPath("$.message").exists());
    }

}
