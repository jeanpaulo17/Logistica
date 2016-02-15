package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import dominio.funcionario;
import face.TelaInicialAdm;
import face.TelaLogin;

public class funcionarioDAO {
	public funcionarioDAO() {

	}

	public void abrirTelaLogin() {
		TelaLogin tl = new TelaLogin();
		tl.setVisible(true);
		tl.setLocationRelativeTo(null);
	}
	
	public String abrirTelaInicial(String permissao) {
		if (permissao.equals("ADMINISTRADOR")) {
			TelaInicialAdm adm = new TelaInicialAdm();
			adm.setLocationRelativeTo(null);
			adm.setResizable(false);
			adm.setVisible(true);
		} else {
			TelaInicialAdm usu = new TelaInicialAdm();
			usu.setLocationRelativeTo(null);
			usu.setResizable(false);
			usu.setVisible(true);
		}
		return null;
	}

	public int fazerLogin(String login, String senha) {
		int modo = 0; 
		ConectaBanco conexao = new ConectaBanco();

		try {
			conexao.conexao();
			PreparedStatement stm = conexao.conn.prepareStatement(
					"select login, senha, permissao FROM funcionario WHERE login=? and senha=?");

			stm.setString(1, login);
			stm.setString(2, senha);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {
				if ((rs.getString(1).equals(login))
						&& (rs.getString(2).equals(senha) && (rs.getString(3).equals("ADMINISTRADOR")))) {

					return modo =1;
				}
				if ((rs.getString(1).equals(login))
						&& (rs.getString(2).equals(senha) && (rs.getString(3).equals("USUARIO")))) {
					
					return modo=2;
				}
			} else {
				
				return modo=3;
			}
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro no Banco de dados!" + ex.getMessage());
		} finally {
			conexao.desconecta();
			
			
		}
		return modo;
		
	}



	public funcionario buscarFuncionario(String login) {
		final ConectaBanco conexao = new ConectaBanco();
		try {

			conexao.conexao();
			PreparedStatement stm = conexao.conn
					.prepareStatement("Select nome,setor FROM sistemadeos.funcionario WHERE login=?");

			stm.setString(1, login);

			ResultSet rs = stm.executeQuery();

			if (rs.next()) {

				String nome = rs.getString("nome");
				String setor = rs.getString("setor");

				funcionario f = new funcionario();

				f.setNome(nome);
				f.setSetor(setor);

				return f;

			}

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());

		} finally {

			conexao.desconecta();

		}
		return null;

	}
}
