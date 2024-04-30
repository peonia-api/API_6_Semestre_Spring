package fatec.sp.gov.login.client.via.record;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(url = "http://localhost:8082", name = "viarecord")
public interface Viarecord {
    @GetMapping("/record/all")
    List<Register> getRecords();
}
