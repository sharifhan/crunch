package crunch.exception;

public class CrunchException extends RuntimeException {
	/*
	 * An appropriate error response is returned if any validation fails.
	 * 
	 * */
    public CrunchException(final String message) {
        super(message);
    }
}
