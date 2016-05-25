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

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import face.TelaAdicionarMotivo;
import face.TelaCadastroAmostra;
import face.TelaEditarAmostra;
import face.TelaManutencao;
import face.TelaVerAmostra;

public class amostraDAO {
	final ConectaBanco conexao = new ConectaBanco();
	PreparedStatement pst;
	Statement stm;

	public amostraDAO() {

	}

	public void abrirCadastroAmostra() {
		TelaCadastroAmostra t = new TelaCadastroAmostra();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public void abrirManutencao() {
		TelaManutencao t;
		try {
			t = new TelaManutencao();
			t.setVisible(true);
			t.setLocationRelativeTo(null);
		} catch (SQLException e) {
		}

	}

	public void abrirAdicionarMotivo() {
		TelaAdicionarMotivo t = new TelaAdicionarMotivo();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public void abrirAmostrasCadastradas() {
		TelaVerAmostra t = new TelaVerAmostra();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}
	
	public void fecharTelaCadastroAmostra() {

		TelaCadastroAmostra t = new TelaCadastroAmostra();
		t.setDefaultCloseOperation(t.DISPOSE_ON_CLOSE);
	}

	public String buscarEmpresa(String proposta) {
		String msg = "";

		try {

			conexao.conexao();

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select empresa from proposta where idproposta='"
							+ proposta + "'");

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

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select idamostra from amostra where numero_amostra='"
							+ string + "';");

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

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select idproposta from proposta where numero_proposta='"
							+ numero_proposta + "';");

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

	public ArrayList<String> obterColetores() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT nome FROM coletor order by idcoletor ;");

			while (rs.next()) {
				dados.add(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Erro ao obter os dados. (obterColetores)"
									+ e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;

	}

	public ArrayList<String> obterStatus() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT descricao FROM status_amostra order by descricao ;");

			while (rs.next()) {
				dados.add(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterStatus)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;

	}

	public String verificaCadastroAmostra(String amostra, int proposta) {

		String status = "";

		conexao.conexao();

		try {

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select numero_amostra, proposta from amostra where numero_amostra='"
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

	public boolean verificaExistenciaAmostra(String amostra) {

		conexao.conexao();

		boolean ok = false;

		try {

			pst = conexao.conn
					.prepareStatement("Select numero_amostra from amostra where numero_amostra = ?");
			pst.setString(1, amostra);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				ok = true;
				return ok;
			} else {

				ok = false;
				return ok;

			}
		} catch (SQLException e) {
		} finally {
			conexao.desconecta();
		}
		return ok;
	}

	public String cadastrarAmostra(String amostra, String periodicidade,
			String ponto, int proposta, String endereco) {

		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("INSERT INTO amostra (numero_amostra,periodicidade,ponto,proposta, endereco) VALUES (?,?,?,?,?)");
			pst.setString(1, amostra);
			pst.setString(2, periodicidade);
			pst.setString(3, ponto);
			pst.setInt(4, proposta);
			pst.setString(5, endereco);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Amostra incluida!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}

	public void abrirEditarAmostra() {
		TelaEditarAmostra t = new TelaEditarAmostra();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
	}

	public void ExcluirAmostra(String amostra) {

		try {
			conexao.conexao();

			pst = conexao.conn
					.prepareStatement("DELETE FROM amostra WHERE numero_amostra=?");
			pst.setString(1, amostra);

			if (pst.executeUpdate() == 1) {
			}

		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} finally {
			conexao.desconecta();

		}
	}

	public String editarAmostra(String amostraNova, String amostra,
			String periodicidade, String ponto, String endereco) {

		conexao.conexao();
		String sql = "update amostra set"
				+ " numero_amostra = ?, periodicidade = ?, ponto = ?, endereco = ?"
				+ " where numero_amostra = '" + amostra + "'";

		try {
			PreparedStatement pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, amostraNova);
			pst.setString(2, periodicidade);
			pst.setString(3, ponto);
			pst.setString(4, endereco);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Amostra Editada!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}

	public void PreencherTabelaAmostrasCadastradas(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("EMPRESA"),
							conexao.rs.getObject("PROPOSTA"),
							conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PONTO"),
							conexao.rs.getObject("QUANTIDADE"),
							conexao.rs.getObject("PERIODICIDADE"),
							conexao.rs.getObject("ENDERECO") });

				} while (conexao.rs.next());
			} else {

			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados.(PreencherTabelaAmostrasCadastradas)"
							+ e.getMessage());

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

