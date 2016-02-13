package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptor;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptorRepository;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingPolicy;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingPublisher;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingService;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeRange;
import cz.jalasoft.mobile.swimming.infrastructure.AlarmManagerScheduler;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolApplicationService {

    private final PoolTrackingDescriptorRepository repository;
    private final PoolTrackingService trackingService;
    private final PoolStatusService statusService;
    private final PoolTrackingPublisher publisher;
    private final PoolTrackingPolicy trackingPolicy;

    private AlarmManagerScheduler scheduler;

    public PoolApplicationService(
            PoolStatusService statusService,
            PoolTrackingDescriptorRepository repository,
            PoolTrackingPublisher publisher,
            PoolTrackingPolicy trackingPolicy,
            AlarmManagerScheduler scheduler)
    {

        this.statusService = statusService;
        this.repository = repository;
        this.trackingService = new PoolTrackingService(statusService, repository);
        this.publisher = publisher;
        this.trackingPolicy = trackingPolicy;
        this.scheduler = scheduler;
    }

    public void poolStatus(final AsyncCallback<PoolStatus> callback) {
        this.statusService.getStatusAsynchronously(callback);
    }

    public PoolTrackingDescriptor trackingConfiguration() {
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
            trackingPolicy.trackingEnabled();

            long intervalMillis = repository.get().trackingIntervalMillis();
            scheduler.scheduleRepeating(intervalMillis);
        } else {
            scheduler.unschedule();
        }
    }

    public void performTracking() {
        PoolTrackingDescriptor descriptor = repository.get();

        if (!trackingPolicy.canPoolBeTracked(descriptor)) {
            return;
        }

        trackingService.performTracking(new AsyncCallback<PoolTracking>() {
            @Override
            public void process(PoolTracking value) {

                if (trackingPolicy.canPoolTrackingBePublished(value)) {
                    publisher.publishPoolTracking(value);
                }
            }

            @Override
            public void processFail(Exception exc) {
                publisher.publishPoolTrackingError(exc);
            }
        });
    }
}
