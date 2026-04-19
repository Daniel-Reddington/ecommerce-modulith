package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetAllProductUseCaseTest {

    @Test
     void should_get_products_with_pagination(){
        ProductRepository repository = mock(ProductRepository.class);

        GetAllProductsUseCase useCase = new GetAllProductsUseCase(repository);

        List<Product> products = List.of(
                Product.create("Laptop", 1000D),
                Product.create("Samsung Galaxy", 2000D)
        );

        when(repository.findAll(0,10)).thenReturn(products);

        List<Product> results = useCase.execute(0,10);

        assertEquals(2, results.size());
        assertEquals("Laptop", results.getFirst().name());

        verify(repository).findAll(0,10);
    }

    @Test
     void should_return_empty_list_when_no_products(){
        ProductRepository repository = mock(ProductRepository.class);
        GetAllProductsUseCase useCase = new GetAllProductsUseCase(repository);

        when(repository.findAll(0,10)).thenReturn(List.of());

        List<Product> results = useCase.execute(0,10);

        assertTrue(results.isEmpty());
        verify(repository).findAll(0,10);
    }
}
