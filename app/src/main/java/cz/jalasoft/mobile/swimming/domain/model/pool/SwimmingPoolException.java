package cz.jalasoft.mobile.swimming.domain.model.pool;

import java.io.IOException;
import java.net.URL;

/**
 * Created by lastovicka on 1/2/16.
 */
public abstract class SwimmingPoolException extends Exception {

    protected SwimmingPoolException(String detail) {
        super(detail);
    }

    protected SwimmingPoolException(Exception cause) {
        super(cause);
    }

}
