package com.fiap.aeroporto.controller;

import com.fiap.aeroporto.model.Aeroporto;
import com.fiap.aeroporto.service.AeroportoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aeroportos")
public class AeroportoController {

    private final AeroportoService service;

    public AeroportoController(AeroportoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Aeroporto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Aeroporto> criar(@RequestBody Aeroporto a) {
        Aeroporto criado = service.criar(a);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aeroporto> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
