package cz.jalasoft.mobile.swimming.infrastructure;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.webpage.WebPageSwimmingPoolService;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class DomainRegistry {

    private static final SwimmingPoolService SWIMMING_POOL_SERVICE = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");

    /**
     * Gets a domain service whose responsibility is to provide a snapshot of a swimming pool status.
     * @return never null
     */
    public static SwimmingPoolService swimmingPoolService() {
        return SWIMMING_POOL_SERVICE;
    }
}
