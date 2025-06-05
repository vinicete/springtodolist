package fipp.revisao2bim.entities.dtos;

public class SignupCompletedDto {

    public Integer id;
    public String email;
    public String name;
    public Integer role;

    public SignupCompletedDto(String email, String name, Integer role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public SignupCompletedDto() {
    }

    public String getEmail() {
        return email;
    }

    public SignupCompletedDto(Integer id, String email, String name, Integer role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
