package br.ifba.cooruja.backend.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ifba.cooruja.backend.dto.ComentarioDTO;
import br.ifba.cooruja.backend.dto.ComentarioRequest;
import br.ifba.cooruja.backend.dto.PostDTO;
import br.ifba.cooruja.backend.dto.PostRequest;
import br.ifba.cooruja.backend.model.ArquivoModel;
import br.ifba.cooruja.backend.model.ComentarioModel;
import br.ifba.cooruja.backend.model.PostModel;
import br.ifba.cooruja.backend.model.UsuarioModel;
import br.ifba.cooruja.backend.negocio.Arquivo;
import br.ifba.cooruja.backend.repository.ArquivoRepository;
import br.ifba.cooruja.backend.repository.PostRepository;
import br.ifba.cooruja.backend.repository.UsuarioRepository;
import br.ifba.cooruja.backend.repository.ComentarioRepository;


@RestController
@RequestMapping("/post")
public class PostController {

	@Value("${upload.path}")
    private String uploadPath;

	@Value("${sharing.path}")
    private String sharingPath;

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private ArquivoRepository arquivoRepository;
	@Autowired
	private ComentarioRepository comentarioRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public PostController(PostRepository postRepository, ArquivoRepository arquivoRepository) {
		super();
		this.postRepository = postRepository;
		this.arquivoRepository = arquivoRepository;
	}

	private List<PostDTO> converterParaPostDto(List<PostModel> listPost) {
		String baseUrl = ServletUriComponentsBuilder
							.fromCurrentContextPath()
							.build()
							.toUriString();
		
		List<PostDTO> list = new ArrayList<PostDTO>();

		for (PostModel pm : listPost) {
			PostDTO pdto = new PostDTO();
			pdto.setId(pm.getId());
			pdto.setTitulo(pm.getTitulo());
			pdto.setTags( pm.getTags() );
			pdto.setId_arquivo( pm.getArquivo().getId() );
			pdto.setPath_arquivo( baseUrl + "/" + sharingPath + pm.getArquivo().getPath_arquivo() );
			pdto.setNome( pm.getUsuario().getNome() );
			pdto.setData_cadastro( pm.getData_cadastro() );
			List<ComentarioDTO> lComentarios = new ArrayList<ComentarioDTO>();
			for ( ComentarioModel cModel : pm.getComentarios() ){
				ComentarioDTO cDto = new ComentarioDTO(
											cModel.getId(), 
											cModel.getUsuario().getId(), 
											cModel.getUsuario().getNome(), 
											cModel.getComentario(), 
											cModel.getData_cadastro() 
										);
				lComentarios.add(cDto);
			}
			pdto.setComentarios( lComentarios );
			list.add(pdto);
		}
		return list;
	}

    @GetMapping("/listall")
	public List<PostDTO> listall() {
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		var model = postRepository.findAll(sort);
		return converterParaPostDto( model );
	}

	@GetMapping("/listall_x")
	public List<PostModel> listall_x() {
		var model = postRepository.findAll();
		return model;
	}

    @PostMapping
	@Transactional
    public Boolean add(@ModelAttribute PostRequest form) {
		String titulo = form.getTitulo();
		String comentario = form.getComentario();
		String tags = form.getTags();
		Long id_usuario = form.getId_usuario();
        MultipartFile file = form.getFile();
		
		UsuarioModel uModel = usuarioRepository.findById(id_usuario).get();
		
		String path = "";
		try {
			String baseUrl = uploadPath;
			Arquivo arquivo = new Arquivo();
			path = arquivo.salvar( baseUrl, "", file);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
			// return ResponseEntity.ok("Erro ao efetuar o uploado da imagem!");
		}

		try {
			ArquivoModel am = new ArquivoModel();
			am.setId_usuario( id_usuario.intValue() );
			am.setTipo_armazenamento( 0 );
			am.setNome_arquivo( file.getOriginalFilename() );
			am.setPath_arquivo( path );
			am.setTamanho(file.getSize());

			// Long id_arquivo = arquivoRepository.save(am).getId();

			PostModel pm = new PostModel();
			pm.setArquivo(am);
			pm.setUsuario( uModel );
			pm.setTitulo(titulo);
			pm.setTags(tags);

			PostModel pms = postRepository.save( pm );
			if (!comentario.isBlank() && !comentario.isEmpty())
			{
				ComentarioModel cm = new ComentarioModel();
				cm.setComentario(comentario);
				cm.setUsuario( uModel );
				cm.setPostModel(pms);
				comentarioRepository.save(cm);
			}
			return true;
			// return ResponseEntity.ok().body(null);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// return ResponseEntity.ok("Erro ao efetuar o uploado da imagem!");
		}
	}

	@PostMapping("/add_comentario")
	public Boolean add_comentario(@ModelAttribute ComentarioRequest form) {
		try {
			Long id_usuario = form.getId_usuario();
			Long id_post = form.getId_post();
			String comentario = form.getComentario();
	
			PostModel pModel = new PostModel();
			pModel.setId(id_post);
	
			UsuarioModel uModel = usuarioRepository.findById(id_usuario).get();
			uModel.setId(id_usuario);
	
			ComentarioModel cm = new ComentarioModel();
			cm.setComentario(comentario);
			cm.setPostModel(pModel);
			cm.setUsuario(uModel);
			cm.setData_cadastro( new Timestamp(System.currentTimeMillis()) );
			comentarioRepository.save(cm);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
