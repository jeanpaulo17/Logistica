package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import dominio.calendario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import utilitarios.ConectaBanco;

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
							 conexao.rs.getObject("ENDERECO")
							
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

							conexao.rs.getObject("FRASCO"), conexao.rs.getObject("PRESERVACAO"), conexao.rs.getObject("SOMA"),
							conexao.rs.getObject("UN")
							
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
	
	
	public void gerarRelatorioColeta(String sql){
		
		Connection c = conexao.getConexao();
 	 		conexao.conexao();
 			conexao.executaSQL(sql);
 			
 			HashMap parametros = new HashMap();  
 			parametros.put("SUBREPORT_DIR" , "//192.168.0.8/informacoes/SISTEMAS/relatorios/") ;  
 			parametros.put("REPORT_CONNECTION", c) ;     
 			
 			
 	 		JRResultSetDataSource relatResul = new JRResultSetDataSource(conexao.rs);
 	 		try {
 	 		
 	 		JasperPrint jpPrint = JasperFillManager.fillReport("//192.168.0.8/informacoes/SISTEMAS/relatorios/coleta.jasper",parametros, relatResul);
			JasperViewer jv = new JasperViewer(jpPrint,false); // cria instancia para impressão , seta exit_on_close == false 
			jv.setVisible(true); // chama relatorio para visualização
			jv.toFront(); // relatorio na frente da aplicação
			
 		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, "Erro ao chamar relatório!"+e.getMessage());
		}
 		finally {
 			conexao.desconecta();
 		}
		     
			        
		     
		     
	}
	
	
}
