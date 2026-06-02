package com.ln.mial.ecommerce.app.service;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LogoutServiceTest {

    @Test
    void logout_RemovesSessionAttribute() {

        HttpSession session = mock(HttpSession.class);

        LogoutService logoutService = new LogoutService();

        logoutService.logout(session);

        verify(session).removeAttribute("iduser");
    }
}
