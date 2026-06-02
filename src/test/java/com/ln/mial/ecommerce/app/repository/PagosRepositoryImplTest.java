package com.ln.mial.ecommerce.app.repository;

import com.ln.mial.ecommerce.infraestructure.adapter.PagosCrudRepository;
import com.ln.mial.ecommerce.infraestructure.adapter.PagosRepositoryImpl;
import com.ln.mial.ecommerce.infraestructure.entity.PagosEntity;
import com.ln.mial.ecommerce.infraestructure.entity.PedidosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagosRepositoryImplTest {

    @Mock
    private PagosCrudRepository paymentCrudRepository;

    @InjectMocks
    private PagosRepositoryImpl pagosRepository;

    @Test
    void testGetPayments() {
        List<PagosEntity> list = List.of(new PagosEntity(), new PagosEntity());
        when(paymentCrudRepository.findAll()).thenReturn(list);

        List<PagosEntity> result = pagosRepository.getPayments();

        assertEquals(2, result.size());
    }

    @Test
    void testGetPaymentById() {
        PagosEntity pago = new PagosEntity();
        pago.setId(1);

        when(paymentCrudRepository.findById(1)).thenReturn(Optional.of(pago));

        PagosEntity result = pagosRepository.getPaymentById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testGetPaymentsByOrder() {
        PedidosEntity order = new PedidosEntity();
        List<PagosEntity> list = List.of(new PagosEntity());

        when(paymentCrudRepository.findByOrder(order)).thenReturn(list);

        List<PagosEntity> result = pagosRepository.getPaymentsByOrder(order);

        assertEquals(1, result.size());
    }

    @Test
    void testSavePayment() {
        PagosEntity pago = new PagosEntity();
        when(paymentCrudRepository.save(pago)).thenReturn(pago);

        PagosEntity result = pagosRepository.savePayment(pago);

        assertNotNull(result);
    }
}
