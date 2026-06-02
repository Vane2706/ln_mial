package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.infraestructure.entity.TypeUser;
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
class LoginServiceTest {

    @Mock
    private UsuariosService usuariosService;

    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginService = new LoginService(usuariosService);
    }

    @Test
    void getUserId_WhenUserExists_ReturnsId() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(1);

        when(usuariosService.findByEmail("test@gmail.com"))
                .thenReturn(user);

        Integer result = loginService.getUserId("test@gmail.com");

        assertEquals(1, result);
    }

    @Test
    void getUserId_WhenUserNotExists_ReturnsZero() {

        when(usuariosService.findByEmail("test@gmail.com"))
                .thenThrow(new RuntimeException());

        Integer result = loginService.getUserId("test@gmail.com");

        assertEquals(0, result);
    }

    @Test
    void getUserType_ReturnsUserType() {

        UsuariosEntity user = new UsuariosEntity();
        user.setTypeUser(TypeUser.USER);

        when(usuariosService.findByEmail("test@gmail.com"))
                .thenReturn(user);

        TypeUser result = loginService.getUserType("test@gmail.com");

        assertEquals(TypeUser.USER, result);
    }

    @Test
    void getUser_ReturnsUser() {

        UsuariosEntity user = new UsuariosEntity();
        user.setId(1);

        when(usuariosService.findById(1))
                .thenReturn(user);

        UsuariosEntity result = loginService.getUser(1);

        assertEquals(1, result.getId());
    }
}
