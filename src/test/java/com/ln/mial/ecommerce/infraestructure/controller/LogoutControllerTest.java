package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.LogoutService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogoutControllerTest {

    @Mock
    private LogoutService logoutService;

    @Mock
    private HttpSession session;

    private LogoutController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new LogoutController(logoutService);
    }

    @Test
    void logout_ReturnHomeRedirect() {

        String result = controller.logout(session);

        verify(logoutService).logout(session);

        assertEquals("redirect:/home", result);
    }
}
