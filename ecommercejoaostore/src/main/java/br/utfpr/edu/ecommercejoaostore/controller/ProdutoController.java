package br.utfpr.edu.ecommercejoaostore.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;
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
	
	@PostMapping("upload")
	public ResponseEntity<?> save(@Valid Produto entity, BindingResult result,
			@RequestParam("anexos") MultipartFile[] anexos,
			HttpServletRequest request){
		if ( result.hasErrors() ) {
			return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		getService().save(entity);
		
		if(anexos.length > 0 && !anexos[0].getOriginalFilename().isEmpty()) {
			saveFile(entity.getId(), anexos,request);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void saveFile(Integer id, MultipartFile[] anexos, HttpServletRequest request) {
		File dir = new File(request.getServletContext().getRealPath("/images/"));
		if(!dir.exists()) { 
			dir.mkdirs(); 
		}
		
		String caminhoAnexo = request.getServletContext().getRealPath("/images/");
		int i = 0;
		for(MultipartFile anexo: anexos) {
		i++;	
		String extensao = anexo.getOriginalFilename().substring(
				anexo.getOriginalFilename().lastIndexOf("."),
				anexo.getOriginalFilename().length());
		
		String nomeArquivo = id + "_" + i + extensao;
		
		try {
			FileOutputStream fileOut = new FileOutputStream(new File(caminhoAnexo + nomeArquivo));
			BufferedOutputStream stream = new BufferedOutputStream(fileOut);
			stream.write(anexo.getBytes());
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
		
	}
}
