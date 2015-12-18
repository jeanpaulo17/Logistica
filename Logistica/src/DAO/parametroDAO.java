package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import face.TelaVerLegislacao;
import utilitarios.ConectaBanco;

public class parametroDAO {
	public parametroDAO() {

	}

	final ConectaBanco conexao = new ConectaBanco();
	
	public void abrirTelaVerLegislacao(){
		TelaVerLegislacao t = new TelaVerLegislacao();
		t.setVisible(true);
		t.setLocationRelativeTo(null);;
	}

	public ArrayList<String> obterDados() {

		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT codigo, descricao FROM parametro ORDER BY descricao;");

			while (rs.next()) {
				dados.add(rs.getString(2));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterDados)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	
	

	public ArrayList<String> obterLegislacao() {

		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT descricao FROM legislacao");

			while (rs.next()) {
				dados.add(rs.getString(1));
			}
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null,	"ERRO ao buscar Legisla��o"+ex.getClass());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}
	
	public ArrayList<String> obterAmostra(String proposta) {

		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT numero_amostra FROM amostra where proposta='"+proposta+"'");

			while (rs.next()) {
				dados.add(rs.getString(1));
			}
		} catch (org.postgresql.util.PSQLException e) {
			JOptionPane.showMessageDialog(null,	"Proposta N�o Existe");
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,	"ERRO!"+ex.getClass());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public int obterCodigoParametro(String descricao) {

		conexao.conexao();
		int dados = 0;

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("SELECT idparametro FROM parametro where descricao = '"
							+ descricao + "';");

			while (rs.next()) {
				dados = rs.getInt(1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterCodigoParametro)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public String verificaCadastroParametro(int amostra, int parametro,
			int proposta) {

		String status = "";

		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select amostra, parametro from amostra_parametro where amostra='"
							+ amostra
							+ "' and parametro='"
							+ parametro
							+ "' and proposta='" + proposta + "'");

			if (rs.next()) {

				status = "false";
				return status;
			} else {
				status = "true";
				return status;
			}

		} catch (SQLException e) {

		} finally {
			conexao.desconecta();
			return status;

		}

	}

	public String cadastrarParametro_Amostra(int amostra, int proposta,
			int parametro) {

		
		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("INSERT INTO amostra_parametro (amostra,proposta,parametro) VALUES (?,?,?)");
			pst.setInt(1, amostra);
			pst.setInt(2, proposta);
			pst.setInt(3, parametro);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Par�metro incluido!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (CADASTRAR PARAMETRO_AMOSTRA)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}
	
	public String cadastrarLegislacao(String legislacao) {

		
		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("INSERT INTO legislacao (descricao) VALUES (?)");
			pst.setString(1, legislacao);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Legisla��o incluida!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}
	

