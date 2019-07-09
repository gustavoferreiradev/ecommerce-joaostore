package br.utfpr.edu.ecommercejoaostore.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.utfpr.edu.ecommercejoaostore.model.Categoria;
import br.utfpr.edu.ecommercejoaostore.model.Cliente;
import br.utfpr.edu.ecommercejoaostore.service.CategoriaService;
import br.utfpr.edu.ecommercejoaostore.service.CidadeService;
import br.utfpr.edu.ecommercejoaostore.service.ClienteService;
import br.utfpr.edu.ecommercejoaostore.service.CrudService;

@Controller
@RequestMapping ("cliente")
public class ClienteController extends CrudController <Cliente, Integer> {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Override
	protected CrudService<Cliente, Integer> getService() {
		return clienteService;
	} 

	
	@Override
	protected String getURL() {
		return "cliente";
	}
	
	@Override
	@GetMapping("new")
	protected ModelAndView form(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (cliente != null) {
			modelAndView.addObject(cliente);
		}else {
			modelAndView.addObject(new Cliente());
		}
		return modelAndView;
	}
	
	@Override
	protected ModelAndView form(@PathVariable Integer id) {
	    ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		modelAndView.addObject(this.getService().findOne(id));
		return modelAndView;
	}
	
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Cliente entity, BindingResult result){
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Cliente edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Cliente> list = this.getService().findAll( 
				PageRequest.of(currentPage -1, pageSize) );
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("cidades", cidadeService.findAll() );
		
		return modelAndView;
 }
}

