package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;

public class preservacaoDAO {
	final ConectaBanco conexao = new ConectaBanco();

	public void cadastrarPreservacao(String pr) {
		String sql = "INSERT INTO preservacao (descricao) VALUES (?);";
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, pr);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Preservação cadastrada com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar preservação!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}
}
