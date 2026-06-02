package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.RegisterService;
import com.ln.mial.ecommerce.infraestructure.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegisterControllerTest {

    @Mock
    private RegisterService registerService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Mock
    private Model model;

    private RegisterController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RegisterController(registerService);
    }

    @Test
    void showRegister_ReturnRegisterView() {

        UserDto userDto = new UserDto();

        String result = controller.showRegister(userDto);

        assertEquals("cuenta/register", result);
    }

    @Test
    void registerUser_WithValidationErrors_ReturnRegisterView() {

        UserDto userDto = new UserDto();

        when(bindingResult.hasErrors()).thenReturn(true);

        String result = controller.registerUser(
                userDto,
                bindingResult,
                redirectAttributes
        );

        assertEquals("cuenta/register", result);
    }

    @Test
    void registerUser_Success_ReturnLoginRedirect() {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Juan");
        userDto.setEmail("juan@gmail.com");
        userDto.setPassword("123456");
        userDto.setCellphone("987654321");

        when(bindingResult.hasErrors()).thenReturn(false);

        String result = controller.registerUser(
                userDto,
                bindingResult,
                redirectAttributes
        );

        verify(registerService, times(1))
                .register(any());

        assertEquals("redirect:/login", result);
    }

    @Test
    void registerUser_EmailExists_ReturnRegisterRedirect() {

        UserDto userDto = new UserDto();
        userDto.setFirstName("Juan");
        userDto.setEmail("juan@gmail.com");
        userDto.setPassword("123456");
        userDto.setCellphone("987654321");

        when(bindingResult.hasErrors()).thenReturn(false);

        doThrow(new IllegalArgumentException("Correo ya registrado"))
                .when(registerService)
                .register(any());

        String result = controller.registerUser(
                userDto,
                bindingResult,
                redirectAttributes
        );

        assertEquals("redirect:/register", result);
    }
}
