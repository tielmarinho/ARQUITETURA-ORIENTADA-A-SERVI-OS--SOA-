package br.edu.fiap.soa.estacionamento.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "veiculo", uniqueConstraints = @UniqueConstraint(name = "uk_veiculo_placa", columnNames = "placa"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Veiculo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String placa;
    @Column(length = 100)
    private String modelo;
    @Column(length = 50)
    private String cor;
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;
    @PrePersist public void prePersist(){ if(dataCadastro==null) dataCadastro=LocalDateTime.now(); }
}
