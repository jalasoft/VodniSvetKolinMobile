package cz.jalasoft.mobile.swimming.util;

/**
 * Created by Honza "Honzales" Lastovicka on 1/10/16.
 */
public interface AsyncCallback<T> {

    void process(T value);

    void processFail(Exception exc);

}
