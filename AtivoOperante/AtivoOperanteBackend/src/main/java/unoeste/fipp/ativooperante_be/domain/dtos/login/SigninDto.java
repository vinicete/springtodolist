package unoeste.fipp.ativooperante_be.domain.dtos.login;

public class SigninDto {
    public String email;
    public String senha;

    public SigninDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
