package br.ifba.cooruja.backend.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.ifba.cooruja.backend.data.DetalheUsuarioData;
import br.ifba.cooruja.backend.model.UsuarioModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    public static final int TOKEN_EXPIRACAO = 600_000; //10 minutos

    public static final String TOKEN_SENHA = "e0395967-d0b2-4b20-952b-11bd2e8d147e"; //criando por guidgenerator.com
    //o ideal é que essa senha não ficasse exposta no código, está aqui por questões de desenvolvimento.


    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws org.springframework.security.core.AuthenticationException {

        try {
            UsuarioModel usuario = new ObjectMapper()
                .readValue(request.getInputStream(), UsuarioModel.class);   
                
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                usuario.getEmail(),
                usuario.getSenha(),
                new ArrayList<>()
                
                
                ));    
        
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuario", e);
        }
            
    }

    @Override
    protected void successfulAuthentication (HttpServletRequest request, 
                                            HttpServletResponse response, 
                                            FilterChain chain, 
                                            Authentication authResult) throws IOException, ServletException {

            DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

            String token = JWT.create().
                        withSubject(usuarioData.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
                        .sign(Algorithm.HMAC512(TOKEN_SENHA));

            response.getWriter().write(token);
            response.getWriter().flush();
        }

}

