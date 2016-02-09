package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfiguration;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfigurationRepository;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingService;
import cz.jalasoft.mobile.swimming.domain.model.track.TimeRange;
import cz.jalasoft.mobile.swimming.infrastructure.AlarmManagerScheduler;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolApplicationService {

    private final PoolTrackingConfigurationRepository repository;
    private final PoolTrackingService trackingService;
    private final PoolStatusService statusService;

    private AlarmManagerScheduler scheduler;

    public PoolApplicationService(PoolStatusService statusService, PoolTrackingConfigurationRepository repository, PoolTrackingService trackingService, AlarmManagerScheduler scheduler) {
        this.statusService = statusService;
        this.repository = repository;
        this.trackingService = trackingService;

        this.scheduler = scheduler;
    }

    public void poolStatus(final AsyncCallback<PoolStatus> callback) {
        this.statusService.getStatusAsynchronously(callback);
    }

    public PoolTrackingConfiguration trackingConfiguration() {
        return repository.get();
    }

    public void saveTrackingTime(TimeRange timeRange) {
        repository.saveTimeRange(timeRange);
    }

    public void saveAttendanceBoundary(int attendance) {
        repository.saveAttendance(attendance);
    }

    public void enableTracking(boolean enable) {
        repository.enableTracking(enable);

        if (enable) {
            scheduler.scheduleRepeating(5000);
        } else {
            scheduler.unschedule();
        }
    }

    public void performTrackingStep() {
        boolean isTrackingEnabled = repository.get().isEnabled();
        if (!isTrackingEnabled) {
            //this should never happen
            return;
        }

        PoolTrackingConfiguration configuration = repository.get();
        trackingService.performTrackingStep(configuration);
    }
}
