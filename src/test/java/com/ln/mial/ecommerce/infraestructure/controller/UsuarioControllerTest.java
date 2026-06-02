package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.CategoriasService;
import com.ln.mial.ecommerce.app.service.ProductosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private ProductosService productosService;

    @Mock
    private CategoriasService categoriasService;

    private UsuarioController controller;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        controller =
                new UsuarioController(
                        productosService,
                        categoriasService
                );
    }

    @Test
    void showIndex_ReturnIndex() {

        Model model = new ConcurrentModel();

        when(productosService.getProducts())
                .thenReturn(java.util.List.of());

        when(categoriasService.getCategories())
                .thenReturn(java.util.List.of());

        String result =
                controller.showIndex(model);

        assertEquals("index", result);
    }

    @Test
    void showPrivacy_ReturnView() {

        String result =
                controller.showPrivacy();

        assertEquals(
                "politica/privacidad",
                result
        );
    }
}