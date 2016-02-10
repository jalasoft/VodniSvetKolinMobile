package cz.jalasoft.mobile.swimming.domain.model.tracking;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolTrackingDescriptor {

    private final boolean isEnabled;
    private final int totalAttendanceBoundary;
    private final int currentAttendanceBoundary;
    private final TimeRange totalTimeRange;
    private final TimeRange currentTimeRange;
    private final long trackingIntervalMillis;

    public PoolTrackingDescriptor(boolean isEnabled, int totalAttendanceBoundary, int currentAttendanceBoundary, TimeRange totalTimeRange, TimeRange currentTimeRange, long trackingIntervalMillis) {
        this.isEnabled = isEnabled;
        this.totalAttendanceBoundary = totalAttendanceBoundary;
        this.currentAttendanceBoundary = currentAttendanceBoundary;
        this.totalTimeRange = totalTimeRange;
        this.currentTimeRange = currentTimeRange;
        this.trackingIntervalMillis = trackingIntervalMillis;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public PoolTrackingDescriptor enable(boolean isEnabled) {
        return new PoolTrackingDescriptor(isEnabled, totalAttendanceBoundary, currentAttendanceBoundary, totalTimeRange, currentTimeRange, trackingIntervalMillis);
    }

    public int totalAttendanceBoundary() {
        return totalAttendanceBoundary;
    }

    public int currentAttendanceBoundary() {
        return currentAttendanceBoundary;
    }

    public PoolTrackingDescriptor withCurrentAttendanceBoundary(int boundary) {
        return new PoolTrackingDescriptor(isEnabled, totalAttendanceBoundary, boundary, totalTimeRange, currentTimeRange, trackingIntervalMillis);
    }

    public TimeRange totalTimeRange() {
        return totalTimeRange;
    }

    public TimeRange currentTimeRange() {
        return currentTimeRange;
    }

    public PoolTrackingDescriptor withCurrentTimeRange(TimeRange timeRange) {
        return new PoolTrackingDescriptor(isEnabled, totalAttendanceBoundary, currentAttendanceBoundary, totalTimeRange, timeRange, trackingIntervalMillis);
    }

    public long trackingIntervalMillis() {
        return trackingIntervalMillis;
    }

    //TODO Eq
}
