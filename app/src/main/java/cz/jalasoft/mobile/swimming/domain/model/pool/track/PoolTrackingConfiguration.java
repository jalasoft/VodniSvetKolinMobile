package cz.jalasoft.mobile.swimming.domain.model.pool.track;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolTrackingConfiguration {

    private final boolean isEnabled;
    private final int totalAttendanceBoundary;
    private final int currentAttendanceBoundary;
    private final TimeRange totalTimeRange;
    private final TimeRange currentTimeRange;

    public PoolTrackingConfiguration(boolean isEnabled, int totalAttendanceBoundary, int currentAttendanceBoundary, TimeRange totalTimeRange, TimeRange currentTimeRange) {
        this.isEnabled = isEnabled;
        this.totalAttendanceBoundary = totalAttendanceBoundary;
        this.currentAttendanceBoundary = currentAttendanceBoundary;
        this.totalTimeRange = totalTimeRange;
        this.currentTimeRange = currentTimeRange;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public int totalAttendanceBoundary() {
        return totalAttendanceBoundary;
    }

    public int currentAttendanceBoundary() {
        return currentAttendanceBoundary;
    }

    public TimeRange totalTimeRange() {
        return totalTimeRange;
    }

    public TimeRange currentTimeRange() {
        return currentTimeRange;
    }
}