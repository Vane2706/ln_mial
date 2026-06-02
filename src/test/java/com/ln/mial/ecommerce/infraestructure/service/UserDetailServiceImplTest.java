package com.ln.mial.ecommerce.infraestructure.service;

import com.ln.mial.ecommerce.app.service.LoginService;
import com.ln.mial.ecommerce.infraestructure.entity.TypeUser;
import com.ln.mial.ecommerce.infraestructure.entity.UsuariosEntity;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserDetailServiceImplTest {

    @Mock
    private LoginService loginService;

    @Mock
    private HttpSession httpSession;

    private UserDetailServiceImpl service;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        service = new UserDetailServiceImpl(loginService);

        service.httpSession = httpSession;
    }

    @Test
    void loadUserByUsername_ReturnsUserDetails() {

        UsuariosEntity user = new UsuariosEntity();

        user.setId(1);
        user.setUsername("juan");
        user.setPassword("123");
        user.setFirstName("Juan");
        user.setCellphone("987654321");
        user.setTypeUser(TypeUser.USER);

        when(loginService.getUserId("juan"))
                .thenReturn(1);

        when(loginService.getuser("juan"))
                .thenReturn(user);

        UserDetails result =
                service.loadUserByUsername("juan");

        assertEquals("juan", result.getUsername());
    }

    @Test
    void loadUserByUsername_NotFound() {

        when(loginService.getUserId("juan"))
                .thenReturn(0);

        assertThrows(
                UsernameNotFoundException.class,
                () -> service.loadUserByUsername("juan")
        );
    }
}