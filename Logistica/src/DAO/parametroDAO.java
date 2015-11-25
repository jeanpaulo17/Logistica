package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;

public class parametroDAO {
	public parametroDAO() {

	}

	final ConectaBanco conexao = new ConectaBanco();

	public ArrayList<String> obterDados() {

		conexao.conexao();
		ArrayList<String> dados = new ArrayList<String>();

		try {
			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("SELECT codigo, descricao FROM parametro ORDER BY descricao;");

			while (rs.next()) {
				dados.add(rs.getString(2));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("SELECT numero FROM amostra where proposta='" + proposta + "'");

			while (rs.next()) {
				dados.add(rs.getString(1));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("SELECT idparametro FROM parametro where descricao = '" + descricao + "';");

			while (rs.next()) {
				dados = rs.getInt(1);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return dados;
	}

	public String verificaCadastroParametro(String amostra, int parametro, String proposta) {

		String status = "";

		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select amostra, parametro from amostra_parametro where amostra='" + amostra
					+ "' and parametro='" + parametro + "' and proposta='" + proposta + "'");

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

	public String cadastrarParametro_Amostra(String amostra, String proposta, int parametro) {

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("INSERT INTO amostra_parametro (amostra,proposta,parametro) VALUES (?,?,?)");
			pst.setString(1, amostra);
			pst.setString(2, proposta);
			pst.setInt(3, parametro);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Parâmetro incluido!");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return null;

	}

	public int obterIdFrasco(String frasco) {

		int idfrasco = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement("select id_frasco from frasco where descricao = ? ;");
			pst.setString(1, frasco);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				idfrasco = rs.getInt(1);

				return idfrasco;
			}
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
		} finally {
			conexao.desconecta();
		}

		return idPreservacao;
	}

	public int obterIdVolume(double volume) {

		int idVolume = 0;

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement("select id_volume from volume where volume = ?");
			pst.setDouble(1, volume);
			ResultSet rs = pst.executeQuery();
			if (rs.next())
				idVolume = rs.getInt(1);

			return idVolume;
		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
		} finally {
			conexao.desconecta();
		}
		return idTipoAmostra;
	}

	public void cadastrarParametro(String parametro, String codigo, String frasco, double volume, String preservacao,
			String tipoAmostra) {

		int frasco1 = obterIdFrasco(frasco);
		int volume1 = obterIdVolume(volume);
		int preservacao1 = obterIdPreservacao(preservacao);
		int tipoAmostra1 = obterIdTipoAmostra(tipoAmostra);

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn.prepareStatement(
					"insert into parametro(descricao, frasco, codigo, preservacao, volume, tipoAmostra) values (?, ?, ?, ?, ?, ?)");
			pst.setString(1, parametro);
			pst.setInt(2, frasco1);
			pst.setString(3, codigo);
			pst.setInt(4, preservacao1);
			pst.setInt(5, volume1);
			pst.setInt(6, tipoAmostra1);

			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Parâmetro incluido!");

		}

		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("select descricao FROM preservacao ");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("select volume FROM volume where id_unidade_medida=3");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("select volume FROM volume where id_unidade_medida=1");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("select volume FROM volume where id_unidade_medida=2");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			ResultSet rs = stm.executeQuery("select descricao FROM tipoAmostra order by idtipoamostra");

			while (rs.next()) {
				String opcao = rs.getString(1);
				dados.add(opcao);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());
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

							conexao.rs.getObject("proposta"), conexao.rs.getObject("empresa"),
							conexao.rs.getObject("amostra"), conexao.rs.getObject("ponto"),
							conexao.rs.getObject("parametro") });

				} while (conexao.rs.next());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

	public void PreencherTabelaParametro(String sql, ArrayList dados) {

		conexao.conexao();
		conexao.executaSQL(sql);
		try {

			if (conexao.rs.first()) {
				dados.clear();
				do {
					dados.add(new Object[] {

							conexao.rs.getObject("PROPOSTA"), conexao.rs.getObject("EMPRESA"),
							conexao.rs.getObject("AMOSTRA"), conexao.rs.getObject("PONTO"),
							conexao.rs.getObject("PARAMETRO"), conexao.rs.getObject("FRASCO"),
							conexao.rs.getObject("PRESERVACAO"), conexao.rs.getObject("VOLUME"),
							conexao.rs.getObject("UNIDADEMEDIDA"), conexao.rs.getObject("TIPOAMOSTRA")

					});

				} while (conexao.rs.next());
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao obter os dados." + e.getMessage());

		} finally {
			conexao.desconecta();
		}
	}

}