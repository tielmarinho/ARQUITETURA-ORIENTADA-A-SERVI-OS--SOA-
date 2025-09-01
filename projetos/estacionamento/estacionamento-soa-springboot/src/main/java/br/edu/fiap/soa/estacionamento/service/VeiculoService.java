package br.edu.fiap.soa.estacionamento.service;
import br.edu.fiap.soa.estacionamento.domain.Veiculo;
import br.edu.fiap.soa.estacionamento.repository.VeiculoRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
public class VeiculoService {
    private final VeiculoRepository repo;
    public VeiculoService(VeiculoRepository repo){ this.repo = repo; }
    public List<Veiculo> listar(){ return repo.findAll(); }
    public Veiculo obter(Long id){ return repo.findById(id).orElseThrow(()-> new RuntimeException("Veículo não encontrado")); }
    @Transactional public Veiculo criar(Veiculo v){
        if(repo.existsByPlaca(v.getPlaca())) throw new RuntimeException("Placa já cadastrada");
        return repo.save(v);
    }
    @Transactional public Veiculo atualizar(Long id, Veiculo novo){
        Veiculo v = obter(id); v.setModelo(novo.getModelo()); v.setCor(novo.getCor()); return repo.save(v);
    }
    @Transactional public void deletar(Long id){ repo.deleteById(id); }
}
