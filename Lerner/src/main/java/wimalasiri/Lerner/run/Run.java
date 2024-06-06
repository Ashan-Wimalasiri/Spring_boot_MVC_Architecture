package wimalasiri.Lerner.run;

import java.time.LocalDateTime;

// This is model in mvc approach

public record Run(
    Integer id,
    String title,
    LocalDateTime startedOn,
    LocalDateTime endedOn,
    Integer miles,
    Location location
) {
    
}
