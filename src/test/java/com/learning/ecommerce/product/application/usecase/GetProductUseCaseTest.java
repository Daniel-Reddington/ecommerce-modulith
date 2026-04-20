package com.learning.ecommerce.product.application.usecase;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.domain.exception.ProductNotFoundException;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GetProductUseCaseTest {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final GetProductUseCase useCase = new GetProductUseCase(repository);

    @Test
     void should_return_product_when_found(){

        UUID id = UUID.randomUUID();

        Product product = Product.create("Laptop", 1000D);

        when(repository.findById(id)).thenReturn(Optional.of(product));

        Product result =  useCase.execute(id);

        assertNotNull(result);

        assertEquals(product.name(), result.name());

        verify(repository).findById(id);

    }


    @Test
     void should_throw_exception_when_product_not_found(){

        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> useCase.execute(id));

        verify(repository).findById(id);

    }
}
