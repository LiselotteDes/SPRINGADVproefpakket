package be.vdab.proefpakket.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.proefpakket.constraints.Ondernemingsnummer;
import be.vdab.proefpakket.valueobjects.Adres;

@Entity
@Table(name = "brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	@Embedded
	private Adres adres;
	@Ondernemingsnummer
	private Long ondernemingsnr;
	
	public long getId() {
		return id;
	}
	public String getNaam() {
		return naam;
	}
	public Adres getAdres() {
		return adres;
	}
	public Long getOndernemingsnr() {
		return ondernemingsnr;
	}
	public void setOndernemingsnr(Long ondernemingsnr) {
		this.ondernemingsnr = ondernemingsnr;
	}
}
