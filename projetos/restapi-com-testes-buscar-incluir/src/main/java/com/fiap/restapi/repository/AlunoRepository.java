package com.fiap.restapi.repository;

import com.fiap.restapi.config.ConnectionFactory;
import com.fiap.restapi.model.Aluno;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AlunoRepository {
    public Aluno adicionar (Aluno aluno){
        final String sql = "INSERT INTO ALUNO (NOME, CURSO) VALUES (?, ?)";
        try{

            Connection conn = ConnectionFactory.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"});

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCurso());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                aluno.setId(rs.getLong(1));
            }
            return aluno;
        }catch (SQLException e){
            throw new RuntimeException("Erro ao adicionar aluno: " + e.getMessage(), e);
        }
    }
}