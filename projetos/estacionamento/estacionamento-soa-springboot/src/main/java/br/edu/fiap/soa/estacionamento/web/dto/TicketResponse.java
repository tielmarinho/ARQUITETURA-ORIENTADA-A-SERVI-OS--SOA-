package br.edu.fiap.soa.estacionamento.web.dto;
import br.edu.fiap.soa.estacionamento.domain.TicketStatus;
import lombok.Builder; import lombok.Data;
import java.math.BigDecimal; import java.time.LocalDateTime;
@Data @Builder
public class TicketResponse {
    private Long id; private String placa; private String vaga;
    private LocalDateTime entrada; private LocalDateTime saida;
    private BigDecimal valor; private TicketStatus status;
}
