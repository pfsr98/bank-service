package pe.edu.unmsm.bank.movement;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class MovementValue {

    private Long movementId;
    private Double value;

}
