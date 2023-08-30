package br.ifba.cooruja.backend.negocio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Arquivo {

	private String getFileExtension(String fileName) {
		if (fileName == null || fileName.lastIndexOf(".") == -1) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf("."));
	}
	
	public String salvar(String pastaRaiz, String pastaArquivos, MultipartFile arquivo) {
		String exString = this.getFileExtension(arquivo.getOriginalFilename());
		UUID gfg2 = UUID.randomUUID();
		String novo_nome = gfg2.toString()+exString;
		
		Path diretorioPath = Paths.get(pastaRaiz, pastaArquivos);
		System.out.println("diretorioPath: " + diretorioPath);
		Path arquivoPath = diretorioPath.resolve(novo_nome);

		try {
			System.out.println(arquivoPath);
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
			// return arquivoPath.toString();
			return pastaArquivos + "/" + novo_nome;
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
}
