package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.application.query.ProductQuery;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetAllProductUseCaseTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final GetAllProductsUseCase useCase = new GetAllProductsUseCase(repository);

    @Test
     void should_get_products_with_pagination(){

        List<Product> products = List.of(
                Product.create("Laptop", 1000D),
                Product.create("Samsung Galaxy", 2000D)
        );

        Page<Product> pageResult = new PageImpl<>(products);

        ProductFilter filter = new ProductFilter(0, 10, null, null, null);

        ProductQuery query = new ProductQuery(filter);

        when(repository.findAll(filter)).thenReturn(pageResult);

        Page<Product> results = useCase.execute(query);

        assertEquals(2, results.getTotalElements());
        assertEquals("Laptop", results.getContent().getFirst().name());

        verify(repository).findAll(filter);
    }

    @Test
     void should_return_empty_list_when_no_products(){

        ProductFilter filter = new ProductFilter(0, 10, null, null, null);

        ProductQuery query = new ProductQuery(filter);

        when(repository.findAll(filter)).thenReturn(Page.empty());

        Page<Product> results = useCase.execute(query);

        assertTrue(results.isEmpty());
        verify(repository).findAll(filter);
    }

    @Test
    void should_filter_product_by_name(){

        List<Product> products = List.of(
                Product.create("Laptop", 1000.0)
        );

        Page<Product> pageResult = new PageImpl<>(products);

        ProductFilter filter = new ProductFilter(0,10, "Lap", 400.0, 1100.0);
        when(repository.findAll(filter)).thenReturn(pageResult);

        ProductQuery query = new ProductQuery(filter);

        Page<Product> results = useCase.execute(query);

        assertEquals(1, results.getTotalElements());
        assertEquals("Laptop", results.getContent().getFirst().name());
        verify(repository).findAll(filter);
    }
}
