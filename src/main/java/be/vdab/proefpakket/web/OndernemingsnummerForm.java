package be.vdab.proefpakket.web;

import javax.validation.constraints.NotNull;

import be.vdab.proefpakket.constraints.Ondernemingsnummer;

public class OndernemingsnummerForm {
	@NotNull
	@Ondernemingsnummer
	private Long ondernemingsnummer;
	
	public Long getOndernemingsnummer() {
		return ondernemingsnummer;
	}
	public void setOndernemingsnummer(Long ondernemingsnummer) {
		this.ondernemingsnummer = ondernemingsnummer;
	}
}
