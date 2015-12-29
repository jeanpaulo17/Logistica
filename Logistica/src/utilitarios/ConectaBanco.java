package utilitarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


	public class ConectaBanco {
			
	public Connection conexao;
	
	public Statement stm; // RESPONSAVEL POR PREPARAR E REALIZAR PESQUISAS NO BANCO DE DADOS
	
	public ResultSet rs; // RESPONSAVEL POR ARMAZENAR O RESULTADO DE UMA PESQUISA PASSADA PARA O STATEMENT
	
	private String driver = "org.postgresql.Driver"; // RESPONSAVEL POR IDENTIFICAR O SERVIÇO DE BANCO DE DADOS
	
	private String caminho = "jdbc:postgresql://192.168.0.8:5432/logistica"; // RESPONSAVEL POR SETAR O LOCAL DO BANDO DE DADOS
	
	private String login = "postgres"; // USUARIO DO POSTEGRES
	
	private String senha = "c4st3l0s#"; // SENHA DO POSTGRES
	
	public Connection conn; // RESPONSAVEL POR REALIZAR A CONEXAO COM O BANCO DE DADOS
	
		
		public void conexao	() { // METODO RESPONSAVEL POR REALIZAR A CONEXAO COM O BANCO
			
			try {
				System.setProperty("jdbc.Drivers", driver); // SETA A PROPRIEDADE DO DRIVER DE CONEXAO
			//	JOptionPane.showMessageDialog(null, "Conectado com sucesso");
				conn = DriverManager.getConnection(caminho, login, senha);
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERRO DE CONEXAO \n ERRO: "+ ex.getMessage());

				ex.printStackTrace();
			}
		}
		
		public void desconecta() {
			
			try {
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "ERRO AO DESCONECTAR \n ERRO: "+ ex.getMessage());

				ex.printStackTrace();
			}
			
			
		}
		
	
		public void executaSQL(String sql) {
			 try {
		            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
		            rs = stm.executeQuery(sql);
		        }
			
			 catch (SQLException ex){
				 System.out.println(ex.getMessage());		
				 
			 }
			 
			
		
			
		}
		
		public Connection conexaorepor(){
			try {
				conexao = DriverManager.getConnection(caminho,login,senha);
				 Connection conexaorepor = conexao;
		            return conexaorepor;
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			return conexao;
           
		}

		public Connection getConexao()  {
			try {
				conexao = DriverManager.getConnection(caminho, login, senha);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conexao;
		}

		public void setConexao(Connection conexao) {
			this.conexao = conexao;
		}

		public String getCaminho() {
			return caminho;
		}

		public void setCaminho(String caminho) {
			this.caminho = caminho;
		}
		
		
		 
		}
