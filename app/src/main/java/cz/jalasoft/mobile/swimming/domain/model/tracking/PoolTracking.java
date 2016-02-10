package cz.jalasoft.mobile.swimming.domain.model.tracking;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public final class PoolTracking {

    private final int currentAttendance;
    private final PoolTrackingDescriptor descriptor;

    public PoolTracking(int currentAttendance, PoolTrackingDescriptor descriptor) {
        if (currentAttendance < 0) {
            throw new IllegalArgumentException("Current attendance must not be negative number.");
        }
        if (descriptor == null) {
            throw new IllegalArgumentException("Pool tracking descriptor must not be null.");
        }

        this.currentAttendance = currentAttendance;
        this.descriptor = descriptor;
    }

    public int currentAttendance() {
        return currentAttendance;
    }

    public int attendanceBoundary() {
        return descriptor.currentAttendanceBoundary();
    }

}
