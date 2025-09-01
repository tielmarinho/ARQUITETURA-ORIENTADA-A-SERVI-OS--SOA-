package br.edu.fiap.soa.estacionamento.domain;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name="ticket")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name="veiculo_id", nullable=false, foreignKey=@ForeignKey(name="fk_ticket_veiculo"))
    private Veiculo veiculo;
    @Column(length=10, nullable=false) private String vaga;
    @Column(nullable=false) private LocalDateTime entrada;
    private LocalDateTime saida;
    @Column(precision=10, scale=2) private BigDecimal valor;
    @Enumerated(EnumType.STRING) @Column(length=20, nullable=false) private TicketStatus status;
    @PrePersist public void prePersist(){ if(entrada==null) entrada=LocalDateTime.now(); if(status==null) status=TicketStatus.ABERTO; }
}
