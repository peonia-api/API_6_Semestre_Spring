package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.Authorization;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.AuthorizationRepository;
import fatec.sp.gov.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorizationRepository autRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(User user) {
        if (user == null || user.getName() == null || user.getName().isBlank() ||
                user.getSurname() == null || user.getSurname().isBlank() ||
                user.getEmail() == null || user.getEmail().isBlank() ||
                user.getFunction() == null || user.getFunction().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        if(!user.getAuthorizations().isEmpty()) {
            Set<Authorization> autorizations = new HashSet<Authorization>();
            for(Authorization aut: user.getAuthorizations()) {
                if(aut.getName() != null && !aut.getName().isBlank()) {
                    Authorization autBd = autRepo.findByName(aut.getName());
                    if(autBd == null) {
                        autBd = autRepo.save(new Authorization(aut.getName()));
                    }
                    autorizations.add(autBd);
                }
            }
            user.setAuthorizations(autorizations);
        }

        return userRepo.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User findById(Long id) {
        Optional<User> userOption = userRepo.findById(id);
        if (userOption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        return userOption.get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        userRepo.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(Long id, User user) {
        if (user == null || user.getName() == null || user.getName().isBlank() ||
                user.getSurname() == null || user.getSurname().isBlank() ||
                user.getEmail() == null || user.getEmail().isBlank() ||
                user.getFunction() == null || user.getFunction().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data for update");
        }

        return userRepo.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setEmail(user.getEmail());
            existingUser.setFunction(user.getFunction());
            existingUser.setPassword(user.getPassword());
            return userRepo.save(existingUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }

}