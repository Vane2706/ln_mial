package com.ln.mial.ecommerce.app.service;

import com.ln.mial.ecommerce.app.repository.PagosRepository;
import com.ln.mial.ecommerce.infraestructure.entity.PagosEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagosServiceTest {

    @Mock
    private PagosRepository pagosRepository;

    @InjectMocks
    private PagosService pagosService;

    @Test
    void testGetPayments() {
        when(pagosRepository.getPayments()).thenReturn(List.of(new PagosEntity()));

        List<PagosEntity> result = pagosService.getPayments();

        assertEquals(1, result.size());
    }

    @Test
    void testGetPaymentById() {
        PagosEntity pago = new PagosEntity();
        when(pagosRepository.getPaymentById(1)).thenReturn(pago);

        PagosEntity result = pagosService.getPaymentById(1);

        assertNotNull(result);
    }

    @Test
    void testSavePayment() {
        PagosEntity pago = new PagosEntity();

        when(pagosRepository.savePayment(pago)).thenReturn(pago);

        PagosEntity result = pagosService.savePayment(pago);

        assertNotNull(result);
    }
}