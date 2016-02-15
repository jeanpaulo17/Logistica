package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;

public class coletorDAO {
	final ConectaBanco conexao = new ConectaBanco();
	private PreparedStatement pst; 
	private Statement stm;

	public void cadastrarColetor(String nome, String email) {
		String sql = "INSERT INTO coletor (nome, email) VALUES (?, ?);";
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, nome);
			pst.setString(2, email);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Coletor cadastrado com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar Coletor!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}
	
	public void PreencherTabela(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {
							
							conexao.rs.getObject("NOME"), conexao.rs.getObject("EMAIL")
							
					});

				} while (conexao.rs.next());
			}else{
				dados.clear();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		} finally {
			conexao.desconecta();
	}
	}
	
	public void ExcluirColetor(String nome, String email)   {

		try {
			conexao.conexao();

			pst = conexao.conn.prepareStatement("DELETE FROM coletor WHERE nome=? and email = ?");
			pst.setString(1, nome);
			pst.setString(2, email);

			
			if(pst.executeUpdate() == 1){
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} finally {
			conexao.desconecta();
			 
		}
	}

	public boolean verificaCadastroColetor(String nome, String email){

		boolean ok = false;
		conexao.conexao();

		try {

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select nome, email from coletor where nome = '"+nome+"' and email = '"+email+"'");

			if (rs.next()) {
					ok = false;
					JOptionPane.showMessageDialog(null, "Esse coletor já existe!");

			} else {
				ok = true;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "erro"+e.getMessage());

		} finally {
			conexao.desconecta();
		}
		return ok;
	}
	
}
