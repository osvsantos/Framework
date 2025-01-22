package utils.exceptions;

public class HeadlessNotSupportedException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public HeadlessNotSupportedException(String message) {
        super(message);
    }
}
