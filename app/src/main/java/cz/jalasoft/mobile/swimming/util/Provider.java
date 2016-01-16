package cz.jalasoft.mobile.swimming.util;

/**
 * Created by Honza "Honzales" Lastovicka on 1/16/16.
 */
public interface Provider<T> {

    T get() throws Exception;

}
