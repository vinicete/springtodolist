package fipp.revisao2bim.services;

import fipp.revisao2bim.entities.User;
import fipp.revisao2bim.entities.dtos.SigninDto;
import fipp.revisao2bim.entities.dtos.SignupCompletedDto;
import fipp.revisao2bim.entities.dtos.SignupDto;
import fipp.revisao2bim.entities.dtos.TokenDto;
import fipp.revisao2bim.repositories.IUserRepository;

public class AuthService {

    private IUserRepository userRepository;

    public AuthService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    public SignupCompletedDto signup(SignupDto input) throws Exception {

        try{
            User usuario = new User();
            usuario.setEmail(input.email);
            usuario.setName(input.name);
            usuario.setRole(input.role);
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setPassword(encoder.encode(input.password));

            var usu = userRepository.save(usuario);

            var us = new SignupCompletedDto();
            us.setEmail(usu.getEmail());
            us.setId(usu.getId());
            us.setName(usu.getName());
            us.setRole(usu.getRole());
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
                    new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword())
            );
            System.out.println("Authentication successful for user: " + input.getEmail());

            var us = userRepository.findByEmail(input.getEmail())
                    .orElseThrow(() -> new Exception("Usuário não encontrado"));

            // Se a autenticação for bem-sucedida, gera o token
            String token = JwtUtil.getToken(input.getEmail(), String.valueOf(us.getRole()));
            System.out.println("Generated token: " + token);
            return new TokenDto(token, us);
        } catch (Exception ex) {
            System.out.println("Error during signin: " + ex.getMessage());
            throw new Exception("Usuário ou senha inválidos");
        }
    }
}
