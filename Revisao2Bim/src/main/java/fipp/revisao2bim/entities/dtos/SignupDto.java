package fipp.revisao2bim.entities.dtos;

public class SignupDto {

    public String email;
    public String name;
    public String password;
    public Integer role;

    public SignupDto(String email, String name, String password, Integer role) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public SignupDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
