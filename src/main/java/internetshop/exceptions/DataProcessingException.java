package internetshop.exceptions;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String msg, Throwable o) {
        super(msg);
    }
}
