package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptor;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingPolicy;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeOfDay;

/**
 * Created by Honza "Honzales" Lastovicka on 2/13/16.
 */
public final class SharedPreferencesPoolTrackingPolicy implements PoolTrackingPolicy {

    private static final String PREFERENCES_NAME = "trackingPolicy";

    private static final String LATEST_ATTENDANCE_KEY = "latestAttendance";
    private static final int INITIAL_ATTENDANCE_VALUE = -1;

    private final SharedPreferences preferences;

    public SharedPreferencesPoolTrackingPolicy(Context context) {
        this.preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean canPoolBeTracked(PoolTrackingDescriptor descriptor) {
        boolean isTrackingEnabled = descriptor.isEnabled();
        if (!isTrackingEnabled) {
            //this should never happen
            return false;
        }

        return isTimeRangeForTracking(descriptor);
    }

    private boolean isTimeRangeForTracking(PoolTrackingDescriptor descriptor) {
        TimeOfDay time = TimeOfDay.from(new Date());
        boolean isTimeToTrack = descriptor.currentTimeRange().isTimeInRange(time);

        return isTimeToTrack;
    }

    public boolean canPoolTrackingBePublished(PoolTracking poolTracking) {
        int latestAttendance = latestAttendance();
        int attendanceBoundary = poolTracking.attendanceBoundary();
        int currentAttendance = poolTracking.currentAttendance();

        boolean latestIsBelow = latestAttendance == INITIAL_ATTENDANCE_VALUE ? false : latestAttendance <= attendanceBoundary;
        boolean isBelow = currentAttendance <= attendanceBoundary;

        boolean result;

        if (isBelow) {
            result = !latestIsBelow;
        } else {
            result = latestIsBelow;
        }

        updateLatestAttendance(currentAttendance);

        return  result;
    }

    private int latestAttendance() {
        int result = preferences.getInt(LATEST_ATTENDANCE_KEY, INITIAL_ATTENDANCE_VALUE);
        return result;
    }

    private void updateLatestAttendance(int attendance) {
        preferences
                .edit()
                .putInt(LATEST_ATTENDANCE_KEY, attendance)
                .commit();
    }

    public void trackingEnabled() {
        preferences
                .edit()
                .remove(LATEST_ATTENDANCE_KEY)
                .commit();
    }
}
