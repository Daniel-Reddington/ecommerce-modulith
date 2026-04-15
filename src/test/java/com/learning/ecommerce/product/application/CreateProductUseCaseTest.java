package com.learning.ecommerce.product.application;

import com.learning.ecommerce.product.application.port.ProductRepository;
import com.learning.ecommerce.product.application.usecase.CreateProductUseCase;
import com.learning.ecommerce.product.domain.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateProductUseCaseTest {

    @Test
    public void should_create_product_successfully(){
        ProductRepository repository = mock(ProductRepository.class);

        when(repository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CreateProductUseCase useCase = new CreateProductUseCase(repository);
        Product result = useCase.execute("Laptop", 1000);

        assertNotNull(result.id());
        assertEquals("Laptop", result.name());

        verify(repository).save(any());
    }
}
