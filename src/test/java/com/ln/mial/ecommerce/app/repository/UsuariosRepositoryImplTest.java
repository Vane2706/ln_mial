package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.UsuariosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.UsuariosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.UsuariosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuariosRepositoryImplTest {

    @Mock
    private UsuariosCrudRepository crudRepository;

    @InjectMocks
    private UsuariosRepositoryImpl repository;

    @Test
    void getUsers() {

        List<UsuariosEntity> users = List.of(new UsuariosEntity());

        when(crudRepository.findAll()).thenReturn(users);

        Iterable<UsuariosEntity> resultado = repository.getUsers();

        assertNotNull(resultado);
        assertEquals(1, ((List<?>) resultado).size());
    }

    @Test
    void createUser() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(1);

        when(crudRepository.save(user)).thenReturn(user);

        UsuariosEntity resultado = repository.createUser(user);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void findByEmail() {

        UsuariosEntity user = new UsuariosEntity();
        user.setEmail("test@mail.com");

        when(crudRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(user));

        UsuariosEntity resultado =
                repository.findByEmail("test@mail.com");

        assertNotNull(resultado);
        assertEquals("test@mail.com", resultado.getEmail());
    }

    @Test
    void findByEmailNull() {

        when(crudRepository.findByEmail("no@mail.com"))
                .thenReturn(Optional.empty());

        UsuariosEntity resultado =
                repository.findByEmail("no@mail.com");

        assertNull(resultado);
    }

    @Test
    void findById() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(10);

        when(crudRepository.findById(10))
                .thenReturn(Optional.of(user));

        UsuariosEntity resultado = repository.findById(10);

        assertNotNull(resultado);
        assertEquals(10, resultado.getId());
    }
}
