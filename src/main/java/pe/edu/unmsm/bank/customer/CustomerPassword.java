package pe.edu.unmsm.bank.customer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class CustomerPassword {

    private final Long customerId;
    private final String password;

}
