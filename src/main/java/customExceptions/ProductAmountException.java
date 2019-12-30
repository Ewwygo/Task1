package customExceptions;

public final class ProductAmountException extends RuntimeException {
    public ProductAmountException(String s,int i) {
        super("Amount of product - " + s + " is less than " + i);

    }
}
