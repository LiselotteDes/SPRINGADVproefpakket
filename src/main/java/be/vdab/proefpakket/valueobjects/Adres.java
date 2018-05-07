package be.vdab.proefpakket.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.proefpakket.entities.Gemeente;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisnr;
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "gemeenteid")
	private Gemeente gemeente;
	
	public String getStraat() {
		return straat;
	}
	public String getHuisNr() {
		return huisnr;
	}
	public Gemeente getGemeente() {
		return gemeente;
	}
}
