package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.CategoriasCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.CategoriasRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.CategoriasEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriasRepositoryImplTest {

    @Mock
    private CategoriasCrudRepository crudRepository;

    @InjectMocks
    private CategoriasRepositoryImpl repository;

    @Test
    void getCategories_shouldReturnList() {
        List<CategoriasEntity> list = List.of(new CategoriasEntity(), new CategoriasEntity());
        when(crudRepository.findAll()).thenReturn(list);

        Iterable<CategoriasEntity> result = repository.getCategories();

        assertNotNull(result);
        assertEquals(2, ((List<?>) result).size());
    }

    @Test
    void getCategoryById_shouldReturnCategory() {
        CategoriasEntity category = new CategoriasEntity();
        category.setId(1);

        when(crudRepository.findById(1)).thenReturn(Optional.of(category));

        CategoriasEntity result = repository.getCategoryById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void saveCategory_shouldSave() {
        CategoriasEntity category = new CategoriasEntity();
        when(crudRepository.save(category)).thenReturn(category);

        CategoriasEntity result = repository.saveCategory(category);

        assertNotNull(result);
        verify(crudRepository).save(category);
    }
}