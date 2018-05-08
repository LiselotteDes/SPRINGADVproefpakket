package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsnummerValidator implements ConstraintValidator<Ondernemingsnummer, Long> {
	
	@Override
	public void initialize(Ondernemingsnummer ondernemingsnummer) {
	}
	
	@Override
	public boolean isValid(Long ondernemingsnummer, ConstraintValidatorContext context) {
		if (ondernemingsnummer == null) {
			return true;
		}
		long laatste2Cijfers = ondernemingsnummer % 100L;
		long overigeCijfers = ondernemingsnummer / 100;
		long rest = overigeCijfers % 97;
		
		return laatste2Cijfers == 97 - rest;
	}
}
