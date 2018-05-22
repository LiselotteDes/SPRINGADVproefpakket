package be.vdab.proefpakket.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.exceptions.KanMailNietZendenException;

@Component
class DefaultMailSender implements MailSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultMailSender.class);
	private final JavaMailSender sender;
	
	DefaultMailSender(JavaMailSender sender) {
		this.sender = sender;
	}
	
	@Override
	/*public void nieuwProefpakket(Bestelling bestelling) {
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			
			message.setTo(bestelling.getEmailadres());
			message.setSubject("Nieuw proefpakket " + bestelling.getBrouwer().getNaam());
			message.setText("Bedankt voor uw interesse. U ontvangt uw proefpakket "
							+ bestelling.getBrouwer().getNaam()
							+ " binnenkort.");
			sender.send(message);
			
		} catch (MailException ex) {
			
			LOGGER.error("Kan mail nieuw proefpakket niet versturen", ex);
			throw new KanMailNietZendenException();
		}
		
	}*/
	public void nieuwProefpakket(String emailAdres, String brouwerNaam) {
		
		try {
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailAdres);
			message.setSubject("Nieuw proefpakket " + brouwerNaam);
			message.setText("Bedankt voor uw interesse. U ontvangt uw proefpakket " + brouwerNaam + " binnenkort.");
			sender.send(message);
			
		} catch(MailException ex) {
			
			LOGGER.error("Kan mail proefpakket niet versturen", ex);
			throw new KanMailNietZendenException();
		}
	}

}
