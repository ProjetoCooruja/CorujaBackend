package br.ifba.cooruja.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ifba.cooruja.backend.model.ArquivoModel;
import br.ifba.cooruja.backend.repository.ArquivoRepository;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
    
	private ArquivoRepository repository;

	public ArquivoController(ArquivoRepository repository) {
		super();
		this.repository = repository;
	}

    @PostMapping
	public boolean upload(@RequestParam MultipartFile file, @RequestParam String id_usuario) { 
		try {
			System.out.println(file.getSize());
			System.out.println(id_usuario);
			// String path = arquivo.salvar(file);
			ArquivoModel am = new ArquivoModel();
			am.setId_usuario( Integer.parseInt(id_usuario) );
			am.setTipo_armazenamento( 0 );
			am.setNome_arquivo( file.getOriginalFilename() );
			// am.setPath_arquivo( path );
			am.setTamanho(file.getSize());
			repository.save(am);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@GetMapping("/listall")
	public List<ArquivoModel> listall() {
		var model = repository.findAll();
		return model;
	}
	
	@GetMapping("/{id}")
	public ArquivoModel findById(@PathVariable("id") Long id) {
		Optional<ArquivoModel> obj = repository.findById(id);
		if ( obj.isPresent() )
			return obj.get();
	    return null;
	}

}
