package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.PasswordResetToken;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
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
        String code = String.format("%06d", new Random().nextInt(999999)); // Generate 6-digit code
        token.setCode(code);
        token.setExpirationDate(new Date(System.currentTimeMillis() + 3600000)); // 1 hour expiration
        token.setUser(user);
        tokenRepository.save(token);

        emailService.sendPasswordResetEmail(user.getEmail(), code);
    }

    public PasswordResetToken validatePasswordResetToken(String code) {
        PasswordResetToken resetToken = tokenRepository.findByCode(code);
        if (resetToken == null || resetToken.getExpirationDate().before(new Date())) {
            throw new RuntimeException("Invalid or expired code");
        }
        return resetToken;
    }

    public void resetPassword(String code, String newPassword) {
        PasswordResetToken resetToken = validatePasswordResetToken(code);
        User user = resetToken.getUser();
        userService.updatePassword(user, newPassword);
        tokenRepository.delete(resetToken); // Remove the token after password reset
    }
}
