package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.DetallePedidosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.DetallePedidosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.DetallePedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.ProductosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetallePedidosRepositoryImplTest {

    @Mock
    private DetallePedidosCrudRepository crudRepository;

    @InjectMocks
    private DetallePedidosRepositoryImpl repository;

    @Test
    void testGetOrderDetailById() {
        DetallePedidosEntity detail = new DetallePedidosEntity();
        detail.setId(1);

        when(crudRepository.findById(1)).thenReturn(Optional.of(detail));

        DetallePedidosEntity result = repository.getOrderDetailById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetOrderDetailsByOrder() {
        PedidosEntity order = new PedidosEntity();

        when(crudRepository.findByOrder(order))
                .thenReturn(List.of(new DetallePedidosEntity()));

        List<DetallePedidosEntity> result = repository.getOrderDetailsByOrder(order);

        assertEquals(1, result.size());
    }

    @Test
    void testSaveOrderDetail() {
        DetallePedidosEntity detail = new DetallePedidosEntity();

        when(crudRepository.save(detail)).thenReturn(detail);

        DetallePedidosEntity result = repository.saveOrderDetail(detail);

        assertNotNull(result);
    }

    @Test
    void testDeleteOrderDetail() {
        doNothing().when(crudRepository).deleteById(1);

        boolean result = repository.deleteOrderDetailById(1);

        assertTrue(result);
    }

    @Test
    void testFindByOrderAndProduct() {
        PedidosEntity order = new PedidosEntity();
        ProductosEntity product = new ProductosEntity();

        DetallePedidosEntity detail = new DetallePedidosEntity();

        when(crudRepository.findByOrderAndProduct(order, product))
                .thenReturn(detail);

        DetallePedidosEntity result = repository.findByOrderAndProduct(order, product);

        assertNotNull(result);
    }
}