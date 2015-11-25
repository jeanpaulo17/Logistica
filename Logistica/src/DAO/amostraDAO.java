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
			ResultSet rs = stm.executeQuery("select empresa from proposta where numero='" + proposta + "'");

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

	public String verificaCadastroAmostra(String amostra, String proposta) {

		String status = "";

		conexao.conexao();

		try {

			Statement stm = conexao.conn.createStatement();
			ResultSet rs = stm.executeQuery("select numero, proposta from amostra where numero='" + amostra
					+ "' and proposta='" + proposta + "'");

			if (rs.next()) {

				if (rs.getString(1).equals(amostra) && rs.getString(2).equals(proposta)) {
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

	public String cadastrarAmostra(String amostra, String periodicidade, String ponto, String proposta) {

		try {

			conexao.conexao();
			PreparedStatement pst = conexao.conn
					.prepareStatement("INSERT INTO amostra (numero,periodicidade,ponto,proposta) VALUES (?,?,?,?)");
			pst.setString(1, amostra);
			pst.setString(2, periodicidade);
			pst.setString(3, ponto);
			pst.setString(4, proposta);

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

}
