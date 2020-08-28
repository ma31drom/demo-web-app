package by.pvt.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.pvt.repository.model.Role;
import by.pvt.repository.model.User;
import by.pvt.service.config.EmailProperties;
import by.pvt.service.dto.UserDetailsDTO;

@Service
public class UserRegistrationEmailService implements RegistrationService {

	@Autowired
	private EmailProperties config;
	
	@Override
	public void registerUser(UserDetailsDTO user) {
		sendEmail(user);
	}

	private void sendEmail(UserDetailsDTO user) {
		// Recipient's email ID needs to be mentioned.
		String to = user.getEmail();
		// Sender's email ID needs to be mentioned
		String from = "norply@gmail.com";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", config.getHost());
		properties.put("mail.smtp.port", config.getPort());
		properties.put("mail.smtp.ssl.enable", config.getSsl());
		properties.put("mail.smtp.auth", config.getAuth());

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config.getLogin(), config.getPass());
			}
		});

		// Used to debug SMTP issues
		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setContent("<a href='http://localhost:8080/register/" + user.getLogin() + "'>Lets try</a>", "text/html");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	@Override
	public void activateUser(String login) {
	}

}
