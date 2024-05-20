package fatec.sp.gov.login.client.via.record;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(contextId = "viarecord", name = "viarecord", url = "http://localhost:8082")
public interface Viarecord {
    @GetMapping("/record/all")
    @PreAuthorize("isAuthenticated()")
    List<Register> getRecords(@RequestParam(value = "occurrence", defaultValue = "ALL") String occurrence);
}
