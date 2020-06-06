package projet.view.mail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfox.commun.exception.ExceptionAnomaly;


public class ServiceMail {
	
	// Champs
	
	private Properties	props;
	private String		userName;
	private String		password;
	private String		senderAddress;
	private String		senderName;
	private final ObservableList<String>groupe=FXCollections.observableArrayList("Administrateur","Participant","Membre","Externe","Responsable","Tout le monde");
	
	// Initialisations
	
	@PostConstruct
	public void init() {
		
		// Lecture du fichier de configuration
		props = new Properties();
		String chemin = "META-INF/mail.properties";
		InputStream in =  ClassLoader.getSystemResourceAsStream( chemin );
		if ( in == null ) {
			throw new ExceptionAnomaly( "Impossible de charger le fichier de configuration :\n" + chemin );
		}
		try {
			props.load( in );
			in.close();
		} catch (IOException e) {
			throw new RuntimeException( e );
		}
		
		userName = getProperty( "user.name" );
		password = getProperty( "user.password" );
		senderName = getProperty( "sender.name" );
		senderAddress = getProperty( "sender.address", userName );
		
	}
	

	public ObservableList<String> getGroupe() {
		return groupe;
	}


	public void send( String toAddress, String toName, String subject, String text, boolean flagHtml, File ... files ) {

		Session session = Session.getInstance( props );
		
		try {
			Message message = new MimeMessage( session );
			Multipart mp = new MimeMultipart();
			message.setContent( mp );

			message.setFrom( new InternetAddress( senderAddress, senderName ));
			
			message.addRecipient( Message.RecipientType.TO,
					new InternetAddress( toAddress, toName ));
			
			message.setSubject( subject );

			if ( text != null ) {
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent( text,  flagHtml ? "text/html"  : "text/plain" );
				mp.addBodyPart(mbp);
			}	 	    
			
			if ( files.length != 0 ) {
				for ( File file : files ) {
					if ( file != null ) {
						MimeBodyPart mbp = new MimeBodyPart();
						mbp.attachFile(file);
						mp.addBodyPart(mbp);
					}
				}
			}	 	    
	 	    
	         // Send message
	         Transport.send( message, userName, password );

	      } catch (MessagingException | IOException e) {
	            throw new RuntimeException(e);
	      }
	  		
	}

	
	// Méthodes auxiliaires
	
	private String getProperty( String key, String defaultValue ) {
		String value = props.getProperty( key );
		if ( value == null ) {
			if ( defaultValue == null ) {
				throw new ExceptionAnomaly( 
						"Paramètre absent dans le fichier de configuration :\n"
						+ key );
			} else {
				return defaultValue;
			}
		}
		return value;
	}

	private String getProperty( String key ) {
		return getProperty( key, null );
	}
	
}
