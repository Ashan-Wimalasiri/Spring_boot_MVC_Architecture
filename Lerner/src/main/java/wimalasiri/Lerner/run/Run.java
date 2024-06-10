package wimalasiri.Lerner.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

// This is model in mvc approach

public record Run(
    //validation constraint
    @NotEmpty
    Integer id,
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    //validation contraint
    @Positive
    Integer miles,
    Location location
) {
    public Integer getId() {
        return this.id;
    }
}
