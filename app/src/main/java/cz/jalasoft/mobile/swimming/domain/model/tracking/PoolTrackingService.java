package cz.jalasoft.mobile.swimming.domain.model.tracking;

import java.util.Date;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class PoolTrackingService {

    private final PoolStatusService statusService;
    private final PoolTrackingRepository configRepository;

    public PoolTrackingService(PoolStatusService statusService, PoolTrackingRepository configRepository) {
        this.statusService = statusService;
        this.configRepository = configRepository;
    }

    public void performTrackingStep() {
        final PoolTracking tracking = configRepository.get();

        boolean isTrackingEnabled = tracking.isEnabled();
        if (!isTrackingEnabled) {
            //this should never happen
            return;
        }

        TimeOfDay time = TimeOfDay.from(new Date());
        boolean isTimeToTrack = tracking.currentTimeRange().isTimeInRange(time);

        if (!isTimeToTrack) {
            return;
        }

        statusService.getStatusAsynchronously(new AsyncCallback<PoolStatus>() {
            @Override
            public void process(PoolStatus poolStatus) {
                finishTracking(poolStatus, tracking);
            }

            @Override
            public void processFail(Exception exc) {
                //asi nic no
            }
        });
    }

    private void finishTracking(PoolStatus poolStatus, PoolTracking configuration) {
        if (!poolStatus.isOpen()) {
            //this should never happen
        }

        int attendanceBoundary = configuration.currentAttendanceBoundary();
        int currentAttendance = poolStatus.attendanceTotal();

    }
}
