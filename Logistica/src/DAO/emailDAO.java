package DAO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

import utilitarios.ConectaBanco;

public class emailDAO {
ConectaBanco conexao = new ConectaBanco();
	
	public void enviarAlerta(){
		String destinatario = null;
		
			calendarioDAO cc = new calendarioDAO();
			amostraDAO a = new amostraDAO();
			ArrayList<String> coletores = a.obterColetores();

			for(int i=1; i<coletores.size();i++){
			
			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			data.setDate(data.getDate() + 1);

    	// cria o anexo.
    	  EmailAttachment attachment = new EmailAttachment();
    	  attachment.setPath(File.separator+""+ File.separator+"192.168.0.8"+File.separator+"informacoes"+File.separator+"SISTEMAS"+File.separator+"Fichas de Coleta"+File.separator+coletores.get(i)+".pdf");  //caminho do anexo
    	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
    	  attachment.setDescription("Roteiro do dia");
    	 
    	  attachment.setName("ROTEIRO "+coletores.get(i)+ " " + (formatador.format(data))+".pdf");
    
    	  // Cria a mensagem de e-mail.
    	  MultiPartEmail email = new MultiPartEmail();
    	  email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
    	  email.setAuthentication("logistica.qualylab@gmail.com", "ql02log06");  
    	  email.setSmtpPort(587);
    	  email.setSSL(true);
    	  
    	  
    	  try {
			email.addTo("ti@grupoqualityambiental.com.br", "Ficha de Coleta");
			//destinatário  
	    	  email.setFrom("logistica.qualylab@gmail.com; jeanpaulo@grupoqualityambiental.com.br"); // remetente 
	    	  email.setSubject("Ficha de Coleta: "+coletores.get(i)); // assunto do e-mail  
			 //conteudo do e-mail abaixo
	    	  email.setMsg("Email automático do Sistema não responda.");
	    	  
	    	  email.attach(attachment);
	    	  // adiciona o anexo à mensagem
	    	  email.send();  // enviar email
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	} 
	}
	
	public void enviarEmail(String coletor, String data){
		String destinatario = null;
		
			calendarioDAO cc = new calendarioDAO();
			amostraDAO a = new amostraDAO();

			
			
    	// cria o anexo.
    	  EmailAttachment attachment = new EmailAttachment();
    	  attachment.setPath(File.separator+""+ File.separator+"192.168.0.8"+File.separator+"informacoes"+File.separator+"SISTEMAS"+File.separator+"Fichas de Coleta"+File.separator+coletor+".pdf");  //caminho do anexo
    	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
    	  attachment.setDescription("Roteiro do dia");
    	 
    	  attachment.setName("ROTEIRO "+coletor+ " " + data+".pdf");
    
    	  // Cria a mensagem de e-mail.
    	  MultiPartEmail email = new MultiPartEmail();
    	  email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
    	  email.setAuthentication("logistica.qualylab@gmail.com", "ql02log06");  
    	  email.setSmtpPort(587);
    	  email.setSSL(true);
    	  
    	  
    	  try {
			email.addTo("ti@grupoqualityambiental.com.br", "Ficha de Coleta");
			//destinatário  
	    	  email.setFrom("logistica.qualylab@gmail.com"); // remetente
	    	//  email.setFrom("jeanpaulo@grupoqualityambiental.com.br");
	    	  email.addBcc("jeanpaulo@grupoqualityambiental.com.br");
	    	  email.setSubject("Ficha de Coleta: "+coletor); // assunto do e-mail  
			 //conteudo do e-mail abaixo
	    	  email.setMsg("Email automático do Sistema não responda.");
	    	  
	    	  email.attach(attachment);
	    	  // adiciona o anexo à mensagem
	    	  email.send();  // enviar email
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	} 
}
