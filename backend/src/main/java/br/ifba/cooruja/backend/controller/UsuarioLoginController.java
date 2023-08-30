package br.ifba.cooruja.backend.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.cooruja.backend.model.UsuarioLoginModel;
import br.ifba.cooruja.backend.repository.UsuarioLoginRepository;

@RestController
@RequestMapping("/usuario_login")
public class UsuarioLoginController {
	
	private UsuarioLoginRepository repository;

	public UsuarioLoginController(UsuarioLoginRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping
	public String teste() {
		System.out.println("teste");
		return "Testando Usuario Login...";
	}
	
	@PostMapping("/")
	@ResponseStatus( HttpStatus.CREATED )
	public boolean insert(@RequestBody UsuarioLoginModel model){
		System.out.println("server - insert: " + model);
		try {
			repository.save(model);
			System.out.println("server - insert: TRUE");
			return true;
		}
		catch (Exception e) {
			System.out.println("server - insert: FALSE");
			e.printStackTrace();
			return false;
		}
	}
	
	// Método que retornar o usuario associado ao ID passado como parametro
	@GetMapping("/{id}")
    public UsuarioLoginModel findByUsuarioId(@PathVariable("id") Long id) {
		Optional<UsuarioLoginModel> obj = repository.findById(id);
		if ( obj.isPresent() )
			return obj.get();
        return null;
    }
}