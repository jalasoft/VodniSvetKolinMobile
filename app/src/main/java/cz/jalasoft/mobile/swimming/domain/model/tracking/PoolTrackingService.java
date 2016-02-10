package cz.jalasoft.mobile.swimming.domain.model.tracking;

import java.util.Date;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.Optional;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class PoolTrackingService {

    private final PoolStatusService statusService;
    private final PoolTrackingDescriptorRepository configRepository;

    public PoolTrackingService(PoolStatusService statusService, PoolTrackingDescriptorRepository configRepository) {
        this.statusService = statusService;
        this.configRepository = configRepository;
    }

    public void performTracking(final AsyncCallback<Optional<PoolTracking>> callback) {
        final PoolTrackingDescriptor descriptor = configRepository.get();

        if (!canPerformTracking(descriptor)) {
            callback.process(Optional.<PoolTracking>empty());
        }

        statusService.getStatusAsynchronously(new AsyncCallback<PoolStatus>() {
            @Override
            public void process(PoolStatus poolStatus) {
                Optional<PoolTracking> tracking = performTracking(poolStatus, descriptor);
                callback.process(tracking);
            }

            @Override
            public void processFail(Exception exc) {
                callback.processFail(exc);
            }
        });
    }

    private boolean canPerformTracking(PoolTrackingDescriptor descriptor) {
        boolean isTrackingEnabled = descriptor.isEnabled();
        if (!isTrackingEnabled) {
            //this should never happen
            return false;
        }

        TimeOfDay time = TimeOfDay.from(new Date());
        boolean isTimeToTrack = descriptor.currentTimeRange().isTimeInRange(time);

        return isTimeToTrack;
    }

    private Optional<PoolTracking> performTracking(PoolStatus poolStatus, PoolTrackingDescriptor descriptor) {
        if (!poolStatus.isOpen()) {
            //this should never happen
            return Optional.empty();
        }

        int attendanceBoundary = descriptor.currentAttendanceBoundary();
        int currentAttendance = poolStatus.attendanceTotal();

        return Optional.empty();
    }
}
