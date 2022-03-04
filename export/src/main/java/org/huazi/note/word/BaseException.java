package org.huazi.note.word;

public class BaseException extends RuntimeException {
    private static final long serialVersionUID = -8170023045088441807L;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
