package br.ifba.tarefa.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.tarefa.backend.model.PerfilModel;
import br.ifba.tarefa.backend.repository.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
	
	private PerfilRepository repository;

	public PerfilController(PerfilRepository repository) {
		super();
		this.repository = repository;
	}
	
	@GetMapping
	public String teste() {
		System.out.println("teste");
		return "Testando Rota Perfil...";
	}
	
	// Método que retornar todos os usuarios do banco de dados
	@GetMapping("/listall")
	public List<PerfilModel> listall() {
		var model = repository.findAll();
		return model;
	}
	
	@GetMapping("/{id}")
	public PerfilModel findById(@PathVariable("id") Long id) {
		Optional<PerfilModel> obj = repository.findById(id);
		if ( obj.isPresent() )
			return obj.get();
	    return null;
	}
	
	@PostMapping("/")
	@ResponseStatus( HttpStatus.CREATED )
	public boolean insert(@RequestBody PerfilModel model){
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

	@DeleteMapping("/{id}")
	@ResponseStatus( HttpStatus.ACCEPTED )
	public boolean delete(@PathVariable("id") Long id) {
		System.out.println("delete");
		try {
			repository.deleteById(id);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	@PutMapping("/{id}")
	@ResponseStatus( HttpStatus.CREATED )
	public ResponseEntity<PerfilModel> update(@PathVariable("id") Long id, @RequestBody PerfilModel perfilModel) {
		var p = repository.findById(id);
        if (p.isPresent()) {
            var perfil = p.get();
            if ( perfilModel.getDescricao_perfil() != null )
            	perfil.setDescricao_perfil(perfilModel.getDescricao_perfil());            
            if ( perfilModel.getNome_perfil() != null )
            	perfil.setNome_perfil(perfilModel.getNome_perfil());
            if ( perfilModel.getStatus() != null )
            	perfil.setStatus(perfilModel.getStatus());
            repository.save(perfil);
            return ResponseEntity.ok(perfil);
        } else {
        	return ResponseEntity.notFound().build();
        }
	}
}
