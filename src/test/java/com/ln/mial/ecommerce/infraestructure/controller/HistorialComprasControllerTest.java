package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.DetallePedidosService;
import com.ln.mial.ecommerce.app.service.EnviosService;
import com.ln.mial.ecommerce.app.service.PagosService;
import com.ln.mial.ecommerce.app.service.PedidosService;
import com.ln.mial.ecommerce.infraestructure.entity.*;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistorialComprasControllerTest {

    @InjectMocks
    private HistorialComprasController controller;

    @Mock
    private PagosService pagosService;

    @Mock
    private PedidosService pedidosService;

    @Mock
    private DetallePedidosService detallePedidosService;

    @Mock
    private EnviosService enviosService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Test
    void testShowPurchasedProducts() {
        UsuariosEntity user = new UsuariosEntity();
        user.setUsername("juan");

        when(session.getAttribute("user")).thenReturn(user);

        PedidosEntity order = new PedidosEntity();
        order.setUser(user);

        when(pedidosService.getOrdersByUserAndStatus(user, StatusPedido.PAGADO))
                .thenReturn(List.of(order));

        when(detallePedidosService.getOrderDetailsByOrder(order))
                .thenReturn(List.of(new DetallePedidosEntity()));

        when(pagosService.getPaymentsByOrder(order))
                .thenReturn(List.of(new PagosEntity()));

        String view = controller.showPurchasedProducts(session, model);

        assertEquals("historial-compras", view);
    }

    @Test
    void testShowShippingForm_OrderNotFound() {
        when(pedidosService.getOrderById(1)).thenReturn(null);

        String view = controller.showShippingForm(1, model);

        assertEquals("redirect:historial-compras", view);
    }

    @Test
    void testShowShippingForm_OK() {
        PedidosEntity order = new PedidosEntity();
        when(pedidosService.getOrderById(1)).thenReturn(order);

        EnviosEntity envio = new EnviosEntity();
        envio.setShippingDate(LocalDateTime.now());
        envio.setEstimatedDeliveryDate(LocalDateTime.now());

        when(enviosService.getShippingByOrder(order))
                .thenReturn(List.of(envio));

        String view = controller.showShippingForm(1, model);

        assertEquals("ver-envio", view);
    }
}