package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.CategoriasService;
import com.ln.mial.ecommerce.infraestructure.entity.CategoriasEntity;
import com.ln.mial.ecommerce.infraestructure.entity.StatusCategoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaControllerTest {

    @Mock
    private CategoriasService service;

    @InjectMocks
    private CategoriaController controller;

    @Test
    void addCategory_shouldSaveAndRedirect() {
        String name = "Ropa";
        String status = "ACTIVO";

        CategoriasEntity category = new CategoriasEntity();
        category.setName(name);
        category.setStatusCategoria(StatusCategoria.ACTIVO);

        when(service.saveCategory(any())).thenReturn(category);

        String view = controller.addCategory(name, status);

        assertEquals("redirect:/admin/create", view);
        verify(service).saveCategory(any(CategoriasEntity.class));
    }
}