package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;

import cz.jalasoft.mobile.swimming.domain.model.expectation.SwimmingPoolExpectation;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.pool.WebPageSwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.tracking.SharedPreferencesSwimmingPoolExpectation;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class DomainRegistry {

    private SwimmingPoolService swimmingPoolService;
    private SwimmingPoolExpectation swimmingPoolExpectation;

    public void init(Context context) {
        this.swimmingPoolService = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");
        this.swimmingPoolExpectation = new SharedPreferencesSwimmingPoolExpectation(context);
    }

    /**
     * Gets a domain service whose responsibility is to provide a snapshot of a swimming pool status.
     * @return never null
     */
    public SwimmingPoolService swimmingPoolService() {
        return swimmingPoolService;
    }

    public SwimmingPoolExpectation swimmingPoolExpectation() {
        return swimmingPoolExpectation;
    }
}
