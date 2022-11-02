package pe.edu.unmsm.bank.infrastructure.exception;

import pe.edu.unmsm.bank.infrastructure.Constants;

public class MovementNotFoundException extends DomainException {

    public MovementNotFoundException(Long id) {
        super(Constants.MOVEMENT_NOT_FOUND + id);
    }

}
