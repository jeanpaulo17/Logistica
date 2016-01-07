package controle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utilitarios.ConectaBanco;
import DAO.amostraDAO;
import DAO.calendarioDAO;
import DAO.emailDAO;
import face.TelaInicialAdm;

public class programa {

	public static void main(String[] args) {
		TelaInicialAdm t = new TelaInicialAdm();
		t.setVisible(true);
		t.setLocationRelativeTo(null);
		
		ConectaBanco conexao = new ConectaBanco();
		emailDAO c = new emailDAO();
		c.enviarAlerta();
		
		
		
	//	calendarioDAO cc = new calendarioDAO();
	//	amostraDAO a = new amostraDAO();
	//	ArrayList<String> coletores = a.obterColetores();

		
		
	//	for(int i = 1; i<coletores.size();i++){
		
	//		cc.criaRelatorioPorDataColetor("select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
	//			+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
	//			+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co"
	//			+ " where aos.coletor = '"+coletores.get(i)+"' AND aos.datacoleta = '08/01/2016'"
	//			+ " and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome", coletores.get(i));
	}
	}



