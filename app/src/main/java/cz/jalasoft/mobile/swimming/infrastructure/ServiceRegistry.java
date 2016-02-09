package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;

import cz.jalasoft.mobile.swimming.application.PoolApplicationService;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingRepository;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.CachingPoolTrackingRepository;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.SharedPreferencesPoolTrackingRepository;
import cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.HttpPagePoolStatusService;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class ServiceRegistry {

    private PoolApplicationService poolTrackingService;

    public void init(Context context) {

        PoolTrackingRepository poolTrackingRepository = poolTrackingRepository(context);

        this.poolTrackingService = new PoolApplicationService(
                new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx"),
                poolTrackingRepository,
                new AlarmManagerScheduler(context)
        );
    }

    private PoolTrackingRepository poolTrackingRepository(Context context) {
        return new CachingPoolTrackingRepository(
                new SharedPreferencesPoolTrackingRepository(context));
    }

    public PoolApplicationService applicationService() {
        return poolTrackingService;
    }
}
