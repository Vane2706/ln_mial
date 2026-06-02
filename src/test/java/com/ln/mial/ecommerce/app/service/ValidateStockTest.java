package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.infraestructure.entity.AlmacenEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateStockTest {

    @Mock
    private AlmacenService service;

    @InjectMocks
    private ValidateStock validateStock;

    @Test
    void calculateBalance_shouldAddEntradas_whenNoPreviousStock() {
        ProductosEntity product = new ProductosEntity();
        AlmacenEntity stock = new AlmacenEntity();
        stock.setEntradas(10);
        stock.setProductosEntity(product);

        when(service.getStockByProductEntity(product))
                .thenReturn(List.of());

        AlmacenEntity result = validateStock.calculateBalance(stock);

        assertEquals(10, result.getBalance());
    }

    @Test
    void calculateBalance_shouldAddToPreviousBalance() {
        ProductosEntity product = new ProductosEntity();

        AlmacenEntity previous = new AlmacenEntity();
        previous.setBalance(10);

        AlmacenEntity stock = new AlmacenEntity();
        stock.setEntradas(5);
        stock.setProductosEntity(product);

        when(service.getStockByProductEntity(product))
                .thenReturn(List.of(previous));

        AlmacenEntity result = validateStock.calculateBalance(stock);

        assertEquals(15, result.getBalance());
    }

    @Test
    void calculateBalance_shouldSubtractWhenNoEntradas() {
        ProductosEntity product = new ProductosEntity();

        AlmacenEntity previous = new AlmacenEntity();
        previous.setBalance(20);

        AlmacenEntity stock = new AlmacenEntity();
        stock.setEntradas(0);
        stock.setSalidas(5);
        stock.setProductosEntity(product);

        when(service.getStockByProductEntity(product))
                .thenReturn(List.of(previous));

        AlmacenEntity result = validateStock.calculateBalance(stock);

        assertEquals(15, result.getBalance());
    }
}