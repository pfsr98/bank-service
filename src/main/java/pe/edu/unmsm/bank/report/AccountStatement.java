package pe.edu.unmsm.bank.report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;
import pe.edu.unmsm.bank.infrastructure.Constants;

import java.time.LocalDateTime;

@Getter
@ToString
@Jacksonized
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountStatement {

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private final LocalDateTime date;

    private final String customer;
    private final String accountNumber;
    private final String accountType;
    private final Double initialBalance;
    private final Boolean status;
    private final Double movement;
    private final Double availableBalance;

}
