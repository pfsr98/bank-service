package pe.edu.unmsm.bank.movement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import pe.edu.unmsm.bank.account.Account;
import pe.edu.unmsm.bank.infrastructure.Constants;
import pe.edu.unmsm.bank.infrastructure.validation.EnumValidator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@ToString
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Movement {

    private final Long movementId;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private final LocalDateTime date;

    @NotNull
    @EnumValidator(enumClass = MovementType.class)
    private final String movementType;

    @NotNull
    @Min(0)
    private final Double value;

    private final Double balance;

    @NotNull
    private final Account account;

}
