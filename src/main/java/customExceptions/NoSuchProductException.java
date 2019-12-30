package customExceptions;

public final class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(String s) {
        super("There is no such product - " + s);
    }

}
