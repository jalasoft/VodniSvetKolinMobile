package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfiguration;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingService;
import cz.jalasoft.mobile.swimming.domain.model.track.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolTrackingApplicationService {

    private final PoolTrackingConfigurationRepository repository;
    private final PoolTrackingService trackingService;

    public PoolTrackingApplicationService(PoolTrackingConfigurationRepository repository, PoolTrackingService trackingService) {
        this.repository = repository;
        this.trackingService = trackingService;
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

        if (enable) {
            trackingService.startTracking();
        } else {
            trackingService.stopTracking();
        }
    }
}
