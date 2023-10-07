package br.ifba.cooruja.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.cooruja.backend.dto.LoginRequest;
import br.ifba.cooruja.backend.model.UsuarioModel;
import br.ifba.cooruja.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private UsuarioRepository repository;
	private final PasswordEncoder encoder;

	public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
		super();
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@GetMapping
	public String teste() {
		System.out.println("teste");
		return "Testando Rota Usuario...";
	}
	
	// Método que retornar todos os usuarios do banco de dados
	@GetMapping("/listall")
	public List<UsuarioModel> listall() {
		var usuarios = repository.findAll();
		return usuarios;
	}
	
	// Método que retornar o usuario associado ao ID passado como parametro
	@GetMapping("/{id}")
    public UsuarioModel findById(@PathVariable("id") Long id) {
		Optional<UsuarioModel> obj = repository.findById(id);
		if ( obj.isPresent() )
			return obj.get();
        return null;
    }
	
	@PostMapping("/")
	@ResponseStatus( HttpStatus.CREATED )
	public boolean insert(@RequestBody UsuarioModel model){
		System.out.println("server - insert: " + model);
		try {
			//salva a senha criptografada no banco
			model.setSenha(encoder.encode(model.getSenha()));
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
	public ResponseEntity<UsuarioModel> update(@PathVariable("id") Long id, @RequestBody UsuarioModel usuarioModel) {
		var p = repository.findById(id);
        if (p.isPresent()) {
            var model = p.get();
            if ( usuarioModel.getData_cadastro() != null )
            	model.setData_cadastro(usuarioModel.getData_cadastro());
            if ( usuarioModel.getData_modificacao() != null )
            	model.setData_modificacao(usuarioModel.getData_modificacao());
            if ( usuarioModel.getEmail() != null )
            	model.setEmail(usuarioModel.getEmail());
            if ( usuarioModel.getId_perfil() != null )
            	model.setId_perfil(usuarioModel.getId_perfil());
            if ( usuarioModel.getNome() != null )
            	model.setNome(usuarioModel.getNome());
            if ( usuarioModel.getSenha() != null )
            	model.setSenha(usuarioModel.getSenha());
            if ( usuarioModel.getSobrenome() != null )
            	model.setSobrenome(usuarioModel.getSobrenome());
            if ( usuarioModel.getTermos_aceite() != null )
            	model.setTermos_aceite(usuarioModel.getTermos_aceite());
            repository.save(model);
            return ResponseEntity.ok(model);
        } else {
        	return ResponseEntity.notFound().build();
        }
	}
	
// 	@PostMapping(value = "/login")
//     public ResponseEntity loginUsuario(@RequestBody LoginRequest loginRequest){
//         try {
//           Optional<UsuarioModel> usuario = repository.findByEmail(loginRequest.getUsername());
          
//           if(usuario.isPresent()){
//               if(usuario.get().getSenha().equals( loginRequest.getPassword()) ) {
//             	  // zerando o valor da senha, para que seja retornado ao cliente o objeto sem o valor da senha
//             	  usuario.get().setSenha("");
//             	  return ResponseEntity.ok(usuario);
//               } else {
//             	  return ResponseEntity.badRequest()
//                           .body("Usuario ou Senha Incorreta!");
//               }

//           }else{
// //              return ResponseEntity.notFound().headers(headers).build();
//         	  return ResponseEntity.notFound().build();
//           }

//       }catch (Exception e){
// 		e.printStackTrace();
// 		throw e;
//       }

// 	}

	@PostMapping(value = "/login")
	public ResponseEntity loginUsuario(@RequestBody LoginRequest loginRequest) {
		try {
			Optional<UsuarioModel> usuario = repository.findByEmail(loginRequest.getUsername());

			if (usuario.isPresent()) {
				String senhaCriptografada = usuario.get().getSenha();
				//variavel que compara a senha com a senha criptografada
				boolean senhaCorreta = encoder.matches(loginRequest.getPassword(), senhaCriptografada);

				if (senhaCorreta) {
					// A senha está correta, então você pode continuar com o login
					usuario.get().setSenha("");
					return ResponseEntity.ok(usuario);
				} else {
					return ResponseEntity.badRequest()
							.body("Usuário ou Senha Incorreta!");
				}
			} else {
				// Usuário não encontrado
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
}

}
