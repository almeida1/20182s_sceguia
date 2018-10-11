package com.usjt.sce.model;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class FabricaDeConexoes {
	static String url = "jdbc:mysql://localhost:3306/biblioteca";
	static String driver = "com.mysql.jdbc.Driver";
	static String usuario = "root";
	static String senha = "";
	static Logger logger = Logger.getLogger(FabricaDeConexoes.class);
	public FabricaDeConexoes(ConfiguraDB configura) {
		this.url = configura.getUrl();
		this.driver = configura.getDriver();
		this.usuario = configura.getUsuario();
		this.senha = configura.getSenha();
	}

	public FabricaDeConexoes() {
	}

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			return (Connection) DriverManager.getConnection(url, usuario, senha);
		} catch (CommunicationsException e) {
			logger.info("Exceçãoo de comunicacao com o DB causa: " + e.getMessage());
			throw new RuntimeException(e);
		} catch (SQLException e) {
			logger.info("Exceção geral causa SQLException: " + e.getMessage() + ";" + e.getErrorCode());
			throw new RuntimeException(e);
		} catch (Exception e) {
			logger.info("Exceçãoo geral causa: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}