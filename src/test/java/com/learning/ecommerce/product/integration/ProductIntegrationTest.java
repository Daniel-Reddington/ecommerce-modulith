package com.learning.ecommerce.product.integration;

import com.learning.ecommerce.EcommerceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = EcommerceApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_create_product_in_db() throws Exception {
        String request = """
                {
                    "name" : "Laptop",
                    "price" : 1000                }
                """;

        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("Laptop"))
                .andExpect(jsonPath("$.data.price").value(1000));

    }

    @Test
    void should_return_product_from_db() throws Exception {
        String request = """
                {
                    "name" : "Laptop",
                    "price" : 1000
                }
        """;

        mockMvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Laptop"))
                .andExpect(jsonPath("$.data[0].price").value(1000))
                .andExpect(jsonPath("$.data.length()").value(1));

    }
}
