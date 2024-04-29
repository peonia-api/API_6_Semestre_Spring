package fatec.sp.gov.login.security;

import java.util.List;

public class Login {

    private String email;

    private String password;

    private List<String> authorities;

    private String token;


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
    public List<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }


}