package fatec.sp.gov.login.service;

import fatec.sp.gov.login.entity.Authorization;
import fatec.sp.gov.login.entity.User;
import fatec.sp.gov.login.repository.AuthorizationRepository;
import fatec.sp.gov.login.repository.UserRepository;
import fatec.sp.gov.login.client.via.record.Viarecord;
import fatec.sp.gov.login.client.via.record.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private Viarecord viarecord;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthorizationRepository autRepo;

    @PreAuthorize("isAuthenticated()")
    public List<Register> findAllRecords() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepo.findByName(currentUserName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!user.getAuthorizations().isEmpty()) {
            return viarecord.getRecords("ALL");
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have any authorizations");
        }
    }
}
