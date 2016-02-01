package cz.jalasoft.mobile.swimming.android.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cz.jalasoft.mobile.swimming.android.activity.fragment.SettingsFragment;
import cz.jalasoft.mobile.swimming.android.activity.fragment.AttendanceDisplayFragment;

/**
 * Created by Honza "Honzales" Lastovicka on 1/23/16.
 */
public final class AttendancePagerAdapter extends FragmentPagerAdapter {

    private final AttendanceDisplayFragment attendanceFragment;
    private final SettingsFragment anotherSettings;

    public AttendancePagerAdapter(FragmentManager fm) {
        super(fm);

        attendanceFragment = AttendanceDisplayFragment.newInstance();
        anotherSettings = new SettingsFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return attendanceFragment;
            case 1:
                return anotherSettings;

            default:
                throw new IllegalArgumentException("Unexpected position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
