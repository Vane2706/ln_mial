package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.AlmacenService;
import com.ln.mial.ecommerce.app.service.ValidateStock;
import com.ln.mial.ecommerce.infraestructure.entity.AlmacenEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlmacenControllerTest {

    @Mock
    private AlmacenService service;

    @Mock
    private ValidateStock validateStock;

    @InjectMocks
    private AlmacenController controller;

    @Test
    void show_shouldReturnViewWithModel() {
        ProductosEntity product = new ProductosEntity();
        product.setId(1);

        when(service.getStockByProductEntity(any()))
                .thenReturn(List.of(new AlmacenEntity()));

        Model model = new ExtendedModelMap();

        String view = controller.show(1, model);

        assertEquals("admin/stock", view);
        assertTrue(model.containsAttribute("stocks"));
    }

    @Test
    void save_shouldCreateNewStock() {
        AlmacenEntity stock = new AlmacenEntity();
        stock.setEntradas(10);

        when(service.getStockByProduct(any())).thenReturn(null);
        when(validateStock.calculateBalance(any())).thenReturn(stock);
        when(service.saveStock(any())).thenReturn(stock);

        String view = controller.save(stock, 1);

        assertEquals("redirect:/admin/products", view);
    }

    @Test
    void save_shouldUpdateExistingStock() {
        AlmacenEntity existing = new AlmacenEntity();
        existing.setEntradas(5);
        existing.setBalance(5);

        AlmacenEntity stock = new AlmacenEntity();
        stock.setEntradas(3);

        when(service.getStockByProduct(any())).thenReturn(existing);
        when(service.updateStock(existing, 3)).thenReturn(existing);

        String view = controller.save(stock, 1);

        assertEquals("redirect:/admin/products", view);
        verify(service).updateStock(existing, 3);
    }
}