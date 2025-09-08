package com.fiap.aeroporto.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "AEROPORTO")
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODIGO", nullable = false, unique = true)
    private String codigo;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CIDADE")
    private String cidade;

    public Aeroporto() {}

    public Aeroporto(String codigo, String nome, String cidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.cidade = cidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aeroporto)) return false;
        Aeroporto that = (Aeroporto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
