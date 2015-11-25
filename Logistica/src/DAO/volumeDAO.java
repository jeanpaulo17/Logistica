package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;

public class volumeDAO {

	final ConectaBanco conexao = new ConectaBanco();

	public void cadastrarVolume(double volume) {

		String sql = "INSERT INTO volume (volume, id_unidade_medida) VALUES (?,?);";

		try {

			conexao.conexao();

			PreparedStatement pst = conexao.conn.prepareStatement(sql);

			PreparedStatement pst2 = conexao.conn.prepareStatement(sql);

			pst.setDouble(1, volume);
			pst.setInt(2, 2);
			pst.executeUpdate();

			pst2.setDouble(1, volume);
			pst2.setInt(2, 3);
			pst2.executeUpdate();

			JOptionPane.showMessageDialog(null, "Volume cadastrado com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar Frasco!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}
}
