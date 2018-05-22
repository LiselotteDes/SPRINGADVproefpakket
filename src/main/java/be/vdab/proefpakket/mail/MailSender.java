package be.vdab.proefpakket.mail;

public interface MailSender {
	
	// void nieuwProefpakket(Bestelling bestelling);
	void nieuwProefpakket(String emailAdres, String brouwerNaam);
	
}
