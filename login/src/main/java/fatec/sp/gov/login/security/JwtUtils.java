package fatec.sp.gov.login.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;

public class JwtUtils {

    private static final String KEY = "br.gov.sp.fatec.springbootexample";

    public static String generateToken(Authentication user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Login userWithoutPassword = new Login();
        userWithoutPassword.setEmail(user.getName());
        if (!user.getAuthorities().isEmpty()) {
            List<String> authorizations = new ArrayList<String>();
            for(GrantedAuthority aut: user.getAuthorities()) {
                authorizations.add(aut.getAuthority());
            }
            userWithoutPassword.setAuthorities(authorizations);
        }
        String userJson = mapper.writeValueAsString(userWithoutPassword);
        Date now = new Date();
        Long hour = 1000L * 60L * 60L; // Uma hour
        return Jwts.builder()
                .claim("userDetails", userJson)
                .setIssuer("br.gov.sp.fatec")
                .setSubject(user.getName())
                .setExpiration(new Date(now.getTime() + hour))
                .signWith(Keys.hmacShaKeyFor(KEY.getBytes()), SignatureAlgorithm.HS256).compact();
    }

    public static Authentication parseToken(String token)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String credentialsJson = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).build()
                .parseClaimsJws(token).getBody().get("userDetails", String.class);
        Login user = mapper.readValue(credentialsJson, Login.class);
        UserDetails userDetails = User.builder().username(user.getEmail()).password("secret")
                .authorities(user.getAuthorities().toArray(new String[user.getAuthorities().size()])).build();
        return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                userDetails.getAuthorities());
    }
    public static String getKey() {
        return KEY;
    }

}