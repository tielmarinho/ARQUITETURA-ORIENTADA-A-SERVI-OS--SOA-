package com.fiap.restapi;

import com.fiap.restapi.model.Aluno;
import com.fiap.restapi.repository.AlunoRepository;
import com.fiap.restapi.service.AlunoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repo;

    @InjectMocks
    private AlunoService service;

    @Test
    @DisplayName("adicionar: deve criar aluno com nome/curso aparados e retornar com id")
    void adicionarDeveCriarComSucesso() {
        when(repo.adicionar(any(Aluno.class))).thenAnswer(inv -> {
            Aluno a = inv.getArgument(0);
            return new Aluno(10L, a.getNome(), a.getCurso());
        });

        Aluno salvo = service.adicionar("  Gabriel  ", "  Engenharia  ");

        ArgumentCaptor<Aluno> captor = ArgumentCaptor.forClass(Aluno.class);
        verify(repo).adicionar(captor.capture());
        Aluno enviado = captor.getValue();
        assertEquals("Gabriel", enviado.getNome());
        assertEquals("Engenharia", enviado.getCurso());

        assertNotNull(salvo);
        assertEquals(10L, salvo.getId());
        assertEquals("Gabriel", salvo.getNome());
        assertEquals("Engenharia", salvo.getCurso());
    }

    @Test
    @DisplayName("adicionar: deve falhar quando nome for vazio")
    void adicionarDeveFalharNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> service.adicionar("   ", "Curso"));
        verifyNoInteractions(repo);
    }

    @Test
    @DisplayName("adicionar: deve falhar quando curso for vazio")
    void adicionarDeveFalharCursoVazio() {
        assertThrows(IllegalArgumentException.class, () -> service.adicionar("Gabriel", "   "));
        verifyNoInteractions(repo);
    }
}
