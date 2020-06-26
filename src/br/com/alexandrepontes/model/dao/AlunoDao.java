package br.com.alexandrepontes.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.alexandrepontes.model.entities.Aluno;
/**
 * Operações elementares de conexão com a base de dados para a entidade Aluno.
 * @author alepq
 *
 */
public interface AlunoDao {
	Aluno inserir(Aluno a) throws SQLException;
	Aluno atualizar(Aluno a);
	boolean excluirPorId(Integer i);
	boolean excluirPorNome(String nomealuno);
	Aluno encontrarPorId(Aluno a);
	List<Aluno> obterAlunos(Integer i);
}