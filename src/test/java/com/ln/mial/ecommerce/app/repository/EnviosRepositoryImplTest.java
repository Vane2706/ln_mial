package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.EnviosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.EnviosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.EnviosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnviosRepositoryImplTest {

    @Mock
    private EnviosCrudRepository shippingCrudRepository;

    @InjectMocks
    private EnviosRepositoryImpl enviosRepository;

    @Test
    void testGetShippingByOrder() {
        PedidosEntity order = new PedidosEntity();
        List<EnviosEntity> list = List.of(new EnviosEntity());

        when(shippingCrudRepository.findByOrder(order)).thenReturn(list);

        List<EnviosEntity> result = enviosRepository.getShippingByOrder(order);

        assertEquals(1, result.size());
    }

    @Test
    void testSaveShipping() {
        EnviosEntity envio = new EnviosEntity();

        when(shippingCrudRepository.save(envio)).thenReturn(envio);

        EnviosEntity result = enviosRepository.saveShipping(envio);

        assertNotNull(result);
    }

    @Test
    void testDeleteShipping() {
        doNothing().when(shippingCrudRepository).deleteById(1);

        boolean result = enviosRepository.deleteShippingById(1);

        assertTrue(result);
    }
}
