package unoeste.fipp.ativooperante_be.domain.dtos.login;

import unoeste.fipp.ativooperante_be.domain.entities.Usuario;

public class TokenDto {

    private String token;
    private Usuario usuario;

    public TokenDto(String token, Usuario user) {
        this.token = token;
        this.usuario = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
