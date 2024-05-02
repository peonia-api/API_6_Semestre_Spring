package fatec.sp.gov.login.client.via.record;

import java.time.LocalDateTime;

public record Register(
        String id,
        OccurrenceType occurrence,
        LocalDateTime dateTime,
        String room
) {
    public enum OccurrenceType {
        ENTRANCE, EXIT
    }
}