					conexao.rs.getObject("PROPOSTA"),
							conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PONTO"),
							conexao.rs.getObject("PERIODO"),
							conexao.rs.getObject("endereco")

					});

				} while (conexao.rs.next());
			} else {
				dados.clear();
			}

		} catch (SQLException e) {

		} finally {
			conexao.desconecta();
		}
	}

	public void PreencherTabelaCalendario(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("PROPOSTA"),
							conexao.rs.getObject("EMPRESA"),
							conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PERIODICIDADE"),
							conexao.rs.getObject("ORDEM"),
							conexao.rs.getObject("PARAMETRO"),
							conexao.rs.getObject("FRASCO"),
							conexao.rs.getObject("VOLUME"),
							conexao.rs.getObject("UNIDADE_MEDIDA"),
							conexao.rs.getObject("PRESERVACAO"),
							conexao.rs.getObject("DATACOLETA"),
							conexao.rs.getObject("COLETOR"), });

				} while (conexao.rs.next());
			} else {
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

					conexao.rs.getObject("PROPOSTA"),
							conexao.rs.getObject("EMPRESA"),
							conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PONTO"),
							conexao.rs.getObject("BOLETIM"),
							conexao.rs.getObject("ORDEM"),
							conexao.rs.getObject("COLETOR"),
							conexao.rs.getObject("DATACOLETA"),
							conexao.rs.getObject("STATUS")

					});

				} while (conexao.rs.next());
			} else {
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
			pst = conexao.conn.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null,
					"Tipo de Amostra cadastrada com sucesso!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao cadastrar tipo de amostra!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
	}

	public void cadastrarAmostra_OS(int idproposta, int idamostra, int qtd) {
		try {
			conexao.conexao();
			pst = conexao.conn
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
	
	public void EditarAmostra_OS(int idproposta, int idamostra, int qtd) {
		try {
			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("update amostra_os set ordem = "+qtd+"where amostra = "+idamostra+"and proposta = "+idproposta);
			int ordem = 0;

			for (int i = 1; i <= qtd; i++) {
				ordem = i;
				pst.setInt(1, ordem);
				pst.setInt(2, idamostra);
				pst.setInt(3, idproposta);
				pst.executeUpdate();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirDataColetor(int idproposta, int idamostra, int ordem,
			String datacoleta, String coletor) throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET coletor=?, datacoleta=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, coletor);
				pst.setString(2, datacoleta);
				pst.setInt(3, idproposta);
				pst.setInt(4, idamostra);
				pst.setInt(5, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirStatusData(int idproposta, int idamostra, int ordem,
			String datacoleta, String status) throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, status);
				pst.setInt(3, idproposta);
				pst.setInt(4, idamostra);
				pst.setInt(5, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirStatusColetor(int idproposta, int idamostra, int ordem,
			String coletor, String status) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET coletor=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, coletor);
			pst.setString(2, status);
			pst.setInt(3, idproposta);
			pst.setInt(4, idamostra);
			pst.setInt(5, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirStatus(int idproposta, int idamostra, int ordem,
			String status) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET status_amostra=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, status);
			pst.setInt(2, idproposta);
			pst.setInt(3, idamostra);
			pst.setInt(4, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirObservacao(int propostaObs, int amostraObs, int ordem,
			String status, String observacao) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET observacao=? where proposta=? and amostra=? and ordem=? and status_amostra=? ");

			pst.setString(1, observacao);
			pst.setInt(2, propostaObs);
			pst.setInt(3, amostraObs);
			pst.setInt(4, ordem);
			pst.setString(5, status);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirColetor(int idproposta, int idamostra, int ordem,
			String coletor) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET coletor=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, coletor);
			pst.setInt(2, idproposta);
			pst.setInt(3, idamostra);
			pst.setInt(4, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletim(int idproposta, int idamostra, int ordem,
			String boletim) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET boletim=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, boletim);
			pst.setInt(2, idproposta);
			pst.setInt(3, idamostra);
			pst.setInt(4, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimColetor(int idproposta, int idamostra, int ordem,
			String boletim, String coletor) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET boletim=?, coletor=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, boletim);
			pst.setString(2, coletor);
			pst.setInt(3, idproposta);
			pst.setInt(4, idamostra);
			pst.setInt(5, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimStatus(int idproposta, int idamostra, int ordem,
			String boletim, String status) throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET boletim=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, boletim);
			pst.setString(2, status);
			pst.setInt(3, idproposta);
			pst.setInt(4, idamostra);
			pst.setInt(5, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimColetorStatus(int idproposta, int idamostra,
			int ordem, String boletim, String coletor, String status)
			throws ParseException {
		try {

			conexao.conexao();
			pst = conexao.conn
					.prepareStatement("UPDATE amostra_os SET boletim=?, coletor=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

			pst.setString(1, boletim);
			pst.setString(2, coletor);
			pst.setString(3, status);
			pst.setInt(4, idproposta);
			pst.setInt(5, idamostra);
			pst.setInt(6, ordem);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Adicionado!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimData(int idproposta, int idamostra, int ordem,
			String datacoleta, String boletim) throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, boletim=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, boletim);
				pst.setInt(3, idproposta);
				pst.setInt(4, idamostra);
				pst.setInt(5, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimDataColetor(int idproposta, int idamostra,
			int ordem, String datacoleta, String boletim, String coletor)
			throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, boletim=?, coletor=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, boletim);
				pst.setString(3, coletor);
				pst.setInt(4, idproposta);
				pst.setInt(5, idamostra);
				pst.setInt(6, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimDataStatus(int idproposta, int idamostra,
			int ordem, String datacoleta, String boletim, String status_amostra)
			throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, boletim=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, boletim);
				pst.setString(3, status_amostra);
				pst.setInt(4, idproposta);
				pst.setInt(5, idamostra);
				pst.setInt(6, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirBoletimDataStatusColetor(int idproposta, int idamostra,
			int ordem, String datacoleta, String boletim, String status,
			String coletor) throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, boletim=?, status_amostra=?, coletor=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, boletim);
				pst.setString(3, status);
				pst.setString(4, coletor);
				pst.setInt(5, idproposta);
				pst.setInt(6, idamostra);
				pst.setInt(7, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirDataStatusColetor(int idproposta, int idamostra,
			int ordem, String datacoleta, String status, String coletor)
			throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=?, coletor=?, status_amostra=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setString(2, coletor);
				pst.setString(3, status);
				pst.setInt(4, idproposta);
				pst.setInt(5, idamostra);
				pst.setInt(6, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "ERROR" + ex.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void DefinirData(int idproposta, int idamostra, int ordem,
			String datacoleta) throws ParseException {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date teste = sdf.parse(datacoleta);
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(teste);
			int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);

			if (diaDaSemana != 1) {
				conexao.conexao();
				pst = conexao.conn
						.prepareStatement("UPDATE amostra_os SET datacoleta=? where proposta=? and amostra=? and ordem=? ");

				pst.setString(1, datacoleta);
				pst.setInt(2, idproposta);
				pst.setInt(3, idamostra);
				pst.setInt(4, ordem);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Adicionado!");
			} else {
				JOptionPane.showMessageDialog(null,
						"Você não pode agendar coletas no Domingo!");
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

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select quantidadedeamostras from proposta where idproposta="
							+ idproposta);

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

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select count(proposta) from amostra_os where proposta="
							+ idproposta);

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
		int qtdPermitida = dao
				.verificaQuantidadeDeAmostrasNaProposta(idproposta);

		if (qtdNaProposta + qtd_inserir > qtdPermitida) {
			ok = false;
		} else {
			ok = true;
		}
		return ok;

	}

	public boolean verificarDiasIguais(String data, int idproposta,
			int idamostra) {
		boolean ok = false;

		conexao.conexao();
		try {
			pst = conexao.conn
					.prepareStatement("Select * from amostra_os where datacoleta=? and  proposta=? and  amostra=?");

			pst.setString(1, data);
			pst.setInt(2, idproposta);
			pst.setInt(3, idamostra);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				ok = true;
				JOptionPane
						.showMessageDialog(null,
								"Você não pode alocar 2 ou mais amostras da mesma proposta no mesmo dia!");
				return ok;
			} else {
				ok = false;
				return ok;
			}
		} catch (SQLException ex) {
		} finally {
			conexao.desconecta();
		}
		return ok;
	}

	public void ExcluirAmostra(int proposta, String amostra) {

		try {
			conexao.conexao();

			pst = conexao.conn
					.prepareStatement("DELETE FROM amostra WHERE proposta=? and numero_amostra=?");
			pst.setInt(1, proposta);
			pst.setString(2, amostra);

			if (pst.executeUpdate() == 1) {
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
		}
	}

	public String obterObservacao(int proposta, int amostra, int ordem) {
		String obs = null;
		try {
			conexao.conexao();

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select observacao from amostra_os where proposta = "
							+ proposta
							+ " and amostra = "
							+ amostra
							+ " and ordem = " + ordem);

			if (rs.next()) {
				obs = rs.getString("observacao");
				return obs;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
		}
		return obs;
	}

	public String obterProposta(int proposta) {
		String obs = null;
		try {
			conexao.conexao();

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT numero_proposta FROM proposta where idproposta = "
							+ proposta);

			if (rs.next()) {
				obs = rs.getString("numero_proposta");
				return obs;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
		}
		return obs;
	}

	public String obterAmostra(int amostra) {
		String obs = null;
		try {
			conexao.conexao();

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT numero_amostra FROM amostra where idamostra = "
							+ amostra);

			if (rs.next()) {
				obs = rs.getString("numero_amostra");
				return obs;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
		}
		return obs;
	}

	public String obterEmpresa(int proposta) {
		String obs = null;
		try {
			conexao.conexao();

			stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT empresa FROM proposta where idproposta = "
							+ proposta);

			if (rs.next()) {
				obs = rs.getString("empresa");
				return obs;
			}

		} catch (SQLException e1) {
		} finally {
			conexao.desconecta();
		}
		return obs;
	}

}
