package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import face.TelaCadastroAmostra;
import face.TelaManutencao;

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
			ResultSet rs = stm.executeQuery("select idamostra from amostra where numero_amostra='" + string + "';");

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
			ResultSet rs = stm
					.executeQuery("select idproposta from proposta where numero_proposta='" + numero_proposta + "';");

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
			ResultSet rs = stm.executeQuery("select numero_amostra, proposta from amostra where numero_amostra='"
					+ amostra + "' and proposta='" + proposta + "'");

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
	
public boolean verificaExistenciaAmostra(String amostra){
		
		conexao.conexao();
		
		boolean ok= false;
		
		try {
			
			PreparedStatement pst = conexao.conn.prepareStatement("Select numero_amostra from amostra where numero_amostra = ?");
			pst.setString(1, amostra);
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				ok = true;
			return ok;
			}
			else{
				ok = false;
				return ok;
			}
		} catch (SQLException e) {
		}
		finally{
			conexao.desconecta();
		}
		return ok;
	}

	public String cadastrarAmostra(String amostra, String periodicidade, String ponto, int proposta) {

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(
					"INSERT INTO amostra (numero_amostra,periodicidade,ponto,proposta) VALUES (?,?,?,?)");
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
			}else{
				dados.clear();
			}

		} catch (SQLException e) {

		} finally {
			conexao.desconecta();
	}
	}
	public void PreencherTabelaColeta(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

							conexao.rs.getObject("PROPOSTA"), conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("ORDEM"), conexao.rs.getObject("COLETOR"),
							conexao.rs.getObject("DATACOLETA"),

					});

				} while (conexao.rs.next());
			}else{
				dados.clear();
			}

		} catch (SQLException e) {

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

	public void cadastrarAmostra_OS(int idproposta, int idamostra, int qtd) {
		try {
			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("INSERT INTO amostra_os (proposta, amostra, ordem) VALUES (?,?,?)");
			int ordem = 0;

			for (int i = 1; i <= qtd; i++) {
				ordem = i;
				pst.setInt(1, idproposta);
				pst.setInt(2, idamostra);
				pst.setInt(3, ordem);
				pst.executeUpdate();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}
	
	
	public void DefinirDataColetor(int idproposta, int idamostra, int ordem, String datacoleta, String coletor) throws ParseException {
		try {
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	         Date teste = sdf.parse(datacoleta);
	         GregorianCalendar gc = new GregorianCalendar();
	         gc.setTime(teste);
	         int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
	            
	         
	        if(diaDaSemana != 1){ 
			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement("UPDATE amostra_os SET coletor=?, datacoleta=? where proposta=? and amostra=? and ordem=? ");
			
			pst.setString(1, coletor);
			pst.setString(2, datacoleta);
			pst.setInt(3, idproposta);
			pst.setInt(4, idamostra);
			pst.setInt(5, ordem);
			
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");
	        }else{
	        	JOptionPane.showMessageDialog(null, "Voc� n�o pode agendar coletas no Domingo!");
	        }
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public int verificaQuantidadeDeAmostrasNaProposta(int idproposta) {
		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select quantidadedeamostras from proposta where idproposta=" + idproposta);

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getClass());
		} finally {
			conexao.desconecta();
		}
		return idproposta;
	}

	public int contaQuantidadeDeAmostrasNaProposta(int idproposta) {
		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select count(proposta) from amostra_os where proposta=" + idproposta);

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getClass());
		} finally {
			conexao.desconecta();
		}
		return idproposta;
	}

	public boolean verificaQtdAmostras(int qtd_inserir, int idproposta) {
		boolean ok;
		amostraDAO dao = new amostraDAO();

		int qtdNaProposta = dao.contaQuantidadeDeAmostrasNaProposta(idproposta);
		int qtdPermitida = dao.verificaQuantidadeDeAmostrasNaProposta(idproposta);

		if (qtdNaProposta + qtd_inserir > qtdPermitida) {
			ok = false;
		} else {
			ok = true;
		}
		return ok;

	}

}
