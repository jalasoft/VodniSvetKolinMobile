package cz.jalasoft.mobile.swimming.domain.model.tracking;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolTracking {

    private final boolean isEnabled;
    private final int totalAttendanceBoundary;
    private final int currentAttendanceBoundary;
    private final TimeRange totalTimeRange;
    private final TimeRange currentTimeRange;

    public PoolTracking(boolean isEnabled, int totalAttendanceBoundary, int currentAttendanceBoundary, TimeRange totalTimeRange, TimeRange currentTimeRange) {
        this.isEnabled = isEnabled;
        this.totalAttendanceBoundary = totalAttendanceBoundary;
        this.currentAttendanceBoundary = currentAttendanceBoundary;
        this.totalTimeRange = totalTimeRange;
        this.currentTimeRange = currentTimeRange;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public PoolTracking enable(boolean isEnabled) {
        return new PoolTracking(isEnabled, totalAttendanceBoundary, currentAttendanceBoundary, totalTimeRange, currentTimeRange);
    }

    public int totalAttendanceBoundary() {
        return totalAttendanceBoundary;
    }

    public int currentAttendanceBoundary() {
        return currentAttendanceBoundary;
    }

    public PoolTracking withCurrentAttendanceBoundary(int boundary) {
        return new PoolTracking(isEnabled, totalAttendanceBoundary, boundary, totalTimeRange, currentTimeRange);
    }

    public TimeRange totalTimeRange() {
        return totalTimeRange;
    }

    public TimeRange currentTimeRange() {
        return currentTimeRange;
    }

    public PoolTracking withCurrentTimeRange(TimeRange timeRange) {
        return new PoolTracking(isEnabled, totalAttendanceBoundary, currentAttendanceBoundary, totalTimeRange, timeRange);
    }
}
