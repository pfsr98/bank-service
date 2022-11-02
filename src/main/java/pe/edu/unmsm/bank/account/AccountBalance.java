package pe.edu.unmsm.bank.account;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class AccountBalance {

    private final Long accountId;
    private final Double initialBalance;

}
