package cz.jalasoft.mobile.swimming.infrastructure.webpage.exception;

import java.io.IOException;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class SwimmingPoolNotAvailableException extends SwimmingPoolException {

    private final URL pageURL;

    public SwimmingPoolNotAvailableException(URL pageURL, IOException cause) {
        super(cause);

        this.pageURL = pageURL;
    }

    public URL url() {
        return pageURL;
    }

    @Override
    public String getMessage() {
        String message = "An exception occurred during accessing page [%s]. Detail: [%s]";
        return String.format(message, pageURL, getCause().getMessage());
    }
}
