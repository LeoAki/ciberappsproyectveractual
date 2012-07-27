package com.monkar.email;


import java.util.Properties;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
  
public class enviaMail {  
    private final static Properties properties = new Properties();  
  
    private static Session session;  
  
    private static void init() {  
  
        properties.put("mail.smtp.host", "smtp.gmail.com");  
        properties.put("mail.smtp.starttls.enable", "true");  
        properties.put("mail.smtp.port",25);  
        properties.put("mail.smtp.mail.sender","soporteciberapps@gmail.com");  
        properties.put("mail.smtp.user", "soporteciberapps");  
        properties.put("mail.smtp.auth", "true");  
  
        session = Session.getDefaultInstance(properties);  
    }  
  
    public static void sendEmail(String titulo,String mensaje) throws Exception{  
  
        init();  
        try{  
            MimeMessage message = new MimeMessage(session);  
            message.setFrom(new InternetAddress((String)properties.get("mail.smtp.mail.sender")));  
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("soporteciberapps@gmail.com"));  
            message.setSubject(titulo);  
            message.setText(mensaje);  
            Transport t = session.getTransport("smtp");  
            t.connect((String)properties.get("mail.smtp.user"), "soporte123");  
            t.sendMessage(message, message.getAllRecipients());  
            t.close();  
        }catch (MessagingException me){  
                        //Aqui se deberia o mostrar un mensaje de error o en lugar  
                        //de no hacer nada con la excepcion, lanzarla para que el modulo  
                        //superior la capture y avise al usuario con un popup, por ejemplo.  
        	me.printStackTrace();
            return;  
        }  
          
    }  
  
}  