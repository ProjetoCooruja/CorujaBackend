package br.ifba.cooruja.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ifba.cooruja.backend.resource.Arquivo;
import br.ifba.cooruja.backend.model.ArquivoModel;
import br.ifba.cooruja.backend.repository.ArquivoRepository;

@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
    @Autowired
	private Arquivo arquivo;

	private ArquivoRepository repository;

	public ArquivoController(ArquivoRepository repository) {
		super();
		this.repository = repository;
	}

    @PostMapping
	public boolean upload(@RequestParam MultipartFile pArquivo, @RequestParam String id_usuario) { 
		try {
			System.out.println(pArquivo.getSize());
			System.out.println(id_usuario);
			String path = arquivo.salvarFoto(pArquivo);
			ArquivoModel am = new ArquivoModel();
			am.setId_usuario( Integer.parseInt(id_usuario) );
			am.setTipo_armazenamento( 0 );
			am.setNome_arquivo( pArquivo.getOriginalFilename() );
			am.setPath_arquivo( path );
			am.setTamanho(pArquivo.getSize());
			repository.save(am);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
