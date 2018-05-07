package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("brouwers")
class BrouwerController {
	
	private final BrouwerService brouwerService;
	
	BrouwerController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}
	
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	
	@GetMapping("{id}")
	ModelAndView brouwer(@PathVariable long id) {
		ModelAndView modelAndView = new ModelAndView(BROUWER_VIEW);
		if (brouwerService.read(id).isPresent()) {
			modelAndView.addObject(brouwerService.read(id).get());
		}
		return modelAndView;
	}
}
