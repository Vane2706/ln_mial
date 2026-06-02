package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.CategoriasService;
import com.ln.mial.ecommerce.app.service.ProductosService;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
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
class AdminFormControllerTest {

    @Mock
    private ProductosService productosService;

    @Mock
    private CategoriasService categoriasService;

    private AdminFormController controller;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        controller =
                new AdminFormController(
                        productosService,
                        categoriasService
                );
    }

    @Test
    void showCategory_ReturnView() {

        Model model = new ConcurrentModel();

        when(categoriasService.getCategories())
                .thenReturn(java.util.List.of());

        String result =
                controller.showCategory(model);

        assertEquals(
                "admin/formulario",
                result
        );
    }

    @Test
    void deleteProduct_ReturnRedirect() {

        when(productosService.deleteProductById(1))
                .thenReturn(true);

        String result =
                controller.deleteProduct(1);

        assertEquals(
                "redirect:/admin/products",
                result
        );
    }

    @Test
    void editProduct_ReturnEditView() {

        Model model = new ConcurrentModel();

        ProductosEntity product =
                new ProductosEntity();

        product.setId(1);

        when(productosService.getProductById(1))
                .thenReturn(product);

        when(categoriasService.getCategories())
                .thenReturn(java.util.List.of());

        String result =
                controller.editProduct(1, model);

        assertEquals(
                "admin/editar",
                result
        );
    }
}
