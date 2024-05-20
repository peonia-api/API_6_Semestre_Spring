package fatec.sp.gov.login.service;

import java.util.Optional;

import fatec.sp.gov.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<fatec.sp.gov.login.entity.User> usuarioOp = usuarioRepo.searchByEmail(username);

        if(usuarioOp.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        fatec.sp.gov.login.entity.User user = usuarioOp.get();

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(user.getPermissionType().name()).build();
    }

}