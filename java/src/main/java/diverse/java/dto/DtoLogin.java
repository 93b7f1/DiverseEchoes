package diverse.java.dto;

import diverse.java.security.Token;

public class DtoLogin {

    private Integer id;
    private String username;
    private Token token;

    public DtoLogin(Integer id, String username, Token token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }



}