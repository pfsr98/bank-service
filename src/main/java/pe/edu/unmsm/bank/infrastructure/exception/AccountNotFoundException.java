package pe.edu.unmsm.bank.infrastructure.exception;

import pe.edu.unmsm.bank.infrastructure.Constants;

public class AccountNotFoundException extends DomainException {

    public AccountNotFoundException(Long id) {
        super(Constants.ACCOUNT_NOT_FOUND + id);
    }

}
