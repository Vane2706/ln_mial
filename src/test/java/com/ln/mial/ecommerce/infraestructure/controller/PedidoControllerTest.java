package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.AlmacenService;
import com.ln.mial.ecommerce.app.service.DetallePedidosService;
import com.ln.mial.ecommerce.app.service.PedidosService;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.StatusPedido;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    @Mock
    private PedidosService pedidosService;

    @Mock
    private DetallePedidosService detallePedidosService;

    @Mock
    private AlmacenService almacenService;

    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Test
    void showCartWithoutOrder() {

        when(session.getAttribute("currentOrder"))
                .thenReturn(null);

        String view =
                pedidoController.showCart(session, model);

        assertEquals("carrito", view);

        verify(model).addAttribute(eq("cart"), any());
    }

    @Test
    void getCartItemCountWithoutOrder() {

        when(session.getAttribute("currentOrder"))
                .thenReturn(null);

        int resultado =
                pedidoController.getCartItemCount(session);

        assertEquals(0, resultado);
    }

    @Test
    void deleteFromCart() {

        String view =
                pedidoController.deleteFromCart(1);

        verify(detallePedidosService)
                .deleteOrderDetailById(1);

        assertEquals("redirect:/user/carrito", view);
    }

    @Test
    void getCartItemCountPaidOrder() {

        PedidosEntity pedido = new PedidosEntity();
        pedido.setStatusPedido(StatusPedido.PAGADO);

        when(session.getAttribute("currentOrder"))
                .thenReturn(pedido);

        int resultado =
                pedidoController.getCartItemCount(session);

        assertEquals(0, resultado);
    }
}