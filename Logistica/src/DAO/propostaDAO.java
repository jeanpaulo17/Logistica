package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import face.TelaCadastroProposta;
import face.TelaEditarAmostra;
import face.TelaEditarProposta;
import face.TelaVerPropostas;

public class propostaDAO {
	
	ConectaBanco conexao = new ConectaBanco();
	
	private PreparedStatement pst; 
	private Statement stm;
	
	public propostaDAO() {

	}

	public void abrirCadastroProposta() {
		TelaCadastroProposta t = new TelaCadastroProposta();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}
	
	public void abrirPropostasCadastradas() {
		TelaVerPropostas t = new TelaVerPropostas();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}
	
	public void abrirEditarPropostas() {
		TelaEditarProposta t = new TelaEditarProposta();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}
	
	public String cadastrarProposta(String proposta, String empresa, Integer qtd) {

		
		conexao.conexao();
		String sql = "INSERT INTO proposta (numero_proposta,empresa,quantidadedeamostras) VALUES (?,?,?)";

		try {
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, proposta);
			pst.setString(2, empresa);
			pst.setInt(3, qtd);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Proposta incluida!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Proposta já existe!");
		} finally {
			conexao.desconecta();
		}

		return null;

	}
	
	public void PreencherTabelaPropostasCadastradas(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("EMPRESA"),
					conexao.rs.getObject("PROPOSTA"),
					conexao.rs.getObject("QTD")});

				} while (conexao.rs.next());
			}else{
				
				
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados.(PreencherTabelaPropostasCadastradas)" + e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}
	
	
	public void ExcluirProposta(String proposta)   {

		try {
			conexao.conexao();

			pst = conexao.conn.prepareStatement("DELETE FROM proposta WHERE numero_proposta=?");
			pst.setString(1, proposta);
			
			if(pst.executeUpdate() == 1){
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} finally {
			conexao.desconecta();
			 
		}
	}
	
	
public String editarProposta(String propostaNova, String empresa, Integer qtd, String proposta) {

		conexao.conexao();
		String sql = "update proposta set"
				+ " numero_proposta = ?, empresa = ?, quantidadedeamostras = ?"
				+ " where numero_proposta = '"+proposta+"'";

		try {
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, propostaNova);
			pst.setString(2, empresa);
			pst.setInt(3, qtd);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Proposta Editada!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro!"+e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}
	
}