	public int obterIdFrasco(String frasco) {

		int idfrasco = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("select id_frasco from frasco where descricao = ? ;");
			pst.setString(1, frasco);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				idfrasco = rs.getInt(1);

				return idfrasco;
			}
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (OBTER ID FRASCO)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
		return idfrasco;
	}

	public int obterIdPreservacao(String preservacao) {

		int idPreservacao = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("select id_preservacao from preservacao where descricao = ?");
			pst.setString(1, preservacao);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				idPreservacao = rs.getInt(1);

			return idPreservacao;
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (OBTER ID PRESERVACAO)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return idPreservacao;
	}
	
	public int obterIdLegislacao(String legislacao) {

		int idlegislacao = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("select idlegislacao from legislacao where descricao = ?");
			pst.setString(1, legislacao);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				idlegislacao = rs.getInt(1);

			return idlegislacao;
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (OBTER ID LEGISLACAO)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return idlegislacao;
	}


	public int obterIdVolume(double volume) {

		int idVolume = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("select id_volume from volume where volume = ?");
			pst.setDouble(1, volume);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				idVolume = rs.getInt(1);

			return idVolume;
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (OBTER ID VOLUME)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
		return idVolume;
	}

	public int obterIdTipoAmostra(String tipoAmostra) {

		int idTipoAmostra = 0;
		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("select idtipoAmostra from tipoAmostra where descricao = ?");
			pst.setString(1, tipoAmostra);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				idTipoAmostra = rs.getInt(1);
				return idTipoAmostra;
			}
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (OBTER TIPO AMOSTRA)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}
		return idTipoAmostra;
	}

	public void cadastrarParametro(String parametro, String codigo,
			String frasco, double volume, String preservacao, String tipoAmostra) {

		int frasco1 = obterIdFrasco(frasco);
		int volume1 = obterIdVolume(volume);
		int preservacao1 = obterIdPreservacao(preservacao);
		int tipoAmostra1 = obterIdTipoAmostra(tipoAmostra);

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("insert into parametro(descricao, frasco, codigo, preservacao, volume, tipoAmostra) values (?, ?, ?, ?, ?, ?)");
			pst.setString(1, parametro);
			pst.setInt(2, frasco1);
			pst.setString(3, codigo);
			pst.setInt(4, preservacao1);
			pst.setInt(5, volume1);
			pst.setInt(6, tipoAmostra1);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Par�metro incluido!");

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao cadastrar Parametro" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}
	
	public void cadastrarParametroLegislacao(String legislacao, String parametro) {

		int legislacao1 = obterIdLegislacao(legislacao);
		int parametro1 = obterCodigoParametro(parametro);

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("insert into legislacao_parametro(legislacao, parametro)values (?, ?)");

			pst.setInt(1, legislacao1);
			pst.setInt(2, parametro1);
		

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Par�metro incluido!");

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao cadastrar Parametro" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

	}

	public ArrayList<String> obterListaDeFrasco() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select descricao FROM frasco ");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterListaDeFrasco)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDePreservacao() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select descricao FROM preservacao ");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterListaDePreservacao)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDeVolumeML() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select volume FROM volume where id_unidade_medida=3");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterListaDeVolumeML)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDeVolumeND() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select volume FROM volume where id_unidade_medida=1");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterListaDeVolumeND)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDeVolumeG() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select volume FROM volume where id_unidade_medida=2");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados.(obterListaDeVolumeG)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDeTipoAmostra() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm
					.executeQuery("select descricao FROM tipoAmostra order by idtipoamostra");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados. (obterListaDeTipoAmostra)" + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public ArrayList<String> obterListaDeCodigo() {
		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select codigo FROM parametro ");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,	"Erro ao obter os dados. (obterListaDeCodigo)");
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public void PreencherTabela(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("proposta"),
							conexao.rs.getObject("empresa"),
							conexao.rs.getObject("amostra"),
							conexao.rs.getObject("ponto"),
							conexao.rs.getObject("parametro") });

				} while (conexao.rs.next());
			}else{
				
				
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados.(PreencherTabela1)" + e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}
	
	public void PreencherTabelaLegislacao(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

					conexao.rs.getObject("descricao") });

				} while (conexao.rs.next());
			}else{
				
				
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao obter os dados.(PreencherTabelaLegislacao)" + e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void PreencherTabelaParametro(String sql, ArrayList<Object[]> dados) {

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
							conexao.rs.getObject("PARAMETRO"),
							conexao.rs.getObject("FRASCO"),
							conexao.rs.getObject("PRESERVACAO"),
							conexao.rs.getObject("VOLUME"),
							conexao.rs.getObject("UNI"),
							conexao.rs.getObject("TIPOAMOSTRA")

					});

				} while (conexao.rs.next());
			}else{
				dados.clear();
			}
				

		} catch (SQLException e) {
		} catch (ArrayIndexOutOfBoundsException array) {
		} catch (ClassCastException ex) {
		}
		finally {
			conexao.desconecta();
		}
	}

}