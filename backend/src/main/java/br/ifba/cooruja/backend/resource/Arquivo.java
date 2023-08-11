package br.ifba.cooruja.backend.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Arquivo {
    
    @Value("${cooruja.pasta.raiz}")
	private String raiz;
	
	@Value("${cooruja.pasta.diretorio-arquivos}")
	private String diretorioFotos;

	private String getFileExtension(String fileName) {
		if (fileName == null || fileName.lastIndexOf(".") == -1) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public String salvarFoto(MultipartFile pArquivo) {
		return this.salvar(this.diretorioFotos, pArquivo);
	}
	
	public String salvar(String diretorio, MultipartFile arquivo) {
		
		String exString = this.getFileExtension(arquivo.getOriginalFilename());
		UUID gfg2 = UUID.randomUUID();
		String novo_nome = gfg2.toString()+exString;
		
		Path diretorioPath = Paths.get(this.raiz, diretorio);

		Path arquivoPath = diretorioPath.resolve(novo_nome);

		

 		// try {
        //     byte[] bytes = file.getBytes();
        //     Path path = Paths.get("/local/do/arquivo/" + file.getOriginalFilename());
        //     Files.write(path, bytes);
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return new ResponseEntity<>("Erro ao armazenar arquivo.", HttpStatus.INTERNAL_SERVER_ERROR);
        // }
		try {
			System.out.println(arquivoPath);
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			return arquivoPath.toString();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
}
