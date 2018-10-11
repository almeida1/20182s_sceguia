package com.usjt.sce.model;

import com.mysql.jdbc.Connection;

public class MySQLDAOFactory extends DAOFactory {
	
	public static Connection createConnection() {
		return (Connection) FabricaDeConexoes.getConnection();
	}

	@Override
	public LivroDAO getLivroDAO() {
		return new LivroDAO();
	}
}
