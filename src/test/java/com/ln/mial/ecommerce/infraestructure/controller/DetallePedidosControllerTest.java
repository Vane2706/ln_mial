package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.AlmacenService;
import com.ln.mial.ecommerce.app.service.DetallePedidosService;
import com.ln.mial.ecommerce.app.service.PedidosService;
import com.ln.mial.ecommerce.app.service.ProductosService;
import com.ln.mial.ecommerce.infraestructure.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetallePedidosControllerTest {

    @InjectMocks
    private DetallePedidosController controller;

    @Mock
    private DetallePedidosService detallePedidosService;

    @Mock
    private PedidosService pedidosService;

    @Mock
    private ProductosService productService;

    @Mock
    private AlmacenService almacenService;

    @Mock
    private HttpSession session;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @Test
    void testShowProductDetail_WithStock() {
        ProductosEntity product = new ProductosEntity();
        product.setId(1);

        AlmacenEntity stock = new AlmacenEntity();
        stock.setBalance(10);

        when(productService.getProductById(1)).thenReturn(product);
        when(almacenService.getStockByProductEntity(product))
                .thenReturn(List.of(stock));

        String view = controller.showProductDetail(1, model);

        assertEquals("product-detail", view);
        verify(model).addAttribute("balance", 10);
    }

    @Test
    void testShowProductDetail_NoStock() {
        ProductosEntity product = new ProductosEntity();

        when(productService.getProductById(1)).thenReturn(product);
        when(almacenService.getStockByProductEntity(product))
                .thenReturn(List.of());

        String view = controller.showProductDetail(1, model);

        assertEquals("product-detail", view);
        verify(model).addAttribute("balance", 0);
    }

    @Test
    void testAddOrderDetail_UserNotLogged() {

        when(session.getAttribute("user")).thenReturn(null);

        String view = controller.addOrderDetail(
                1, 2, session, request, redirectAttributes
        );

        assertEquals("redirect:/login", view);

        verify(redirectAttributes)
                .addFlashAttribute(
                        "error",
                        "Debes iniciar sesión para realizar una compra."
                );
    }

    @Test
    void testAddOrderDetail_ProductAdded() {

        UsuariosEntity user = new UsuariosEntity();

        ProductosEntity product = new ProductosEntity();
        product.setPrice(BigDecimal.TEN);

        AlmacenEntity stock = new AlmacenEntity();
        stock.setBalance(10);

        PedidosEntity order = new PedidosEntity();
        order.setTotalAmount(BigDecimal.ZERO);

        when(request.getHeader("Referer"))
                .thenReturn("/product/1");

        when(session.getAttribute("user"))
                .thenReturn(user);

        when(productService.getProductById(1))
                .thenReturn(product);

        when(almacenService.getStockByProductEntity(product))
                .thenReturn(List.of(stock));

        when(session.getAttribute("currentOrder"))
                .thenReturn(order);

        when(detallePedidosService.findByOrderAndProduct(order, product))
                .thenReturn(null);

        when(detallePedidosService.saveOrderDetail(any()))
                .thenReturn(new DetallePedidosEntity());

        String view = controller.addOrderDetail(
                1, 2, session, request, redirectAttributes
        );

        assertEquals("redirect:/product/1", view);

        verify(detallePedidosService).saveOrderDetail(any());
        verify(redirectAttributes)
                .addFlashAttribute(
                        "success",
                        "Producto agregado correctamente."
                );
    }
}