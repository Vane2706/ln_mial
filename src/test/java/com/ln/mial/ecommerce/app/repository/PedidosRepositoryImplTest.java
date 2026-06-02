package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.PedidosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.PedidosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidosRepositoryImplTest {

    @Mock
    private PedidosCrudRepository crudRepository;

    @InjectMocks
    private PedidosRepositoryImpl repository;

    @Test
    void getOrderById() {

        PedidosEntity pedido = new PedidosEntity();
        pedido.setId(1);

        when(crudRepository.findById(1))
                .thenReturn(Optional.of(pedido));

        PedidosEntity resultado =
                repository.getOrderById(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    void saveOrder() {

        PedidosEntity pedido = new PedidosEntity();

        when(crudRepository.save(pedido))
                .thenReturn(pedido);

        PedidosEntity resultado =
                repository.saveOrder(pedido);

        assertNotNull(resultado);
    }
}