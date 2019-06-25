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

import br.utfpr.edu.ecommercejoaostore.model.Produto;
import br.utfpr.edu.ecommercejoaostore.service.CategoriaService;
import br.utfpr.edu.ecommercejoaostore.service.CrudService;
import br.utfpr.edu.ecommercejoaostore.service.MarcaService;
import br.utfpr.edu.ecommercejoaostore.service.ProdutoService;

@Controller
@RequestMapping ("produto")
public class ProdutoController extends CrudController <Produto, Integer> {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private MarcaService marcaService;
	
	@Override
	protected CrudService<Produto, Integer> getService() {
		return produtoService;
	}

	@Override
	protected String getURL() {
		return "produto";
	}

	@Override
	@RequestMapping ("new")
		protected ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (produto != null) {
			modelAndView.addObject(produto);
		}else {
			modelAndView.addObject(new Produto());
		}
		return modelAndView;
	}

	@Override
	@GetMapping("{id}")
	protected ModelAndView form(@PathVariable Integer id) {
		    ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
			modelAndView.addObject(this.getService().findOne(id));
			return modelAndView;
	}
	
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Produto entity, BindingResult result){
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Produto edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Produto> list = this.getService().findAll( 
				PageRequest.of(currentPage -1, pageSize) );
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("categorias", categoriaService.findAll() );
		
		ModelAndView modelAndView2 = new ModelAndView(this.getURL() + "/list");
		modelAndView2.addObject("list", list);
		modelAndView2.addObject("marcas", marcaService.findAll() );
		
		if( list.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView2.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView2;
	}
}
