package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.domain.model.pool.track.PoolTrackingConfiguration;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.PoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolTrackingApplicationService {

    private final PoolTrackingConfigurationRepository repository;

    public PoolTrackingApplicationService(PoolTrackingConfigurationRepository repository) {
        this.repository = repository;
    }

    public PoolTrackingConfiguration configuration() {
        return repository.get();
    }

    public void saveTimeRange(TimeRange timeRange) {
        repository.saveTimeRange(timeRange);
    }

    public void saveAttendance(int attendance) {
        repository.saveAttendance(attendance);
    }

    public void enableTracking(boolean enable) {
        repository.enableTracking(enable);
    }
}
