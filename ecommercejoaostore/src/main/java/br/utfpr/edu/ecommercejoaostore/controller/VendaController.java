package br.utfpr.edu.ecommercejoaostore.controller;

import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.utfpr.edu.ecommercejoaostore.model.CompraProduto;
import br.utfpr.edu.ecommercejoaostore.model.Venda;
import br.utfpr.edu.ecommercejoaostore.model.VendaProduto;
import br.utfpr.edu.ecommercejoaostore.model.VendaProdutoPK;
import br.utfpr.edu.ecommercejoaostore.service.ClienteService;
import br.utfpr.edu.ecommercejoaostore.service.CrudService;
import br.utfpr.edu.ecommercejoaostore.service.ProdutoService;
import br.utfpr.edu.ecommercejoaostore.service.VendaProdutoService;
import br.utfpr.edu.ecommercejoaostore.service.VendaService;

@Controller
@RequestMapping ("venda")
@SessionAttributes("carrinho")

public class VendaController extends CrudController <Venda, Integer>{

	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private VendaProdutoService vendaProdutoService;
	
	@Autowired
	private ClienteService clienteService;	
		
	@Autowired
	private ProdutoService produtoService;
	
	@ModelAttribute("carrinho")
	private List<VendaProduto> getWatchList() {
		return new ArrayList<>();
	}

	@Override
	protected CrudService<Venda, Integer> getService(){
		return vendaService;
	}
	
	@Override
	protected String getURL() {
		return "venda";
	}
	
	@Override
	@GetMapping("new")
	protected ModelAndView form(Venda venda) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		if (venda != null) {
			modelAndView.addObject(venda);
		}else {
			modelAndView.addObject(new Venda());
		}
		modelAndView.addObject("clientes",clienteService.findAll());
		modelAndView.addObject("produtos",produtoService.findAll());
		return modelAndView;
	}
	
	@Override
	@GetMapping("{id}")
	protected ModelAndView form(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
		
		modelAndView.addObject(this.getService().findOne(id));
		return modelAndView;
	}
	@PostMapping("save")
	public ModelAndView save(@Valid Venda entity, BindingResult result, 
			RedirectAttributes attributes, Model model,
			@ModelAttribute("carrinho") List<VendaProduto> carrinho) {
		if ( result.hasErrors() ) {
			return form(entity);
		}
		
		carrinho.forEach(c -> c.getId().setVenda(entity));
		this.getService().save(entity);
		model.addAttribute("carrinho", new ArrayList<>());
		attributes.addFlashAttribute("mensagem", "Registro salvo com sucesso!");
		return new ModelAndView("redirect:/" + this.getURL() + "/page");
	}
	@PostMapping("ajax")
	public ResponseEntity<?> save(@Valid Venda entity, BindingResult result){
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("ajax/{id}")
	@ResponseBody
	public Venda edit(@PathVariable Integer id) {
		return getService().findOne(id);
	}
	
	@Override
	@GetMapping("page")
	public ModelAndView list(@RequestParam("page") Optional<Integer> page,
							 @RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);
		
		Page<Venda> list = this.getService().findAll( 
				PageRequest.of(currentPage -1, pageSize) );
		
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/list");
		modelAndView.addObject("list", list);
		modelAndView.addObject("clientes", clienteService.findAll() );
		modelAndView.addObject("produtos", produtoService.findAll() );


		if( list.getTotalPages() > 0) {
			List<Integer> pageNumbers = IntStream
					.rangeClosed(1, list.getTotalPages())
					.boxed().collect(Collectors.toList());
			modelAndView.addObject("pageNumbers", pageNumbers);
		}
		return modelAndView;
		
	}
	
	@PostMapping("add")
	public ModelAndView add(@RequestParam("produto") Integer id, Model model,
			@RequestParam("quantidade") Integer quantidade,
			@RequestParam("valor") Double valor,
			@ModelAttribute("carrinho") List<VendaProduto> carrinho) {
		VendaProduto vendaProduto = new VendaProduto();
		VendaProdutoPK vendaProdutoPK = new VendaProdutoPK();
		vendaProdutoPK.setProduto(produtoService.findOne(id));
		vendaProduto.setId(vendaProdutoPK);
		vendaProduto.setQuantidade(quantidade);
		vendaProduto.setValor(valor);
		carrinho.add(vendaProduto);
		model.addAttribute("carrinho", carrinho);		
			
		return new ModelAndView("redirect:/" + this.getURL() + "/new");
	}
	
	@GetMapping("limpar")
	public ModelAndView limpar (Model model, @ModelAttribute("carrinho") List<VendaProduto> carrinho) {
		ModelAndView modelAndView = new ModelAndView(this.getURL() + "/form");
			modelAndView.addObject("clientes",clienteService.findAll());
		modelAndView.addObject("produtos",produtoService.findAll());
		model.addAttribute("carrinho", new ArrayList<>());
		return modelAndView;
	}
	

}
