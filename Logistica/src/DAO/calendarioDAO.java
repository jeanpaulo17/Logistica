package DAO;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utilitarios.ConectaBanco;
import dominio.calendario;

public class calendarioDAO {
	ConectaBanco conexao = new ConectaBanco();
	Statement stm;
	ResultSet rs;
	ArrayList<calendario> calendarios = new ArrayList<calendario>();
	String msg;
	Connection conn;

	public void PreencherTabela(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

							conexao.rs.getObject("PROPOSTA"), conexao.rs.getObject("EMPRESA"), conexao.rs.getObject("AMOSTRA"),
							conexao.rs.getObject("PERIODICIDADE"), conexao.rs.getObject("ORDEM"), conexao.rs.getObject("PARAMETRO"),
							 conexao.rs.getObject("FRASCO"), conexao.rs.getObject("VOLUME"), conexao.rs.getObject("UNIDADEMEDIDA"),
							 conexao.rs.getObject("PRESERVACAO"), conexao.rs.getObject("DATACOLETA"), conexao.rs.getObject("COLETOR"),
							 conexao.rs.getObject("OBSERVACAO")
							
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

	public void PreencherTabelaSoma(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("FRASCO"),
							conexao.rs.getObject("PRESERVACAO"),
							conexao.rs.getObject("SOMA"),
							conexao.rs.getObject("UN")

					});

				} while (conexao.rs.next());
			} else {
				dados.clear();
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void criaRelatorioPorDataColetor(String sql, String coletor) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			amostraDAO a = new amostraDAO();
			ArrayList<String> coletores = a.obterColetores();

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//QUALITYSERVER12/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/data_e_coletor.jasper",
							parametros, relatResul);

			// JasperExportManager.exportReportToPdf(jpPrint);

			JasperExportManager.exportReportToPdfFile(jpPrint, File.separator
					+ "" + File.separator + "192.168.0.8" + File.separator
					+ "informacoes" + File.separator + "SISTEMAS"
					+ File.separator + "Fichas de Coleta" + File.separator
					+ coletor + ".pdf");

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		}
	}

	public void gerarRelatorio(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/data_e_coletor_amostra.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public void gerarRelatorioPorDataColetor(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/data_e_coletor.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public void gerarRelatorioPorColetorAmostra(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/coletor_amostra.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public void gerarRelatorioPorData(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/pordata.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public void gerarRelatorioPorAmostra(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/somente_amostra.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public void gerarRelatorioPorAmostraData(String sql) {

		conexao.conexao();
		conexao.executaSQL(sql);

		try {

			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("SUBREPORT_DIR",
					"//192.168.0.8/informacoes/SISTEMAS/relatorios/");
			parametros.put("REPORT_CONNECTION", conexao.getConexao());

			JRResultSetDataSource relatResul = new JRResultSetDataSource(
					conexao.rs);

			JasperPrint jpPrint = JasperFillManager
					.fillReport(
							"//192.168.0.8/informacoes/SISTEMAS/relatorios/data_amostra.jasper",
							parametros, relatResul);

			JasperViewer jv = new JasperViewer(jpPrint, false); // cria
																// instancia
																// para
																// impressão ,
																// seta
																// exit_on_close
																// == false
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação

		} catch (JRException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao chamar relatório!" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

}
