package cz.jalasoft.mobile.swimming;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class ValueOrException<T> {

    public static <T> ValueOrException<T> success(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        return new ValueOrException<T>(value, null);
    }

    public static <T> ValueOrException<T> exception(Exception exc) {
        if (exc == null) {
            throw new IllegalArgumentException("Exception must not be null.");
        }
        return new ValueOrException<T>(null, exc);
    }

    //-----------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------

    private final T value;
    private final Exception error;

    private ValueOrException(T value, Exception error) {
        this.value = value;
        this.error = error;
    }

    public T value() {
        if (value == null) {
            throw new IllegalStateException("Value not available.");
        }
        return value;
    }

    public boolean isSuccess() {
        return value != null;
    }

    public boolean isError() {
        return error != null;
    }

    public Exception exception() {
        if (error == null) {
            throw new IllegalStateException("Error not available.");
        }
        return error;
    }
}
