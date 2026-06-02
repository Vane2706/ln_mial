package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.CategoriasRepository;
import com.ln.mial.ecommerce.infraestructure.entity.CategoriasEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriasServiceTest {

    @Mock
    private CategoriasRepository repository;

    @InjectMocks
    private CategoriasService service;

    @Test
    void getCategories_shouldReturnData() {
        when(repository.getCategories()).thenReturn(List.of(new CategoriasEntity()));

        Iterable<CategoriasEntity> result = service.getCategories();

        assertNotNull(result);
    }

    @Test
    void getCategoryById_shouldReturnEntity() {
        CategoriasEntity category = new CategoriasEntity();
        category.setId(1);

        when(repository.getCategoryById(1)).thenReturn(category);

        CategoriasEntity result = service.getCategoryById(1);

        assertEquals(1, result.getId());
    }

    @Test
    void saveCategory_shouldCallRepository() {
        CategoriasEntity category = new CategoriasEntity();

        when(repository.saveCategory(category)).thenReturn(category);

        CategoriasEntity result = service.saveCategory(category);

        assertNotNull(result);
        verify(repository).saveCategory(category);
    }
}