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

	public String fazerLogin(String login, String senha) {

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
					JOptionPane.showMessageDialog(null, "Logado com sucesso!");

					String permissao = rs.getString(3);

					abrirTelaInicial(permissao);

				}
				if ((rs.getString(1).equals(login))
						&& (rs.getString(2).equals(senha) && (rs.getString(3).equals("USUARIO")))) {
					JOptionPane.showMessageDialog(null, "Logado com sucesso!");

					String permissao = rs.getString(3);
					abrirTelaInicial(permissao);

				}
			} else {
				JOptionPane.showMessageDialog(null, "Login ou Senha incorretos!");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro no Banco de dados!" + ex.getMessage());
		} finally {
			conexao.desconecta();
			return null;
		}
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
