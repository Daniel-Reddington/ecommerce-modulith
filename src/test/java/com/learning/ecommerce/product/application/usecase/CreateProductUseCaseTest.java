package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.exception.InvalidProductNameException;
import com.learning.ecommerce.product.domain.exception.InvalidProductPriceException;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateProductUseCaseTest {

    @Test
    void should_create_and_save_product_successfully() {

        ProductRepository repository = mock(ProductRepository.class);

        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateProductUseCase useCase = new CreateProductUseCase(repository);

        Product result = useCase.execute("Laptop", 1000.0);

        assertNotNull(result.id());
        assertEquals("Laptop", result.name());
        assertEquals(1000.0, result.price());

        verify(repository).save(any(Product.class));
    }

    @Test
    void should_not_save_when_name_is_empty() {

        ProductRepository repository = mock(ProductRepository.class);
        CreateProductUseCase useCase = new CreateProductUseCase(repository);

        assertThrows(InvalidProductNameException.class, () ->
                useCase.execute("", 1000.0)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void should_not_save_when_price_is_invalid() {

        ProductRepository repository = mock(ProductRepository.class);
        CreateProductUseCase useCase = new CreateProductUseCase(repository);

        assertThrows(InvalidProductPriceException.class, () ->
                useCase.execute("Laptop", -10.0)
        );

        verify(repository, never()).save(any());
    }
}