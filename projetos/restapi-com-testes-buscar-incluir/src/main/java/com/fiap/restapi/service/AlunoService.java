package com.fiap.restapi.service;

import com.fiap.restapi.model.Aluno;
import com.fiap.restapi.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repo;

    public AlunoService(AlunoRepository repo){
        this.repo = repo;
    }

    public Aluno adicionar(String nome, String curso){
        validar(nome, curso);
        Aluno aluno = new Aluno(null, nome.trim(), curso.trim());
        return repo.adicionar(aluno);
    }

    private void validar(String nome, String curso){
        if(nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório");
        if(curso == null || curso.isBlank()) throw new IllegalArgumentException("Curso é obrigatório");
        if(nome.length() > 150) throw new IllegalArgumentException("Nome excede 150 caracteres");
        if(curso.length() > 150) throw new IllegalArgumentException("Curso excede 150 caracteres");
    }
}