package br.edu.fiap.soa.estacionamento.web;
import br.edu.fiap.soa.estacionamento.domain.Ticket; import br.edu.fiap.soa.estacionamento.service.TicketService;
import br.edu.fiap.soa.estacionamento.web.dto.CheckInRequest; import br.edu.fiap.soa.estacionamento.web.dto.TicketResponse;
import io.swagger.v3.oas.annotations.Operation; import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/tickets") @Tag(name="Tickets", description="Fluxo de entrada/saída")
public class TicketController {
    private final TicketService service;
    public TicketController(TicketService service){ this.service = service; }
    @GetMapping("{id}") @Operation(summary="Obter ticket por ID")
    public TicketResponse obter(@PathVariable Long id){
        Ticket t = service.obter(id);
        return TicketResponse.builder().id(t.getId()).placa(t.getVeiculo().getPlaca()).vaga(t.getVaga()).entrada(t.getEntrada()).saida(t.getSaida()).valor(t.getValor()).status(t.getStatus()).build();
    }
    @GetMapping("abertos") @Operation(summary="Listar tickets abertos") public List<Ticket> abertos(){ return service.listarAbertos(); }
    @PostMapping("checkin") @Operation(summary="Realizar check-in")
    public ResponseEntity<TicketResponse> checkIn(@RequestBody @Valid CheckInRequest req){
        Ticket t = service.checkIn(req.getPlaca(), req.getModelo(), req.getCor(), req.getVaga());
        TicketResponse resp = TicketResponse.builder().id(t.getId()).placa(t.getVeiculo().getPlaca()).vaga(t.getVaga()).entrada(t.getEntrada()).status(t.getStatus()).build();
        return ResponseEntity.ok(resp);
    }
    @PostMapping("{id}/checkout") @Operation(summary="Realizar check-out")
    public TicketResponse checkOut(@PathVariable Long id){
        Ticket t = service.checkOut(id);
        return TicketResponse.builder().id(t.getId()).placa(t.getVeiculo().getPlaca()).vaga(t.getVaga()).entrada(t.getEntrada()).saida(t.getSaida()).valor(t.getValor()).status(t.getStatus()).build();
    }
}
