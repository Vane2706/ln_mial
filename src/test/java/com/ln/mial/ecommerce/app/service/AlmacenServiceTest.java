package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.AlmacenRepository;
import com.ln.mial.ecommerce.infraestructure.entity.AlmacenEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlmacenServiceTest {

    @Mock
    private AlmacenRepository repository;

    @InjectMocks
    private AlmacenService service;

    @Test
    void saveStock_shouldCallRepository() {
        AlmacenEntity stock = new AlmacenEntity();

        when(repository.saveStock(stock)).thenReturn(stock);

        AlmacenEntity result = service.saveStock(stock);

        assertNotNull(result);
    }

    @Test
    void getStockByProductEntity_shouldReturnList() {
        ProductosEntity product = new ProductosEntity();

        when(repository.getStockByProductEntity(product))
                .thenReturn(List.of(new AlmacenEntity()));

        List<AlmacenEntity> result = service.getStockByProductEntity(product);

        assertFalse(result.isEmpty());
    }

    @Test
    void getStockByProduct_shouldReturnFirstStock() {
        ProductosEntity product = new ProductosEntity();
        AlmacenEntity stock = new AlmacenEntity();

        when(repository.getStockByProductEntity(product))
                .thenReturn(List.of(stock));

        AlmacenEntity result = service.getStockByProduct(product);

        assertNotNull(result);
    }

    @Test
    void updateStock_shouldIncreaseValues() {
        AlmacenEntity existing = new AlmacenEntity();
        existing.setEntradas(10);
        existing.setBalance(10);

        when(repository.saveStock(any())).thenReturn(existing);

        AlmacenEntity result = service.updateStock(existing, 5);

        assertEquals(15, result.getEntradas());
        assertEquals(15, result.getBalance());
    }

    @Test
    void getAvailableStock_shouldReturnBalance() {
        ProductosEntity product = new ProductosEntity();
        AlmacenEntity stock = new AlmacenEntity();
        stock.setBalance(20);

        when(repository.getStockByProductEntity(product))
                .thenReturn(List.of(stock));

        Integer result = service.getAvailableStock(product);

        assertEquals(20, result);
    }
}