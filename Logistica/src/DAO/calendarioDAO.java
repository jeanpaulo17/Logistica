package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import utilitarios.ConectaBanco;
import dominio.calendario;

public class calendarioDAO {
	ConectaBanco conexao = new ConectaBanco();
	Statement stm;
	ResultSet rs;
	ArrayList<calendario> calendarios = new ArrayList<calendario>();
	String msg;
	
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
	
}
