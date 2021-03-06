package cz.jalasoft.mobile.swimming.infrastructure.persistence;

import android.content.Context;
import android.content.SharedPreferences;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptor;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptorRepository;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeOfDay;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class SharedPreferencesPoolTrackingDescritorRepository implements PoolTrackingDescriptorRepository {

    private static final boolean DEFAULT_TRACKING_ENABLED = false;
    private static final String TRACKING_ENABLED_KEY = "trackingEnabled";

    private static final int TOTAL_ATTENDANCE_BOUNDARY = 230;
    private static final String ATTENDANCE_KEY = "expectedAttendance";

    private static final String START_TRACKING_TIME_KEY = "startTrackingTime";
    private static final String MIN_START_TRACKING_TIME = "06:00";
    private static final TimeOfDay MIN_START_TRACKING_TIME_OF_DAY = TimeOfDay.from(MIN_START_TRACKING_TIME);

    private static final String END_TRACKING_TIME_KEY = "endTrackingTime";
    private static final String MAX_END_TRACKING_TIME = "21:00";
    private static final TimeOfDay MAX_END_TRACKING_TIME_OF_DAY = TimeOfDay.from(MAX_END_TRACKING_TIME);

    private static final TimeRange TOTAL_TIME_RANGE = TimeRange.from(MIN_START_TRACKING_TIME_OF_DAY, MAX_END_TRACKING_TIME_OF_DAY);

    private static final long DEFAULT_TRACKING_INTERVAL_MILLIS = 5000;
    private static final String TRACKING_INTERVAL_MILLIS_KEY = "trackingIntervalMillis";

    private final SharedPreferences preferences;

    public SharedPreferencesPoolTrackingDescritorRepository(Context context) {
        String preferenceName = context.getString(R.string.setting_preference_name);
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    @Override
    public PoolTrackingDescriptor get() {
        boolean isTrackingEnabled = readTrackingEnabled();
        int currentAttendance = readAttendanceBoundary();
        TimeRange currentTimeRange = readTimeRange();
        long trackingIntervalMillis = readTrackingIntervalMillis();

        return new PoolTrackingDescriptor(isTrackingEnabled, TOTAL_ATTENDANCE_BOUNDARY, currentAttendance, TOTAL_TIME_RANGE, currentTimeRange, trackingIntervalMillis);
    }

    private int readAttendanceBoundary() {
        int result = preferences.getInt(ATTENDANCE_KEY, TOTAL_ATTENDANCE_BOUNDARY);
        return result;
    }

    private TimeRange readTimeRange() {
        String startTimeIso = preferences.getString(START_TRACKING_TIME_KEY, MIN_START_TRACKING_TIME);
        TimeOfDay startTime = TimeOfDay.from(startTimeIso);

        String endTimeIso = preferences.getString(END_TRACKING_TIME_KEY, MAX_END_TRACKING_TIME);
        TimeOfDay endTime = TimeOfDay.from(endTimeIso);

        TimeRange range = TimeRange.from(startTime, endTime);
        return range;
    }

    private boolean readTrackingEnabled() {
        return preferences.getBoolean(TRACKING_ENABLED_KEY, DEFAULT_TRACKING_ENABLED);
    }

    private long readTrackingIntervalMillis() {
        return preferences.getLong(TRACKING_INTERVAL_MILLIS_KEY, DEFAULT_TRACKING_INTERVAL_MILLIS);
    }

    @Override
    public void enableTracking(boolean enabled) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(TRACKING_ENABLED_KEY, enabled);
        editor.commit();
    }

    @Override
    public void saveTimeRange(TimeRange timeRange) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(START_TRACKING_TIME_KEY, timeRange.startTime().toString());
        editor.putString(END_TRACKING_TIME_KEY, timeRange.endTime().toString());
        editor.commit();
    }

    @Override
    public void saveAttendance(int attendance) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(ATTENDANCE_KEY, attendance);
        editor.commit();
    }
}
