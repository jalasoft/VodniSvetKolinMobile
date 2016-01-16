package cz.jalasoft.mobile.swimming.infrastructure;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.service.WebPageSwimmingPoolService;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class DomainRegistry {

    private SwimmingPoolService swimmingPoolService;

    public void init() {
        this.swimmingPoolService = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");
    }

    /**
     * Gets a domain service whose responsibility is to provide a snapshot of a swimming pool status.
     * @return never null
     */
    public SwimmingPoolService swimmingPoolService() {
        return swimmingPoolService;
    }
}
