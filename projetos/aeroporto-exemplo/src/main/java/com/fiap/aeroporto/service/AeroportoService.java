package com.fiap.aeroporto.service;

import com.fiap.aeroporto.model.Aeroporto;
import com.fiap.aeroporto.repository.AeroportoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AeroportoService {

    private final AeroportoRepository repository;

    public AeroportoService(AeroportoRepository repository) {
        this.repository = repository;
    }

    public List<Aeroporto> listar() {
        return repository.findAll();
    }

    public Aeroporto criar(Aeroporto a) {
        // TODO: validações aqui; lançar exceções que os alunos devem tratar em @RestControllerAdvice
        return repository.save(a);
    }

    public Optional<Aeroporto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Aeroporto> buscarPorCodigo(String codigo) {
        return repository.findByCodigo(codigo);
    }
}
