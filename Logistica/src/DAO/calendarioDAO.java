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
	String msg ;
	ResultSet rs;
	ArrayList<calendario> calendarios = new ArrayList<calendario>();
	
	public ArrayList<calendario> obterCalendarioPorColetor(String coletor){
		conexao.conexao();
		
		try{
		stm = conexao.conn.createStatement();
		String sql = "select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
				+ " am.periodicidade as PERIODICIDADE, aos.ordem as ORDEM,  pa.descricao as PARAMETRO, fr.descricao as FRASCO, vol.volume as VOLUME,"
				+ " un.unidade_medida as UNIDADEMEDIDA, pre.descricao as PRESERVACAO, aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
				+ " from proposta as pr, amostra as am, amostra_os as aos, parametro as pa, frasco as fr, volume as vol,"
				+ " unidade_medida as un, preservacao as pre, coletor as co, amostra_parametro as ap"
				+ " where aos.coletor='"+coletor+"' and pr.idproposta = aos.proposta and aos.proposta = ap.proposta"
				+ " and aos.amostra = am.idamostra and aos.amostra =ap.amostra and pa.idparametro = ap.parametro"
				+ " and fr.id_frasco = pa.frasco and vol.id_volume = pa.volume and pre.id_preservacao = pa.preservacao"
				+ " and un.id_unidade_medida = vol.id_unidade_medida and aos.coletor = co.nome";
		
		rs = stm.executeQuery(sql);
		
		while(rs.next()){
		
		calendario c = new calendario(rs.getString("PROPOSTA"), rs.getString("EMPRESA"),rs.getString("AMOSTRA"),
		rs.getString("PERIODICIDADE"),rs.getInt("ORDEM"),rs.getString("PARAMETRO"),rs.getString("FRASCO"),
		rs.getDouble("VOLUME"),rs.getString("UNIDADEMEDIDA"),rs.getString("PRESERVACAO"),rs.getString("DATACOLETA"),
		rs.getString("COLETOR"));
		
		calendarios.add(c);
		
		}
		
		}
		
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "ERRO"+ex.getMessage());
			
		}finally{
		conexao.desconecta();
		
		}
		
		return calendarios;
		
	}

}
