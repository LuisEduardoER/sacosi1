package Mail;

import java.security.Security;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailManager {

	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_PORT = "465";
	private static final String emailFromAddress = "rentSystem@gmail.com";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static final String SUBJECT = "[Rent System] Your Reservation has been descarted.";

	//Soh pra dizer como envia... passa um array de emails e a mensagem, tirem o comentario /* */
	/*public static void main(String args[]) throws Exception {
		String []vetor = {"luizinho16@gmail.com", "anata.hoshii@gmail.com", "melmongiovi@gmail.com", "raissa_mds@gmail.com", "s.costa.filipe@gmail.com"};
		MailManager mg = new MailManager();
		String message = "Email enviado com sucesso, avisando ao cliente que a sua reserva foi cancelada. =D";
		mg.sendEmail(vetor, message);
	}*/
	
	
	/**
	 * Metodo que envia os emails atraves de uma lista de emails e uma mensagem
	 * @param listOfEmailsTo
	 * 				lista com os emails dos clientes
	 * @param message
	 * 				string com a mensagem no corpo do email
	 * @throws MessagingException
	 * 				caso a mensagem nao possa ser enviada
	 */
	public void sendEmail(ArrayList<String> listOfEmailsTo, String message) throws MessagingException{
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		new MailManager().sendMessage(listOfEmailsTo, SUBJECT, message,
				emailFromAddress);
	}
	
	/**
	 * Metodo que envia as mensagens, a partir da documentacao JavaMail
	 * @param listOfEmailsTo
	 * @param subject
	 * @param message
	 * @param from
	 * @throws MessagingException
	 */
	private void sendMessage(ArrayList<String>listOfEmailsTo, String subject,
			String message, String from) throws MessagingException {
		boolean debug = true;

		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");
		
		//Padrao "iterator" aki... =P
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
				
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("rentsystem@gmail.com",
								"sacorentsystem");
					}
				});

		session.setDebug(debug);

		Message msg = new MimeMessage(session);
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);

		InternetAddress[] addressTo = new InternetAddress[listOfEmailsTo.size()];
		for (int i = 0; i < listOfEmailsTo.size(); i++) {
			addressTo[i] = new InternetAddress(listOfEmailsTo.get(i));
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}