package pe.edu.unmsm.bank.infrastructure.exception;

import pe.edu.unmsm.bank.infrastructure.Constants;

public class CustomerNotFoundException extends DomainException {

    public CustomerNotFoundException(Long id) {
        super(Constants.CUSTOMER_NOT_FOUND + id);
    }

}
