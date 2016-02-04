package cz.jalasoft.mobile.swimming.domain.model.track;

import java.util.Date;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class PoolTrackingService {

    private final PoolStatusService statusService;

    public PoolTrackingService(PoolStatusService statusService) {
        this.statusService = statusService;
    }

    public void performTrackingStep(final PoolTrackingConfiguration configuration) {
        TimeOfDay time = TimeOfDay.from(new Date());
        boolean isTimeToTrack = configuration.currentTimeRange().isTimeInRange(time);

        if (!isTimeToTrack) {
            return;
        }

        statusService.getStatusAsynchronously(new AsyncCallback<PoolStatus>() {
            @Override
            public void process(PoolStatus poolStatus) {
                finishTracking(poolStatus, configuration);
            }

            @Override
            public void processFail(Exception exc) {
                //asi nic no
            }
        });
    }

    private void finishTracking(PoolStatus poolStatus, PoolTrackingConfiguration configuration) {
        if (!poolStatus.isOpen()) {
            //this should never happen
        }

        int attendanceBoundary = configuration.currentAttendanceBoundary();
        int currentAttendance = poolStatus.attendanceTotal();

        //TODO
    }
}
