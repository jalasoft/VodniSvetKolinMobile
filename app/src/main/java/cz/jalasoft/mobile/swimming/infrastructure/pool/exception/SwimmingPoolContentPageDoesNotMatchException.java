package cz.jalasoft.mobile.swimming.infrastructure.pool.exception;

import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolException;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class SwimmingPoolContentPageDoesNotMatchException extends SwimmingPoolException {

    public SwimmingPoolContentPageDoesNotMatchException(String detail) {
        super(detail);
    }

    @Override
    public String getMessage() {
        return "Swimming pool page content has changed and it is not possible to read info. Detail: " + super.getMessage();
    }
}
