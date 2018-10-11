package com.usjt.sce;

import static org.junit.Assert.*;

import org.junit.Test;

import com.usjt.sce.model.DAOFactory;
import com.usjt.sce.model.ILivroDAO;
import com.usjt.sce.model.Livro;

public class UC02ConsultarLivro {
	@Test
	public void CT03CadastrarLivro_com_sucesso(){
		//cenario
		Livro umLivro = ObtemLivro.comDadosValidos();
		Livro resultadoObtido=null;
		DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		ILivroDAO livroDAO = mySQLFactory.getLivroDAO();
		//acao
		livroDAO.adiciona(umLivro);
		resultadoObtido = livroDAO.consulta(umLivro.getIsbn());
		//verificacao
		assertTrue(resultadoObtido.equals(umLivro));
        livroDAO.exclui(umLivro.getIsbn());
	}
}
