package com.usjt.sce;

import static org.junit.Assert.*;
import org.junit.Test;
import com.usjt.sce.model.ConfiguraDB;
import com.usjt.sce.model.FabricaDeConexoes;

public class TestaConexaoComDB {
	/**
	 * Objetivo - verificar o comportamento do sistema na conexao ao DB com
	 * configuracao valida Pré-condição - a configuracao default da fabrica de
	 * conexoes é valida
	 */
	static FabricaDeConexoes fabrica;
	@Test
	public void quandoConectaComOBancoRetornaOK() {
		// cenario
		// acao
		fabrica = new FabricaDeConexoes();
		// verificacao
		assertNotNull(fabrica.getConnection());
	}

	/**
	 * Objetivo - verificar o comportamento do sistema na conexao ao DB com senha de
	 * acesso invalida Pré-condição - a senha cadastrada é "aluno"
	 */
	@Test
	public void quandoConectaComSenhaInvalida_SQLException() {
		// cenario
		String url = "jdbc:mysql://localhost:3306/biblioteca";
		String driver = "com.mysql.jdbc.Driver";
		String usuario = "root";
		String senha = "aaa"; // senha errada
		FabricaDeConexoes fabricaDeConexoes = null;
		ConfiguraDB configuraDB = new ConfiguraDB(url, driver, usuario, senha);
		fabricaDeConexoes = new FabricaDeConexoes(configuraDB);
		try {
			// acao
			fabricaDeConexoes.getConnection();
			fail("deveria falhar");
		} catch (Exception e) {
			// verificacao
			System.out.println(e.getMessage());
			assertEquals(e.getMessage(), "java.sql.SQLException: Access denied for user 'root'@'localhost' (using password: YES)");
		}
	}
}