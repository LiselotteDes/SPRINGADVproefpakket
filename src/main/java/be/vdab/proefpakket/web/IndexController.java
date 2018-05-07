package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("/")
class IndexController {
	
	private final BrouwerService brouwerService;
	
	IndexController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}
	
	private static final String VIEW = "index";
	private static final char[] ALFABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW, "alfabet", ALFABET);
	}
	
	/*@GetMapping("{letter}")
	ModelAndView findByBeginLetter(@PathVariable String letter) {
		return new ModelAndView(VIEW, "alfabet", ALFABET).addObject("brouwers", brouwerService.findByBeginNaam(letter));
	}	FOUT! */
	@GetMapping(params = "letter")
	ModelAndView findByBeginLetter(String letter) {
		return new ModelAndView(VIEW, "alfabet", ALFABET).addObject("brouwers", brouwerService.findByBeginNaam(letter));
	}
}
