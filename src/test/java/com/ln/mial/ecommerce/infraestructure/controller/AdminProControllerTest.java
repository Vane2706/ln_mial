package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.AlmacenService;
import com.ln.mial.ecommerce.app.service.CategoriasService;
import com.ln.mial.ecommerce.app.service.ProductosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminProControllerTest {

    @Mock
    private ProductosService productosService;

    @Mock
    private CategoriasService categoriasService;

    @Mock
    private AlmacenService almacenService;

    @InjectMocks
    private AdminProController controller;

    @Mock
    private Model model;

    @Test
    void showProducts() {

        when(productosService.getProducts())
                .thenReturn(List.of());

        String view =
                controller.showProducts(model);

        assertEquals("admin/productos", view);
    }

    @Test
    void showProductsByCategory() {

        when(productosService.getProductsByCategory(1))
                .thenReturn(List.of());

        String view =
                controller.showProductsByCategory(1, model);

        assertEquals("admin/productos", view);
    }
}