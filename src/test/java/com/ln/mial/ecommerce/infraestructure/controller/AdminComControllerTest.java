package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.DetallePedidosService;
import com.ln.mial.ecommerce.app.service.EnviosService;
import com.ln.mial.ecommerce.app.service.PagosService;
import com.ln.mial.ecommerce.app.service.PedidosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminComControllerTest {

    @Mock
    private DetallePedidosService detallePedidosService;

    @Mock
    private PedidosService pedidosService;

    @Mock
    private PagosService pagosService;

    @Mock
    private EnviosService enviosService;

    @InjectMocks
    private AdminComController controller;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    void showCompras() {

        when(pedidosService.getOrdersByStatus(any()))
                .thenReturn(java.util.List.of());

        String view =
                controller.showCompras(model);

        assertEquals("admin/compras", view);
    }

    @Test
    void saveShippingDetailsException() {

        String view =
                controller.saveShippingDetails(
                        1,
                        "Delivery",
                        "fecha",
                        "fecha",
                        redirectAttributes
                );

        assertEquals("redirect:/admin/compras", view);
    }
}
