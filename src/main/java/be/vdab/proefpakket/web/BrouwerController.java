package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("brouwers")
class BrouwerController {
	
	private final BrouwerService brouwerService;
	
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}
	
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String REDIRECT_BIJ_BROUWER_NIET_GEVONDEN = "redirect:/";
	
	@GetMapping("{id}")
	ModelAndView brouwer(@PathVariable("id") Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW).addObject(brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	private static final String ONDERNEMINGSNUMMER_VIEW = "brouwers/ondernemingsnr";
	
	@GetMapping("{id}/ondernemingsnr")
	ModelAndView ondernemingsnr(@PathVariable("id") Optional<Brouwer> brouwer, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			OndernemingsnummerForm form = new OndernemingsnummerForm();
			form.setOndernemingsnummer(brouwer.get().getOndernemingsnr());
			return new ModelAndView(ONDERNEMINGSNUMMER_VIEW).addObject(brouwer.get()).addObject(form);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return  new ModelAndView(REDIRECT_BIJ_BROUWER_NIET_GEVONDEN);
	}
	
	@PostMapping("{id}/ondernemingsnr")
	ModelAndView ondernemingsnr(@PathVariable("id") Optional<Brouwer> brouwer, 
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
}
