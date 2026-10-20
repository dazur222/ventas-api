package com.elgardenia.ventas_api.venta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class VentaServiceTest {

    @Test
    void calculaTasaIepsEnLosLimitesDeGraduacionYParaGradosDecimales() {
        assertTasaIeps("14", "0.2650");
        assertTasaIeps("14.01", "0.3000");
        assertTasaIeps("14.5", "0.3000");
        assertTasaIeps("15", "0.3000");
        assertTasaIeps("20", "0.3000");
        assertTasaIeps("20.01", "0.5300");
        assertTasaIeps("20.5", "0.5300");
        assertTasaIeps("21", "0.5300");
    }

    private void assertTasaIeps(String gradosAlcohol, String tasaEsperada) {
        BigDecimal tasa = VentaService.calcularTasaIeps(new BigDecimal(gradosAlcohol));
        assertEquals(0, new BigDecimal(tasaEsperada).compareTo(tasa));
    }
}
