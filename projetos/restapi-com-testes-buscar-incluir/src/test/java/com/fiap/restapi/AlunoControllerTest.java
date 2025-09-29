package com.fiap.restapi;

import com.fiap.restapi.controller.AlunoController;
import com.fiap.restapi.model.Aluno;
import com.fiap.restapi.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;

@WebMvcTest(controllers = AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService service;

    @Test
    void buscarDeveRetornarAlunoExistente() throws Exception {
        mockMvc.perform(get("/api/alunos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nome", is("Gabriel")));
    }

    @Test
    void incluirDeveRetornarAlunoCriado() throws Exception {
        Mockito.when(service.adicionar(anyString(), anyString()))
                .thenReturn(new Aluno(100L, "Sofia", "Nutrição"));

        String json = "{ \"nome\":\"Sofia\", \"curso\":\"Nutrição\" }";
        mockMvc.perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()) // controller atual retorna 200
                .andExpect(jsonPath("$.id", is(100)))
                .andExpect(jsonPath("$.nome", is("Sofia")))
                .andExpect(jsonPath("$.curso", is("Nutrição")));
    }
}
