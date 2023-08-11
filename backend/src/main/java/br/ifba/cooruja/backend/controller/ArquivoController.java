package br.ifba.cooruja.backend.controller;

import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.ifba.cooruja.backend.resource.Arquivo;
import br.ifba.cooruja.backend.model.ArquivoModel;
import br.ifba.cooruja.backend.model.PerfilModel;
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

	// @GetMapping("/remote")
    // public ResponseEntity<Resource> getRemoteImage() throws IOException {
    //     String remoteImageUrl = "https://exemplo.com/imagem.jpg";

    //     try {
    //         URL website = new URL(remoteImageUrl);
    //         ReadableByteChannel rbc = Channels.newChannel(website.openStream());

    //         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //         outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    //         ByteArrayResource byteArrayResource = new ByteArrayResource(outputStream.toByteArray());

    //         return ResponseEntity.ok()
    //                 .contentType(MediaType.IMAGE_JPEG) // Ou outro tipo apropriado
    //                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"imagem.jpg\"")
    //                 .body(byteArrayResource);
    //     } catch (Exception e) {
    //         // Trate exceções de forma adequada aqui.
    //         throw new RuntimeException("Erro ao obter a imagem remota.", e);
    //     }
    // }
}
