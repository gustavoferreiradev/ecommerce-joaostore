package br.utfpr.edu.ecommercejoaostore.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

public class IndexController {

	@GetMapping(value = "home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		return modelAndView;
	}
	
	
	@GetMapping(value = "")
	public String index(Model model) {
		
		return "redirect:/home";
	}
	
	@GetMapping(value = "403")
	public ModelAndView erro403() {
		ModelAndView modelAndView = new ModelAndView("403");
		
		return modelAndView;
	}
}
