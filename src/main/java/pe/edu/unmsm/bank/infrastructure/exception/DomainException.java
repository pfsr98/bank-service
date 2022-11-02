package pe.edu.unmsm.bank.infrastructure.exception;

public class DomainException extends RuntimeException {

    public DomainException(String message) {
        super(message);
    }

}
