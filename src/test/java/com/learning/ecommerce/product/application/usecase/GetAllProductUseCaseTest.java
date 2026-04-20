package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.filter.ProductFilter;
import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;

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

        ProductFilter filter = new ProductFilter(0, 10, null, null, null);

        when(repository.findAll(filter)).thenReturn(products);

        List<Product> results = useCase.execute(filter);

        assertEquals(2, results.size());
        assertEquals("Laptop", results.getFirst().name());

        verify(repository).findAll(filter);
    }

    @Test
     void should_return_empty_list_when_no_products(){

        ProductFilter filter = new ProductFilter(0, 10, null, null, null);

        when(repository.findAll(filter)).thenReturn(List.of());

        List<Product> results = useCase.execute(filter);

        assertTrue(results.isEmpty());
        verify(repository).findAll(filter);
    }

    @Test
    void should_filter_product_by_name(){

        List<Product> products = List.of(
                Product.create("Laptop", 1000.0)
        );

        ProductFilter filter = new ProductFilter(0,10, "Lap", 400.0, 1100.0);
        when(repository.findAll(filter)).thenReturn(products);

        List<Product> results = useCase.execute(filter);

        assertEquals(1, results.size());
        assertEquals("Laptop", results.getFirst().name());
        verify(repository).findAll(filter);
    }
}
