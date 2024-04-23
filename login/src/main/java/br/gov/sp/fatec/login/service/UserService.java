package br.gov.sp.fatec.login.service;

import br.gov.sp.fatec.login.entity.User;
import br.gov.sp.fatec.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User createUser(User user) {
        if (user == null || user.getName() == null || user.getName().isBlank() ||
                user.getSurname() == null || user.getSurname().isBlank() ||
                user.getEmail() == null || user.getEmail().isBlank() ||
                user.getFunction() == null || user.getFunction().isBlank() ||
                user.getPassword() == null || user.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados inválidos!");
        }
        return userRepo.save(user);
    }

    public User findById(Long id) {
        Optional<User> userOption = userRepo.findById(id);
        if (userOption.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        return userOption.get();
    }

    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
        }
        userRepo.deleteById(id);
    }

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
