package cz.jalasoft.mobile.swimming.domain.model.pool.status;

import java.io.IOException;
import java.net.URL;

/**
 * Created by lastovicka on 1/2/16.
 */
public abstract class PoolException extends Exception {

    protected PoolException(String detail) {
        super(detail);
    }

    protected PoolException(Exception cause) {
        super(cause);
    }

}
