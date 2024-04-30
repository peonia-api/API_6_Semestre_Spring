package fatec.sp.gov.login.client.via.record;

import java.time.LocalDateTime;

public record Register(
        String id,
        String occurrence,
        LocalDateTime dateTime,
        String room
){}
