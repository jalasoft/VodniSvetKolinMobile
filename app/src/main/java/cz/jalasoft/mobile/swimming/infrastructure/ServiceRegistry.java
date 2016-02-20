package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;

import cz.jalasoft.mobile.swimming.application.PoolApplicationService;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptorRepository;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.CachingPoolTrackingDescriptorRepository;
import cz.jalasoft.mobile.swimming.infrastructure.persistence.SharedPreferencesPoolTrackingDescritorRepository;
import cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.HttpPagePoolStatusService;
import cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.dummy.DummyPoolStatusService;

/**
 * A registry of domain objects.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class ServiceRegistry {

    private PoolApplicationService poolTrackingService;

    public void init(Context context) {

        PoolTrackingDescriptorRepository poolTrackingRepository = poolTrackingRepository(context);

        this.poolTrackingService = new PoolApplicationService(
                new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx"),
                //new DummyPoolStatusService(),
                poolTrackingRepository,
                new NotificationPoolTrackingPublisher(context),
                new SharedPreferencesPoolTrackingPolicy(context),
                new AlarmManagerScheduler(context)
        );
    }

    private PoolTrackingDescriptorRepository poolTrackingRepository(Context context) {
        return new CachingPoolTrackingDescriptorRepository(
                new SharedPreferencesPoolTrackingDescritorRepository(context)
        );
    }

    public PoolApplicationService applicationService() {
        return poolTrackingService;
    }
}
