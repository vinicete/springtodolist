package fipp.revisao2bim.entities.dtos;

public class SigninDto {

    public String email;
    public String password;

    public SigninDto(String email,  String password) {
        this.email = email;
        this.password = password;
    }

    public SigninDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
