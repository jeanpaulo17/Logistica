package DAO;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class emailDAO {

	
	public void enviarAlerta(){
		String destinatario = null;
		
		try {
			Date data = new Date();
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			data.setDate(data.getDate() + 1);  

			//JOptionPane.showMessageDialog(null, data);

    	// cria o anexo.
    	  EmailAttachment attachment = new EmailAttachment();
    	  attachment.setPath(File.separator+""+ File.separator+"192.168.0.8"+File.separator+"informacoes"+File.separator+"SISTEMAS"+File.separator+"com.pdf");  //caminho do anexo
    	  attachment.setDisposition(EmailAttachment.ATTACHMENT);
    	  attachment.setDescription("Roteiro do dia");
    	 
    	  attachment.setName("ROTEIRO ("+formatador.format(data)+").pdf");
    	
    	  // Cria a mensagem de e-mail.
    	  MultiPartEmail email = new MultiPartEmail();
    	  email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail  
    	  email.setAuthentication("logistica.qualylab@gmail.com", "ql02log06");  
    	  email.setSmtpPort(587);
    	  email.setSSL(true);
    	  email.addTo("ti@grupoqualityambiental.com.br", "TESTE"); //destinatário  
    	  email.setFrom("logistica.qualylab@gmail.com"); // remetente  
    	  email.setSubject("ASSUNTO: "); // assunto do e-mail  
		 //conteudo do e-mail abaixo
    	  email.setMsg("CORPO DO EMAIL !");
    	  
    	  email.attach(attachment);
    	  // adiciona o anexo à mensagem
    	  email.send();  // enviar email  
    	  
    	 
        
	}catch(EmailException ex){
		JOptionPane.showMessageDialog(null, "Erro"+ex.getMessage());
			}
	} 
}
