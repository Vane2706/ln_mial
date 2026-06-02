package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.AlmacenCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.AlmacenRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.AlmacenEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlmacenRepositoryImplTest {

    @Mock
    private AlmacenCrudRepository crudRepository;

    @InjectMocks
    private AlmacenRepositoryImpl repository;

    @Test
    void saveStock_shouldSave() {
        AlmacenEntity stock = new AlmacenEntity();

        when(crudRepository.save(stock)).thenReturn(stock);

        AlmacenEntity result = repository.saveStock(stock);

        assertNotNull(result);
        verify(crudRepository).save(stock);
    }

    @Test
    void getStockByProductEntity_shouldReturnList() {
        ProductosEntity product = new ProductosEntity();
        List<AlmacenEntity> list = List.of(new AlmacenEntity());

        when(crudRepository.getStockByProductosEntity(product)).thenReturn(list);

        List<AlmacenEntity> result = repository.getStockByProductEntity(product);

        assertEquals(1, result.size());
    }

    @Test
    void deleteStockById_shouldReturnTrue() {
        doNothing().when(crudRepository).deleteById(1);

        boolean result = repository.deleteStockById(1);

        assertTrue(result);
        verify(crudRepository).deleteById(1);
    }
}