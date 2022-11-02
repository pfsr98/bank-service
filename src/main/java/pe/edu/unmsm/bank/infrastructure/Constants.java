package pe.edu.unmsm.bank.infrastructure;

public class Constants {

    private Constants() {
        throw new IllegalStateException(Constants.UTILITY_CLASS);
    }

    public static final String CUSTOMER_NOT_FOUND = "Cliente no encontrado con id: ";
    public static final String ACCOUNT_NOT_FOUND = "Cuenta no encontrada con id: ";
    public static final String MOVEMENT_NOT_FOUND = "Movimiento no encontrado con id: ";
    public static final String BALANCE_UNAVAILABLE = "Saldo no disponible";
    public static final String DAILY_QUOTA_EXCEEDED = "Cupo diario excedido";
    public static final String INVALID_ENUM = "Debe ser un tipo válido";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String UTILITY_CLASS = "Utility class";

}
