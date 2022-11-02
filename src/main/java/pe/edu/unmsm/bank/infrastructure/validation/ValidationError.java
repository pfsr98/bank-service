package pe.edu.unmsm.bank.infrastructure.validation;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ValidationError {

    private final String field;
    private final String message;

}
