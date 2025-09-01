package br.edu.fiap.soa.estacionamento.service;
import br.edu.fiap.soa.estacionamento.domain.*; import br.edu.fiap.soa.estacionamento.repository.*;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal; import java.math.RoundingMode; import java.time.Duration; import java.time.LocalDateTime; import java.util.List;
@Service
public class TicketService {
    private final TicketRepository ticketRepo; private final VeiculoRepository veiculoRepo;
    @Value("${parking.hourly-rate:8.00}") private BigDecimal hourlyRate;
    public void setHourlyRate(BigDecimal rate){ this.hourlyRate = rate; }
    public TicketService(TicketRepository t, VeiculoRepository v){ this.ticketRepo=t; this.veiculoRepo=v; }
    public Ticket obter(Long id){ return ticketRepo.findById(id).orElseThrow(()-> new RuntimeException("Ticket não encontrado")); }
    public List<Ticket> listarAbertos(){ return ticketRepo.findByStatus(TicketStatus.ABERTO); }
    @Transactional public Ticket checkIn(String placa, String modelo, String cor, String vaga){
        Veiculo veiculo = veiculoRepo.findByPlaca(placa).orElseGet(()-> { Veiculo nv = new Veiculo(); nv.setPlaca(placa); nv.setModelo(modelo); nv.setCor(cor); return veiculoRepo.save(nv); });

        // Impede ticket ABERTO duplicado para o mesmo veículo
        if(ticketRepo.existsByVeiculoIdAndStatus(veiculo.getId(), TicketStatus.ABERTO)){
            throw new RuntimeException("Já existe um ticket ABERTO para este veículo");
        }

        Ticket t = Ticket.builder().veiculo(veiculo).vaga(vaga).entrada(LocalDateTime.now()).status(TicketStatus.ABERTO).build();
        return ticketRepo.save(t);
    }
    @Transactional public Ticket checkOut(Long ticketId){
        Ticket t = obter(ticketId);
        if(t.getStatus()==TicketStatus.FECHADO) throw new RuntimeException("Ticket já está fechado");
        t.setSaida(LocalDateTime.now()); t.setValor(calcularValor(t.getEntrada(), t.getSaida())); t.setStatus(TicketStatus.FECHADO);
        return ticketRepo.save(t);
    }
    public BigDecimal calcularValor(LocalDateTime entrada, LocalDateTime saida){
        long minutes = Duration.between(entrada, saida).toMinutes(); long hours = (minutes + 59)/60; if(hours<=0) hours=1;
        return hourlyRate.multiply(BigDecimal.valueOf(hours)).setScale(2, RoundingMode.HALF_UP);
    }
}
