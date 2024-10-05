package fr.mowitnow.mowermanager.domain.exceptions;

public class TechnicalException extends RuntimeException {

    public TechnicalException(String message, Exception e) {
        super(message);
    }
}
