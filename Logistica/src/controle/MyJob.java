package controle;

import java.util.ArrayList;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import DAO.amostraDAO;
import DAO.calendarioDAO;
import DAO.emailDAO;

public class MyJob implements Job{
	
	public MyJob(){
		
	}
	  public void execute(JobExecutionContext context) {
		  
		  	emailDAO email = new emailDAO();
			calendarioDAO cc = new calendarioDAO();
			amostraDAO a = new amostraDAO();
			ArrayList<String> coletores = a.obterColetores();
			
			
			Calendar c = Calendar.getInstance();
	        int dia = c.get(Calendar.DAY_OF_MONTH);
	        c.set(Calendar.DAY_OF_MONTH, dia + 1);
	        dia = c.get(Calendar.DAY_OF_MONTH);

			for(int i = 1; i<coletores.size();i++){
			
				cc.criaRelatorioPorDataColetor("select  pr.numero_proposta as PROPOSTA, pr.empresa as EMPRESA, am.numero_amostra as AMOSTRA,"
					+ " am.periodicidade as PERIODICIDADE,aos.datacoleta as DATACOLETA, aos.coletor as COLETOR"
					+ " from proposta as pr, amostra as am, amostra_os as aos, coletor as co"
					+ " where aos.coletor = '"+coletores.get(i)+"' AND aos.datacoleta = '"+dia+"'"
					+ " and pr.idproposta = aos.proposta and aos.amostra = am.idamostra and aos.coletor = co.nome", coletores.get(i));
	}
			
			email.enviarAlerta();
			
		  }   
}
