package br.edu.fiap.soa.estacionamento.web;
import br.edu.fiap.soa.estacionamento.domain.Veiculo;
import br.edu.fiap.soa.estacionamento.service.VeiculoService;
import br.edu.fiap.soa.estacionamento.web.dto.VeiculoDTO;
import io.swagger.v3.oas.annotations.Operation; import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
import java.net.URI; import java.util.List;
@RestController @RequestMapping("/api/veiculos") @Tag(name="Veículos", description="CRUD de veículos")
public class VeiculoController {
    private final VeiculoService service;
    public VeiculoController(VeiculoService service){ this.service = service; }
    @GetMapping @Operation(summary="Listar veículos") public List<Veiculo> listar(){ return service.listar(); }
    @GetMapping("{id}") @Operation(summary="Obter veículo por ID") public Veiculo obter(@PathVariable Long id){ return service.obter(id); }
    @PostMapping @Operation(summary="Criar veículo")
    public ResponseEntity<Veiculo> criar(@RequestBody @Valid VeiculoDTO dto){
        Veiculo v = new Veiculo(); v.setPlaca(dto.getPlaca()); v.setModelo(dto.getModelo()); v.setCor(dto.getCor());
        Veiculo salvo = service.criar(v); return ResponseEntity.created(URI.create("/api/veiculos/"+salvo.getId())).body(salvo);
    }
    @PutMapping("{id}") @Operation(summary="Atualizar veículo")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoDTO dto){
        Veiculo v = new Veiculo(); v.setModelo(dto.getModelo()); v.setCor(dto.getCor()); return service.atualizar(id, v);
    }
    @DeleteMapping("{id}") @Operation(summary="Excluir veículo")
    public ResponseEntity<Void> deletar(@PathVariable Long id){ service.deletar(id); return ResponseEntity.noContent().build(); }
}
