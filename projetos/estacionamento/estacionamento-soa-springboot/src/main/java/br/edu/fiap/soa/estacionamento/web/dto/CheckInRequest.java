package br.edu.fiap.soa.estacionamento.web.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class CheckInRequest {
    @Schema(example="ABC-1D23") @NotBlank private String placa;
    @Schema(example="Fiat Argo 1.3") private String modelo;
    @Schema(example="Preto") private String cor;
    @Schema(example="A12") @NotBlank private String vaga;
}
