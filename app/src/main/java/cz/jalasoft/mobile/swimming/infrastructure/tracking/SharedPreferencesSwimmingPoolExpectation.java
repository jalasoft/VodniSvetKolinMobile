package cz.jalasoft.mobile.swimming.infrastructure.tracking;

import android.content.Context;
import android.content.SharedPreferences;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.domain.model.expectation.SwimmingPoolExpectation;
import cz.jalasoft.mobile.swimming.domain.model.expectation.TimeRange;

/**
 * Created by Honza "Honzales" Lastovicka on 1/29/16.
 */
public final class SharedPreferencesSwimmingPoolExpectation implements SwimmingPoolExpectation {

    private static final int MAX_ATTENDANCE = 230;
    private static final String EXPECTED_ATTENDANCE_KEY = "expectedAttendance";


    private final SharedPreferences preferences;

    public SharedPreferencesSwimmingPoolExpectation(Context context) {
        String preferenceName = context.getString(R.string.setting_preference_name);
        preferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }

    @Override
    public int maxAttendanceBoundary() {
        return MAX_ATTENDANCE;
    }

    @Override
    public int attendanceBoundary() {
        int result = preferences.getInt(EXPECTED_ATTENDANCE_KEY, MAX_ATTENDANCE);
        return result;
    }

    @Override
    public void changeAttendanceBoundary(int boundary) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(EXPECTED_ATTENDANCE_KEY, boundary);
        editor.commit();
    }

    @Override
    public TimeRange timeRange() {
        return null;
    }

    @Override
    public void changeTimeRange(TimeRange range) {

    }
}
