package br.com.alexandrepontes.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.alexandrepontes.model.entities.Registro;
/**
 * Operações elementares de conexão com a base de dados para a entidade Registro.
 * @author alepq
 *
 */
public interface RegistroDao {
		Registro inserir(Registro r) throws SQLException;
		Registro atualizar(Registro r) throws SQLException;
		List<Registro> encontrarPorId(Integer idturma);
		
}
