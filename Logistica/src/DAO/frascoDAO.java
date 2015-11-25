package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;

public class frascoDAO {
	final ConectaBanco conexao = new ConectaBanco();

	public void cadastrarFrasco(String frasco) {
		String sql = "INSERT INTO frasco (descricao) VALUES (?);";
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, frasco);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Frasco cadastrado com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar Frasco!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}

}
