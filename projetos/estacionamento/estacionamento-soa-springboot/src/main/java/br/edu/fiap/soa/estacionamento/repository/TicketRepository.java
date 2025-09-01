package br.edu.fiap.soa.estacionamento.repository;
import br.edu.fiap.soa.estacionamento.domain.Ticket;
import br.edu.fiap.soa.estacionamento.domain.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(TicketStatus status);
    boolean existsByVeiculoIdAndStatus(Long veiculoId, TicketStatus status);
}
