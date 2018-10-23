package com.usjt.sce;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import com.usjt.sce.model.DAOFactory;
import com.usjt.sce.model.ILivroDAO;
import com.usjt.sce.model.Livro;

public class UC01CadastrarLivro {
	@AfterClass
	public static void reverter() {
		Livro umLivro = ObtemLivro.comDadosValidos();
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		livroDAO.exclui(umLivro.getIsbn());
	}

	@Test
	public void CT01CadastrarLivroComDadosValidos() {
		try {
			// cenario
			Livro umLivro = new Livro();
			// acao
			umLivro.setIsbn("121212");
			umLivro.setTitulo("Engenharia de Softwar");
			umLivro.setAutor("Pressman");
		} catch (RuntimeException e) {
			// verificacao
			fail("nao deve falhar");
		}
	}

	@Test
	public void CT02CadastrarLivroComISBNBranco() {
		try {
			// cenario
			Livro umLivro = new Livro();
			// acao
			umLivro.setIsbn("");
			umLivro.setTitulo("Engenharia de Softwar");
			umLivro.setAutor("Pressman");
		} catch (RuntimeException e) {
			// verificacao
			assertEquals("ISBN invalido", e.getMessage());
		}
	}

	@Test
	public void CT03CadastrarLivro_com_sucesso() {
		// cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		// acao
		int codigoRetorno = livroDAO.adiciona(umLivro);
		// verificacao
		assertEquals(1, codigoRetorno);
		
	}

	@Test
	public void CT04CadastrarLivro_dados_invalidos_isbn_ja_cadastrado() {
		// cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		// acao
		try {
			livroDAO.adiciona(umLivro);
			livroDAO.adiciona(umLivro);
			fail("deveria falhar - a aplicação não deve permtir a inclusão de um isbn repetido");
		} catch (Exception e) {
			// verificacao
			System.out.println(e.getMessage());
			assertEquals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '121212' for key 'PRIMARY'", e.getMessage());
		}
	}

}
