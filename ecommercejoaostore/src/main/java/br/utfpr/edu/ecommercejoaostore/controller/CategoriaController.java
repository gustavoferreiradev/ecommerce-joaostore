package br.utfpr.edu.ecommercejoaostore.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
import br.utfpr.edu.ecommercejoaostore.service.CategoriaService;
import br.utfpr.edu.ecommercejoaostore.service.CrudService;

@Controller
@RequestMapping ("categoria")
public class CategoriaController extends CrudController <Categoria, Integer> {
	
	@Autowired
	private CategoriaService categoriaService;

	@Override
	protected CrudService<Categoria, Integer> getService() {
		return categoriaService;
	}

	@Override
	protected String getURL() {
		return "categoria";
	}

	@Override
	@GetMapping("new")
	protected ModelAndView form(Categoria categoria) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (categoria != null) {
			modelAndView.addObject(categoria);
		}else {
			modelAndView.addObject(new Categoria());
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
	public ResponseEntity<?> save(@Valid Categoria entity, BindingResult result){
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Categoria edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Categoria> list = this.getService().findAll( 
				PageRequest.of(currentPage -1, pageSize) );
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("categorias", categoriaService.findAll() );


		if( list.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
		
	}
	

}
