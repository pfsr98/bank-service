package pe.edu.unmsm.bank.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import pe.edu.unmsm.bank.infrastructure.Constants;
import pe.edu.unmsm.bank.infrastructure.validation.ValidationError;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private final LocalDateTime timestamp;
    private final String path;
    private final String error;
    private final String message;
    private final List<ValidationError> validationErrors;

}
