package br.com.alexandrepontes.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.alexandrepontes.model.entities.Disciplina;
/**
 * Operações elementares de conexão com a base de dados para a entidade Disciplina.
 * @author alepq
 *
 */
public interface DisciplinaDao {	
	 Disciplina inserir(Disciplina d) throws SQLException, InterruptedException;
	 Disciplina atualizar(Disciplina d);
	 Disciplina encontrarPorId(Integer id);
	 List<Disciplina> obterDisciplinas();
	 void excluirPorNome(String nomedisc);
	 boolean encontrarPorNome(String nomedisc);
}