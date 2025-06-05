package fipp.revisao2bim.entities.dtos;

import fipp.revisao2bim.entities.User;

public class TokenDto {

    private String token;
    private User user;

    public TokenDto(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
