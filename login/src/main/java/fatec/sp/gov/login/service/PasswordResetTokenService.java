package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.PasswordResetToken;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public void createPasswordResetToken(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setExpirationDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiration
        token.setUser(user);
        tokenRepository.save(token);

        emailService.sendPasswordResetEmail(user.getEmail(), token.getToken());
    }

    public PasswordResetToken validatePasswordResetToken(String token) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null || resetToken.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Invalid or expired token");
        }
        return resetToken;
    }

    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = validatePasswordResetToken(token);
        User user = resetToken.getUser();
        userService.updatePassword(user, newPassword);
        tokenRepository.delete(resetToken); // Remove o token após a redefinição de senha
    }
}
