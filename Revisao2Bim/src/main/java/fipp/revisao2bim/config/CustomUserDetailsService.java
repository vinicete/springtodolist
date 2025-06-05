package fipp.revisao2bim.config;

import fipp.revisao2bim.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserDetailsService implements UserDetailsService{

    private IUserRepository userRepository;

    public CustomUserDetailsService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws Exception {
        var us = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(String.valueOf(usuario.getNivel())) // ou authorities se preferir
                .build();
    }
}
