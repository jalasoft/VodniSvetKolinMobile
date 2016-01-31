package cz.jalasoft.mobile.swimming.infrastructure.pool.track;

import cz.jalasoft.mobile.swimming.domain.model.pool.track.PoolTrackingConfiguration;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.PoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/31/16.
 */
public final class CachingPoolTrackingConfigurationRepository implements PoolTrackingConfigurationRepository {

    private final PoolTrackingConfigurationRepository decorated;

    private PoolTrackingConfiguration configuration;

    public CachingPoolTrackingConfigurationRepository(PoolTrackingConfigurationRepository decorated) {
        this.decorated = decorated;
    }

    @Override
    public PoolTrackingConfiguration get() {
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
