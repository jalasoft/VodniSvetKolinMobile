package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;

import cz.jalasoft.mobile.swimming.application.PoolStatusApplicationService;
import cz.jalasoft.mobile.swimming.application.PoolTrackingApplicationService;
import cz.jalasoft.mobile.swimming.infrastructure.pool.status.HttpPagePoolStatusService;
import cz.jalasoft.mobile.swimming.infrastructure.pool.track.CachingPoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.infrastructure.pool.track.SharedPreferencesPoolTrackingConfigurationRepository;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class ServiceRegistry {

    private PoolStatusApplicationService poolStatusService;
    private PoolTrackingApplicationService poolTrackingService;

    public void init(Context context) {
        this.poolStatusService = new PoolStatusApplicationService(new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx"));
        this.poolTrackingService = new PoolTrackingApplicationService(
                new CachingPoolTrackingConfigurationRepository(
                        new SharedPreferencesPoolTrackingConfigurationRepository(context))
        );
    }

    /**
     * Gets a domain service whose responsibility is to provide a snapshot of a swimming pool status.
     * @return never null
     */
    public PoolStatusApplicationService poolStatusService() {
        return poolStatusService;
    }

    public PoolTrackingApplicationService poolTrackingService() {
        return poolTrackingService;
    }
}
