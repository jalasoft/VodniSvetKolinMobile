package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;

import cz.jalasoft.mobile.swimming.application.PoolApplicationService;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingService;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.CachingPoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.SharedPreferencesPoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.HttpPagePoolStatusService;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class ServiceRegistry {

    private PoolApplicationService poolTrackingService;

    public void init(Context context) {

        PoolStatusService statusService = new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx");

        this.poolTrackingService = new PoolApplicationService(
                new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx"),
                configurationRepository(context),
                applicationService(statusService),
                new AlarmManagerScheduler(context)
        );
    }

    private PoolTrackingService applicationService(PoolStatusService statusService) {
        return new PoolTrackingService(statusService);
    }

    private PoolTrackingConfigurationRepository configurationRepository(Context context) {
        return new CachingPoolTrackingConfigurationRepository(
                new SharedPreferencesPoolTrackingConfigurationRepository(context));
    }

    public PoolApplicationService applicationService() {
        return poolTrackingService;
    }
}
