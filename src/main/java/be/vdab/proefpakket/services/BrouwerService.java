package be.vdab.proefpakket.services;

import java.util.List;
import java.util.Optional;

import be.vdab.proefpakket.entities.Brouwer;

public interface BrouwerService {
	
	List<Brouwer> findByBeginNaam(String beginNaam);
	Optional<Brouwer> read(long id);
	
}