package br.edu.fiap.soa.estacionamento;

import br.edu.fiap.soa.estacionamento.service.TicketService;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketServiceTest {

    @Test
    void calcula_valor_minimo_1h() {
        TicketService svc = new TicketService(null, null);
        svc.setHourlyRate(new BigDecimal("10.00"));
        BigDecimal v = svc.calcularValor(LocalDateTime.now(), LocalDateTime.now());
        assertEquals(new BigDecimal("10.00"), v);
    }

    @Test
    void arredonda_para_cima() {
        TicketService svc = new TicketService(null, null);
        svc.setHourlyRate(new BigDecimal("8.00"));
        BigDecimal v1 = svc.calcularValor(LocalDateTime.now().minusMinutes(30), LocalDateTime.now());
        BigDecimal v2 = svc.calcularValor(LocalDateTime.now().minusMinutes(61), LocalDateTime.now());
        assertEquals(new BigDecimal("8.00"), v1);  // 30min -> 1h
        assertEquals(new BigDecimal("16.00"), v2); // 61min -> 2h
    }
}
