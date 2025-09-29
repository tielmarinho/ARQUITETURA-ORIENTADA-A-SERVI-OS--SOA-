package com.fiap.restapi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String URL =
            System.getenv().getOrDefault("ORACLE_URL","jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl");
    private static final String USER =
            System.getenv().getOrDefault("ORACLE_USER", "RM");
    private static final String PASS =
            System.getenv().getOrDefault("ORACLE_PASSWORD","ddmmaa");

    public static Connection getConnection() {
        try{
            Properties props  = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);
            return DriverManager.getConnection(URL, props);
        }catch (SQLException e){
            throw new RuntimeException("Erro ao abrir conexão com o Oracle: " + e.getMessage(), e);
        }
    }
}