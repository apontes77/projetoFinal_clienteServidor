package br.com.alexandrepontes.model.dao;

import java.util.List;

import br.com.alexandrepontes.model.entities.BuscaGeral;
/**
 * Operações elementares de conexão com a base de dados para a entidade BuscaGeral.
 * @author alepq
 *
 */
public interface BuscaGeralDao {
		List<BuscaGeral> cadastroPorMatriculaDeAluno(Integer mat);
		List<BuscaGeral> cadastroPorNomeDeAluno(String nome);
		List<BuscaGeral> obterTurmasComDisciplinas(String nometurma);
}
