package com.ln.mial.ecommerce.infraestructure.controller;

import com.ln.mial.ecommerce.app.service.PedidosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminGraficoTest {

    @Mock
    private PedidosService pedidosService;

    @InjectMocks
    private AdminGrafico adminGrafico;

    @Test
    void testGetYearlyMonthlyOrders() {
        Map<Integer, Map<Integer, Long>> data = new HashMap<>();

        when(pedidosService.getYearlyMonthlyOrderCounts()).thenReturn(data);

        Map<Integer, Map<Integer, Long>> result = adminGrafico.getYearlyMonthlyOrders();

        assertNotNull(result);
    }

    @Test
    void testShowStatisticsPage() {
        String view = adminGrafico.showStatisticsPage();

        assertEquals("admin/grafico", view);
    }
}