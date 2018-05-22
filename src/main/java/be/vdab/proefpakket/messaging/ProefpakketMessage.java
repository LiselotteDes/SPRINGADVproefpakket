package be.vdab.proefpakket.messaging;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProefpakketMessage {

	private String emailAdres;
	private String brouwerNaam;
	
	public ProefpakketMessage(String emailAdres, String brouwerNaam) {
		this.emailAdres = emailAdres;
		this.brouwerNaam = brouwerNaam;
	}
	
	protected ProefpakketMessage() {
	}
	
	public String getEmailAdres() {
		return emailAdres;
	}
	
	public String getBrouwerNaam() {
		return brouwerNaam;
	}
	
}
