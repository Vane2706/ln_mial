package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.PedidosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.StatusPedido;
import com.ln.mial.ecommerce.infraestructure.entity.UsuariosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidosServiceTest {

    @Mock
    private PedidosRepository pedidosRepository;

    @Mock
    private DetallePedidosService detallePedidosService;

    @InjectMocks
    private PedidosService pedidosService;

    @Test
    void getOrderById() {

        PedidosEntity pedido = new PedidosEntity();
        pedido.setId(1);

        when(pedidosRepository.getOrderById(1)).thenReturn(pedido);

        PedidosEntity resultado = pedidosService.getOrderById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void saveOrder() {

        PedidosEntity pedido = new PedidosEntity();
        pedido.setTotalAmount(BigDecimal.valueOf(100));

        when(pedidosRepository.saveOrder(pedido)).thenReturn(pedido);

        PedidosEntity resultado = pedidosService.saveOrder(pedido);

        assertEquals(BigDecimal.valueOf(100), resultado.getTotalAmount());
    }

    @Test
    void getOrdersByStatus() {

        List<PedidosEntity> pedidos = List.of(new PedidosEntity());

        when(pedidosRepository.getOrdersByStatus(StatusPedido.PAGADO))
                .thenReturn(pedidos);

        List<PedidosEntity> resultado =
                pedidosService.getOrdersByStatus(StatusPedido.PAGADO);

        assertEquals(1, resultado.size());
    }

    @Test
    void getOrdersByUserAndStatus() {

        UsuariosEntity usuario = new UsuariosEntity();

        List<PedidosEntity> pedidos = List.of(new PedidosEntity());

        when(pedidosRepository.getOrdersByUserAndStatus(
                usuario,
                StatusPedido.PAGADO))
                .thenReturn(pedidos);

        List<PedidosEntity> resultado =
                pedidosService.getOrdersByUserAndStatus(
                        usuario,
                        StatusPedido.PAGADO);

        assertEquals(1, resultado.size());
    }
}