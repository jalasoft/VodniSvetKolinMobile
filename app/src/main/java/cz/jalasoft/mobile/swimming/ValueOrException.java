package cz.jalasoft.mobile.swimming;

/**
 * A value that can be either success (value) or fail (exception)
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class ValueOrException<T, E extends Exception> {

    public static <T, E extends Exception> ValueOrException<T, E> success(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        return new ValueOrException<T, E>(value, null);
    }

    public static <T, E extends Exception> ValueOrException<T, E> exception(E exc) {
        if (exc == null) {
            throw new IllegalArgumentException("Exception must not be null.");
        }
        return new ValueOrException<T, E>(null, exc);
    }

    //-----------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------

    private final T value;
    private final E exception;

    private ValueOrException(T value, E error) {
        this.value = value;
        this.exception = error;
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

    public boolean isFail() {
        return value == null;
    }

    public E exception() {
        if (exception == null) {
            throw new IllegalStateException("Error not available.");
        }
        return exception;
    }
}
