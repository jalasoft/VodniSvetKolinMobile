package cz.jalasoft.mobile.swimming.infrastructure;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.webpage.WebPageSwimmingPoolService;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class ServiceRegistry {

    private static final SwimmingPoolService SWIMMING_POOL_SERVICE = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");

    public static SwimmingPoolService swimmingPoolService() {
        return SWIMMING_POOL_SERVICE;
    }
}
