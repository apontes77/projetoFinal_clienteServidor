package br.com.alexandrepontes.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.alexandrepontes.model.entities.Turma;
/**
 * Operações elementares de conexão com a base de dados para a entidade Turma.
 * @author alepq
 *
 */
public interface TurmaDao {		
		Turma inserir(Turma t) throws SQLException;
		Turma atualizar (Turma t);
		void excluirPorId(Integer id);
		Turma encontrarPorId(Integer id);
		List<Turma> obterTurmas();
}
