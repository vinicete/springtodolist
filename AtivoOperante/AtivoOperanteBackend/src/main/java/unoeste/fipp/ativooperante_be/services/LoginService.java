package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.config.JwtUtil;
import unoeste.fipp.ativooperante_be.domain.dtos.login.SigninDto;
import unoeste.fipp.ativooperante_be.domain.dtos.login.SignupCompletedDto;
import unoeste.fipp.ativooperante_be.domain.dtos.login.SignupDto;
import unoeste.fipp.ativooperante_be.domain.dtos.login.TokenDto;
import unoeste.fipp.ativooperante_be.domain.entities.Usuario;
import unoeste.fipp.ativooperante_be.repositories.UsuarioRepository;

@Service
public class LoginService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired private AuthenticationManager authenticationManager;  // Gerencia a autenticação


    public SignupCompletedDto signup(SignupDto input) throws Exception {

        try{
            Usuario usuario = new Usuario();
            usuario.setCpf(input.cpf);
            usuario.setEmail(input.email);
            usuario.setNome(input.nome);
            usuario.setNivel(input.nivel);
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setSenha(encoder.encode(input.senha));

            var usu = usuarioRepository.save(usuario);

            var us = new SignupCompletedDto();
            us.setCpf(usu.getCpf());
            us.setEmail(usu.getEmail());
            us.setId(usu.getId());
            us.setNome(usu.getNome());
            us.setNivel(usu.getNivel());
            return us;
        }
        catch (Exception ex){
            throw new Exception("Erro ao cadastrar usuário: " + ex.getMessage(), ex);
        }
    }

    public TokenDto signin(SigninDto input) throws Exception {

        try {
            System.out.println("Attempting to authenticate user: " + input.getEmail());
            // Autentica o usuário
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getSenha())
            );
            System.out.println("Authentication successful for user: " + input.getEmail());

            var us = usuarioRepository.findByEmail(input.getEmail())
                    .orElseThrow(() -> new Exception("Usuário não encontrado"));

            // Se a autenticação for bem-sucedida, gera o token
            String token = JwtUtil.getToken(input.getEmail(), String.valueOf(us.getNivel()));
            System.out.println("Generated token: " + token);
            return new TokenDto(token, us);
        } catch (Exception ex) {
            System.out.println("Error during signin: " + ex.getMessage());
            throw new Exception("Usuário ou senha inválidos");
        }
    }
}
