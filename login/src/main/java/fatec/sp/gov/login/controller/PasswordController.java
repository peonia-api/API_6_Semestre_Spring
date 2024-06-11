package fatec.sp.gov.login.controller;

import fatec.sp.gov.login.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/password")
@CrossOrigin
public class PasswordController {

    @Autowired
    private PasswordResetTokenService tokenService;

    @PostMapping("/request-reset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody Map<String, String> emailMap) {
        String email = emailMap.get("email");
        tokenService.createPasswordResetToken(email);
        return ResponseEntity.ok("Password reset email sent");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestBody Map<String, String> passwordMap) {
        String newPassword = passwordMap.get("newPassword");
        tokenService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password has been reset");
    }
}
