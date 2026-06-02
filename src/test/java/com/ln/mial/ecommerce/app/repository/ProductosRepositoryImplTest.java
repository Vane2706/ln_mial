package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.ProductosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.ProductosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductosRepositoryImplTest {

    @Mock
    private ProductosCrudRepository crudRepository;

    private ProductosRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new ProductosRepositoryImpl(crudRepository);
    }

    @Test
    void getProducts_ReturnList() {

        when(crudRepository.findAll())
                .thenReturn(List.of(new ProductosEntity()));

        List<ProductosEntity> result =
                repository.getProducts();

        assertEquals(1, result.size());
    }

    @Test
    void getProductById_ReturnProduct() {

        ProductosEntity product = new ProductosEntity();
        product.setId(1);

        when(crudRepository.findById(1))
                .thenReturn(Optional.of(product));

        ProductosEntity result =
                repository.getProductById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void saveProduct_ReturnSaved() {

        ProductosEntity product = new ProductosEntity();

        when(crudRepository.save(product))
                .thenReturn(product);

        ProductosEntity result =
                repository.saveProduct(product);

        assertNotNull(result);
    }
}