package br.utfpr.edu.ecommercejoaostore.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.utfpr.edu.ecommercejoaostore.service.CrudService;

public abstract class CrudController <T, ID extends Serializable> {
	
    protected abstract CrudService <T, ID> getService();
	
	protected abstract String getURL();
	
	protected abstract ModelAndView form(T entity);
	
	protected abstract ModelAndView form(@PathVariable ID id);
	
	@GetMapping
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", this.getService().findAll());
		
		return modelAndView;
	}
	
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<T> list = this.getService().findAll( 
				PageRequest.of(currentPage -1, pageSize) );
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		
		if( list.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
	}
	
	
	@PostMapping
	public ModelAndView save(@Valid T entity, BindingResult result, 
			RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			return form(entity);
		}
		this.getService().save(entity);
		attributes.addFlashAttribute("mensagem", "Registro salvo com sucesso!");
		
		return new ModelAndView("redirect:/" + this.getURL() + "/page");
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable ID id){
		try {
			this.getService().delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
