package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import face.TelaCadastroProposta;

public class propostaDAO {

	public propostaDAO() {

	}

	public void abrirCadastroProposta() {
		TelaCadastroProposta t = new TelaCadastroProposta();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public String cadastrarProposta(String proposta, String empresa) {

		ConectaBanco conexao = new ConectaBanco();
		conexao.conexao();
		String sql = "INSERT INTO proposta (numero,empresa) VALUES (?,?)";

		try {
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, proposta);
			pst.setString(2, empresa);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Proposta incluida!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Proposta já existe!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}

}
