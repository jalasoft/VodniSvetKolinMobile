package cz.jalasoft.mobile.swimming.infrastructure.persistence;

import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingRepository;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/31/16.
 */
public final class CachingPoolTrackingRepository implements PoolTrackingRepository {

    private final PoolTrackingRepository decorated;

    private PoolTracking configuration;

    public CachingPoolTrackingRepository(PoolTrackingRepository decorated) {
        this.decorated = decorated;
    }

    @Override
    public PoolTracking get() {
        if (configuration != null) {
            return configuration;
        }

        configuration = decorated.get();
        return configuration;
    }

    @Override
    public void enableTracking(boolean enabled) {
        configuration = configuration.enable(enabled);
        decorated.enableTracking(enabled);
    }

    @Override
    public void saveTimeRange(TimeRange timeRange) {
        configuration = configuration.withCurrentTimeRange(timeRange);
        decorated.saveTimeRange(timeRange);
    }

    @Override
    public void saveAttendance(int attendance) {
        configuration = configuration.withCurrentAttendanceBoundary(attendance);
        decorated.saveAttendance(attendance);
    }
}
