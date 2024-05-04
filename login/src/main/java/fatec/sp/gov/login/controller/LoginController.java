package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.security.JwtUtils;
import fatec.sp.gov.login.security.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authManager;

    @PostMapping
    public Login autenticar(@RequestBody Login login) throws JsonProcessingException {
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
            auth = authManager.authenticate(auth); // Autentica o usuário
            login.setToken(JwtUtils.generateToken(auth)); // Gera o token após a autenticação bem-sucedida
            login.setPassword(null); // Limpa a senha por segurança
            return login;
        } catch (Exception e) {
            throw new RuntimeException("Falha na autenticação", e);
        }
    }

    @GetMapping
    public String message() {
        return "Login page. Use POST.";
    }
}
