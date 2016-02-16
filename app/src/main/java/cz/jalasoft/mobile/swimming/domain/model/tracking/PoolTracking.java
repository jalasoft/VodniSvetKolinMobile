package cz.jalasoft.mobile.swimming.domain.model.tracking;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public final class PoolTracking {

    private final PoolStatus status;
    private final PoolTrackingDescriptor descriptor;

    public PoolTracking(PoolStatus status, PoolTrackingDescriptor descriptor) {
        if (status == null) {
            throw new IllegalArgumentException("Pool status must not be null.");
        }
        if (descriptor == null) {
            throw new IllegalArgumentException("Pool tracking descriptor must not be null.");
        }

        this.status = status;
        this.descriptor = descriptor;
    }

    public int currentAttendance() {
        return status.attendanceTotal();
    }

    public int attendanceBoundary() {
        return descriptor.currentAttendanceBoundary();
    }

    public boolean isAttendanceAboveBoundary() {
        return status.attendanceTotal() > descriptor.currentAttendanceBoundary();
    }

    public boolean isAttendanceBelowOrEqualToBoundary() {
        return status.attendanceTotal() < descriptor.currentAttendanceBoundary();
    }
}
