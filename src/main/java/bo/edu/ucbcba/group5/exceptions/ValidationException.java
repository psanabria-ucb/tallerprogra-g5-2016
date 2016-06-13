package bo.edu.ucbcba.group5.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Error de validacion: " + message);
    }
}
