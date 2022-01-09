package de.silencio.activecraftcore.exceptions;

public class InvalidArgumentException extends ActiveCraftException{

    private ErrorType errorType;

    public InvalidArgumentException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public InvalidArgumentException(ErrorType errorType) {
        this("Invalid arguments.", errorType);
    }

    public InvalidArgumentException() {
        this("Invalid arguments.", ErrorType.DEFAULT);
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public enum ErrorType {
        DEFAULT,
        INCLUDE_MESSAGE
    }

}
