package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.infraestructure.entity.TypeUser;
import com.ln.mial.ecommerce.infraestructure.entity.UsuariosEntity;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private UsuariosService usuariosService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    private RegisterService registerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        registerService = new RegisterService(
                usuariosService,
                passwordEncoder,
                emailService
        );
    }

    @Test
    void register_NewUser_Success() throws MessagingException {

        UsuariosEntity user = new UsuariosEntity(
                1,
                "user",
                "Juan",
                "juan@gmail.com",
                "987654321",
                "123456",
                LocalDateTime.now(),
                TypeUser.USER
        );

        when(usuariosService.findByEmail(user.getEmail()))
                .thenReturn(null);

        when(passwordEncoder.encode(anyString()))
                .thenReturn("encodedPassword");

        when(usuariosService.createUser(any()))
                .thenReturn(user);

        assertDoesNotThrow(() ->
                registerService.register(user));

        verify(usuariosService, times(1))
                .createUser(any());

        verify(emailService, times(1))
                .sendEmail(anyString(), anyString(), anyString());
    }

    @Test
    void register_EmailAlreadyExists_ThrowsException() {

        UsuariosEntity user = new UsuariosEntity();

        user.setEmail("juan@gmail.com");

        when(usuariosService.findByEmail(user.getEmail()))
                .thenReturn(user);

        assertThrows(
                IllegalArgumentException.class,
                () -> registerService.register(user)
        );
    }
}
