package fatec.sp.gov.login.service;


import fatec.sp.gov.login.entity.Authorization;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.AuthorizationRepository;
import fatec.sp.gov.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorizationRepository autRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PreAuthorize("isAuthenticated()")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepo.searchByEmail(currentUserName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if(!user.getAuthorizations().isEmpty()) {
            Set<Authorization> autorizations = new HashSet<>();
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

        return user;
    }

    @PreAuthorize("hasRole('ADMIN')")
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid data");
        }
        if (!user.getAuthorizations().isEmpty()) {
            Set<Authorization> authorizations = new HashSet<>();
            for (Authorization auth : user.getAuthorizations()) {
                if (auth.getName() != null && !auth.getName().isBlank()) {
                    Authorization authDb = autRepo.findByName(auth.getName());
                    if (authDb == null) {
                        authDb = new Authorization(auth.getName(), auth.getPermissionType());
                        authDb = autRepo.save(authDb);
                    } else {
                        authDb.setPermissionType(auth.getPermissionType());
                    }
                    authorizations.add(authDb);
                }
            }
            user.setAuthorizations(authorizations);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public User findById(UUID id) {
        Optional<User> userOption = userRepo.findById(id);
        if (userOption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }

        return userOption.get();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(UUID id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        userRepo.deleteById(id);
        return "Removed user with success: ";
    }

    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(UUID id, User user) {
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
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));

            if (!user.getAuthorizations().isEmpty()) {
                Set<Authorization> updatedAuthorizations = new HashSet<>();
                for (Authorization aut : user.getAuthorizations()) {
                    Authorization autBd = autRepo.findByName(aut.getName());
                    if (autBd == null) {
                        autBd = autRepo.save(new Authorization(aut.getName()));
                    }
                    updatedAuthorizations.add(autBd);
                }
                existingUser.setAuthorizations(updatedAuthorizations);
            }

            return userRepo.save(existingUser);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    }


}