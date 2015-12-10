package DAO;

import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import face.TelaCadastroAmostra;
import face.TelaCadastroProposta;
import face.TelaManutencao;
import utilitarios.ConectaBanco;
import utilitarios.ModeloTable;

public class amostraDAO {
	final ConectaBanco conexao = new ConectaBanco();

	public amostraDAO() {

	}

	public void abrirCadastroAmostra() {
		TelaCadastroAmostra t = new TelaCadastroAmostra();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public void abrirManutencao() {
		TelaManutencao t = new TelaManutencao();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public String buscarEmpresa(String proposta) {
		String msg = "";

		try {

			conexao.conexao();

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select empresa from proposta where idproposta='" + proposta + "'");

			if (rs.next()) {
				msg = rs.getString(1);
				return msg;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
			return msg;
		}
	}
	
	public String buscarIdAmostra(String string) {
		String msg = "";

		try {

			conexao.conexao();

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select idamostra from amostra where numero_amostra='"+string+"';");

			if (rs.next()) {
				msg = rs.getString(1);
				return msg;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
			return msg;
		}
	}
	
	
	public String buscarIdProposta(String numero_proposta) {
		String msg = "";

		try {

			conexao.conexao();

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select idproposta from proposta where numero_proposta='"+numero_proposta+"';");

			if (rs.next()) {
				msg = rs.getString(1);
				return msg;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
			return msg;
		}
	}
	

	public String verificaCadastroAmostra(String amostra, int proposta) {

		String status = "";

		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select numero_amostra, proposta from amostra where numero_amostra='" + amostra
					+ "' and proposta='" + proposta + "'");

			if (rs.next()) {

				if (rs.getString(1).equals(amostra) && rs.getInt(2) == proposta) {
					status = "false";
					return status;
				} else {
					status = "true";
					return status;
				}
			}

		} catch (SQLException e) {

		} finally {
			conexao.desconecta();
			return status;

		}

	}

	public String cadastrarAmostra(String amostra, String periodicidade, String ponto, int proposta) {

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
			.prepareStatement("INSERT INTO amostra (numero_amostra,periodicidade,ponto,proposta) VALUES (?,?,?,?)");
			pst.setString(1, amostra);
			pst.setString(2, periodicidade);
			pst.setString(3, ponto);
			pst.setInt(4, proposta);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Amostra incluida!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}

	public void PreencherTabela(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

							conexao.rs.getObject("PROPOSTA"), conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PONTO"), conexao.rs.getObject("PERIODO"),

					});

				} while (conexao.rs.next());
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void cadastrarTipoAmostra(String tipo) {

		String sql = "INSERT INTO tipoamostra (descricao) VALUES (?);";
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tipo de Amostra cadastrada com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar tipo de amostra!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}
	
	
	public void cadastrarAmostra_OS(int idproposta, int idamostra, int qtd){
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement("INSERT INTO amostra_os (proposta, amostra, ordem) VALUES (?,?,?)");
			int ordem = 0;
			
		for(int i=1; i<=qtd ;i++){
			ordem = i;
			pst.setInt(1, idproposta);
			pst.setInt(2, idamostra);
			pst.setInt(3, ordem);
			pst.executeUpdate();
		}
	}catch(SQLException ex){
		JOptionPane.showMessageDialog(null, "ERROR"+ex.getMessage());
		
	}finally{
		conexao.desconecta();
	}
	}
	
	public int verificaQuantidadeDeAmostrasNaProposta(int idproposta){
		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select quantidadedeamostras from proposta where idproposta="+idproposta);
			
			if(rs.next()){
				return rs.getInt(1);
			}
		
	}catch(SQLException ex){
		JOptionPane.showMessageDialog(null, "ERROR"+ex.getClass());
	}finally{
		conexao.desconecta();
	}
		return idproposta;
} 
	
	public int contaQuantidadeDeAmostrasNaProposta(int idproposta){
		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select count(proposta) from amostra_os where proposta="+idproposta);
			
			if(rs.next()){
				return rs.getInt(1);
			}
		
	}catch(SQLException ex){
		JOptionPane.showMessageDialog(null, "ERROR"+ex.getClass());
	}finally{
		conexao.desconecta();
	}
		return idproposta;
} 
	
	
	public boolean verificaQtdAmostras(int qtd_inserir, int idproposta){
		boolean ok;
		amostraDAO dao = new amostraDAO();
		
		int qtdNaProposta = dao.contaQuantidadeDeAmostrasNaProposta(idproposta);
		int qtdPermitida = dao.verificaQuantidadeDeAmostrasNaProposta(idproposta);
		
		if(qtdNaProposta + qtd_inserir > qtdPermitida){
			ok = false;
		}else{
			ok = true;
		}
		return ok;
		
	}
	
}
