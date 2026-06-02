package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.*;
import com.ln.mial.ecommerce.infraestructure.entity.DetallePedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagoControllerTest {

    @InjectMocks
    private PagoController pagoController;

    @Mock
    private PagosService pagosService;

    @Mock
    private DetallePedidosService detallePedidosService;

    @Mock
    private UploadFile uploadFile;

    @Mock
    private PedidosService pedidosService;

    @Mock
    private AlmacenService almacenService;

    @Mock
    private EmailService emailService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private MultipartFile multipartFile;

    @Test
    void testShowPaymentPage_OrderNull() {
        when(session.getAttribute("currentOrder")).thenReturn(null);

        String view = pagoController.showPaymentPage(session, model);

        assertEquals("redirect:/user/carrito", view);
    }

    @Test
    void testShowPaymentPage_OK() {
        PedidosEntity order = new PedidosEntity();
        when(session.getAttribute("currentOrder")).thenReturn(order);

        DetallePedidosEntity detail = new DetallePedidosEntity();
        detail.setPrice(BigDecimal.TEN);
        detail.setQuantity(2);

        when(detallePedidosService.getOrderDetailsByOrder(order))
                .thenReturn(List.of(detail));

        String view = pagoController.showPaymentPage(session, model);

        assertEquals("pago", view);
    }

    @Test
    void testConfirmarPago_OrderNull() throws Exception {
        when(session.getAttribute("currentOrder")).thenReturn(null);

        ModelAndView mv = pagoController.confirmarPago(multipartFile, "dir", session);

        assertEquals("redirect:/user/carrito", mv.getViewName());
    }
}