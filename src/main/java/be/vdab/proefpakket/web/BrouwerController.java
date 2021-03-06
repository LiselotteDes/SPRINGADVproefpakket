package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.BrouwerService;
import be.vdab.proefpakket.services.GemeenteService;

@Controller
@RequestMapping("brouwers")
@SessionAttributes("bestelling")
class BrouwerController {
	
	private final BrouwerService brouwerService;
	private final GemeenteService gemeenteService;
	private final BestellingService bestellingService;
	
	BrouwerController(BrouwerService brouwerService, GemeenteService gemeenteService, BestellingService bestellingService) {
		this.brouwerService = brouwerService;
		this.gemeenteService = gemeenteService;
		this.bestellingService = bestellingService;
	}
	
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
	
	@GetMapping("{brouwer}")
	ModelAndView brouwer(@PathVariable Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW).addObject(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	private static final String ONDERNEMINGSNUMMER_VIEW = "brouwers/ondernemingsnr";
	
	@GetMapping("{brouwer}/ondernemingsnr")
	ModelAndView ondernemingsnr(@PathVariable Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			OndernemingsnummerForm form = new OndernemingsnummerForm();
			form.setOndernemingsnummer(brouwer.get().getOndernemingsnr());
			return new ModelAndView(ONDERNEMINGSNUMMER_VIEW).addObject(brouwer.get()).addObject(form);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return  new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@PostMapping("{brouwer}/ondernemingsnr")
	ModelAndView ondernemingsnr(@PathVariable Optional<Brouwer> brouwer, 
								@Valid OndernemingsnummerForm form, 
								BindingResult bindingResult,
								RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(ONDERNEMINGSNUMMER_VIEW).addObject(brouwer.get());
			}
			brouwer.get().setOndernemingsnr(form.getOndernemingsnummer());
			brouwerService.update(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	private static final String PROEFPAKKET_STAP_1_VIEW = "brouwers/proefpakketstap1";
	
	@GetMapping("{brouwer}/proefpakket")
	ModelAndView proefpakket(@PathVariable Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(PROEFPAKKET_STAP_1_VIEW).addObject(brouwer.get()).addObject(new Bestelling());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@InitBinder("bestelling")
	void initBinder(DataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
	private static final String PROEFPAKKET_STAP_2_VIEW = "brouwers/proefpakketstap2";
	
	@PostMapping(value = "{brouwer}/proefpakket", params = "stap2")
	ModelAndView proefpakketStap1NaarStap2(@PathVariable Optional<Brouwer> brouwer,
											@Validated(Bestelling.Stap1.class) Bestelling bestelling, 
											BindingResult bindingResult,
											RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(PROEFPAKKET_STAP_1_VIEW).addObject(brouwer.get());
			}
			return new ModelAndView(PROEFPAKKET_STAP_2_VIEW).addObject(brouwer.get()).addObject("gemeenten", gemeenteService.findAll());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@PostMapping(value="{brouwer}/proefpakket", params = "stap1")
	ModelAndView proefpakketStap2NaarStap1(@PathVariable Optional<Brouwer> brouwer, 
											Bestelling bestelling,
											RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(PROEFPAKKET_STAP_1_VIEW).addObject(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	private static final String REDIRECT_NA_BESTELLING = "redirect:/";
	
	@PostMapping(value="{brouwer}/proefpakket", params="opslaan")
	ModelAndView proefpakketOpslaan(@PathVariable Optional<Brouwer> brouwer,
									@Validated(Bestelling.Stap2.class) Bestelling bestelling,
									BindingResult bindingResult,
									RedirectAttributes redirectAttributes,
									SessionStatus sessionStatus) {
		if (brouwer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(PROEFPAKKET_STAP_2_VIEW).addObject(brouwer.get()).addObject("gemeenten", gemeenteService.findAll());
			}
			//bestelling.setBrouwer(brouwer.get());
			bestellingService.create(bestelling);
			sessionStatus.setComplete();
			return new ModelAndView(REDIRECT_NA_BESTELLING);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
}
