package br.edu.fiap.soa.estacionamento.repository;
import br.edu.fiap.soa.estacionamento.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    Optional<Veiculo> findByPlaca(String placa);
    boolean existsByPlaca(String placa);
}
