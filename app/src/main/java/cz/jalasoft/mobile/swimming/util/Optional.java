package cz.jalasoft.mobile.swimming.util;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public final class Optional<T> {

    public static <T> Optional<T> of(T value) {
        if (value == null) {
            throw new IllegalArgumentException("Value must not be null.");
        }
        return new Optional<>(value);
    }

    public static <T> Optional<T> empty() {
        return new Optional<>(null);
    }

    private final T value;

    private Optional(T value) {
        this.value = value;
    }

    public T get() {
        if (isNotPresent()) {
            throw new IllegalStateException("Value is not present.");
        }
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public boolean isNotPresent() {
        return value == null;
    }
}
