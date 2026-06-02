package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.DetallePedidosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.DetallePedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetallePedidosServiceTest {

    @Mock
    private DetallePedidosRepository repository;

    @InjectMocks
    private DetallePedidosService service;

    @Test
    void testGetOrderDetailById() {
        DetallePedidosEntity detail = new DetallePedidosEntity();

        when(repository.getOrderDetailById(1)).thenReturn(detail);

        DetallePedidosEntity result = service.getOrderDetailById(1);

        assertNotNull(result);
    }

    @Test
    void testGetOrderDetailsByOrder() {
        PedidosEntity order = new PedidosEntity();

        when(repository.getOrderDetailsByOrder(order))
                .thenReturn(List.of(new DetallePedidosEntity()));

        List<DetallePedidosEntity> result = service.getOrderDetailsByOrder(order);

        assertEquals(1, result.size());
    }

    @Test
    void testSaveOrderDetail() {
        DetallePedidosEntity detail = new DetallePedidosEntity();

        when(repository.saveOrderDetail(detail)).thenReturn(detail);

        DetallePedidosEntity result = service.saveOrderDetail(detail);

        assertNotNull(result);
    }

    @Test
    void testFindByOrderAndProduct() {
        PedidosEntity order = new PedidosEntity();
        ProductosEntity product = new ProductosEntity();

        DetallePedidosEntity detail = new DetallePedidosEntity();

        when(repository.findByOrderAndProduct(order, product))
                .thenReturn(detail);

        DetallePedidosEntity result = service.findByOrderAndProduct(order, product);

        assertNotNull(result);
    }

    @Test
    void testDeleteOrderDetail() {
        when(repository.deleteOrderDetailById(1)).thenReturn(true);

        boolean result = service.deleteOrderDetailById(1);

        assertTrue(result);
    }
}