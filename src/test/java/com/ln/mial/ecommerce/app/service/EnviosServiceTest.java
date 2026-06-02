package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.EnviosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.EnviosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnviosServiceTest {

    @Mock
    private EnviosRepository enviosRepository;

    @InjectMocks
    private EnviosService enviosService;

    @Test
    void testGetShippingByOrder() {
        PedidosEntity order = new PedidosEntity();

        when(enviosRepository.getShippingByOrder(order))
                .thenReturn(List.of(new EnviosEntity()));

        List<EnviosEntity> result = enviosService.getShippingByOrder(order);

        assertEquals(1, result.size());
    }

    @Test
    void testSaveShipping_PendingToEnCamino() {
        EnviosEntity envio = new EnviosEntity();
        envio.setShippingDate(LocalDateTime.now().minusDays(1));
        envio.setShippingStatus("PENDIENTE");

        when(enviosRepository.saveShipping(any())).thenReturn(envio);

        EnviosEntity result = enviosService.saveShipping(envio);

        assertEquals("EN_CAMINO", result.getShippingStatus());
    }

    @Test
    void testSaveShipping_EnCaminoToEntregado() {
        EnviosEntity envio = new EnviosEntity();
        envio.setShippingDate(LocalDateTime.now().minusDays(2));
        envio.setEstimatedDeliveryDate(LocalDateTime.now().minusDays(1));
        envio.setShippingStatus("EN_CAMINO");

        when(enviosRepository.saveShipping(any())).thenReturn(envio);

        EnviosEntity result = enviosService.saveShipping(envio);

        assertEquals("ENTREGADO", result.getShippingStatus());
    }
}