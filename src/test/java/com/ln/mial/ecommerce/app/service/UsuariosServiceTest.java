package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.UsuariosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.UsuariosEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuariosServiceTest {

    @Mock
    private UsuariosRepository usuariosRepository;

    private UsuariosService usuariosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuariosService = new UsuariosService(usuariosRepository);
    }

    @Test
    void createUser_ReturnsSavedUser() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(1);

        when(usuariosRepository.createUser(user))
                .thenReturn(user);

        UsuariosEntity result =
                usuariosService.createUser(user);

        assertEquals(1, result.getId());
    }

    @Test
    void findByEmail_ReturnsUser() {

        UsuariosEntity user = new UsuariosEntity();
        user.setEmail("test@gmail.com");

        when(usuariosRepository.findByEmail("test@gmail.com"))
                .thenReturn(user);

        UsuariosEntity result =
                usuariosService.findByEmail("test@gmail.com");

        assertEquals("test@gmail.com",
                result.getEmail());
    }

    @Test
    void findById_ReturnsUser() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(1);

        when(usuariosRepository.findById(1))
                .thenReturn(user);

        UsuariosEntity result =
                usuariosService.findById(1);

        assertEquals(1, result.getId());
    }
}
