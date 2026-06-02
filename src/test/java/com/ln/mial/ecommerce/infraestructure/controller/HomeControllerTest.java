package com.ln.mial.ecommerce.infraestructure.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Test
    void home_ReturnHomeView() {

        HomeController controller =
                new HomeController();

        String result = controller.home();

        assertEquals("home", result);
    }
}
