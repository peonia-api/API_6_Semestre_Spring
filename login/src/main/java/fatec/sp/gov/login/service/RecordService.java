package fatec.sp.gov.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import fatec.sp.gov.login.client.via.record.Viarecord;
import fatec.sp.gov.login.client.via.record.Register;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private Viarecord viarecord;

    @PreAuthorize("isAuthenticated()")
    public List<Register> findAllRecords() {
        return viarecord.getRecords("ALL");
    }
}
