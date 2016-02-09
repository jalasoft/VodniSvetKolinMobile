package cz.jalasoft.mobile.swimming.android.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.activity.fragment.setting.EnableTrackingSettingFragment;
import cz.jalasoft.mobile.swimming.android.activity.fragment.setting.AttendanceBoundarySettingFragment;
import cz.jalasoft.mobile.swimming.android.activity.fragment.setting.TrackingTimeSettingFragment;
import cz.jalasoft.mobile.swimming.domain.model.tracking.TimeRange;

import static cz.jalasoft.mobile.swimming.android.Application.*;

/**
 * Created by Honza "Honzales" Lastovicka on 1/26/16.
 */
public final class SettingsFragment extends Fragment implements
        EnableTrackingSettingFragment.Listener,
        AttendanceBoundarySettingFragment.Listener,
        TrackingTimeSettingFragment.Listener {

    private EnableTrackingSettingFragment enableTrackingFragment;
    private AttendanceBoundarySettingFragment attendanceBoundaryFragment;
    private TrackingTimeSettingFragment trackingTimeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        enableTrackingFragment = enableTrackingFragment();
        enableTrackingFragment.setListener(this);

        attendanceBoundaryFragment = attendanceBoundaryFragment();
        attendanceBoundaryFragment.setListener(this);

        trackingTimeFragment = trackingTimeFragment();
        trackingTimeFragment.setListener(this);
    }

    private EnableTrackingSettingFragment enableTrackingFragment() {
        return (EnableTrackingSettingFragment) getChildFragmentManager().findFragmentById(R.id.setting_enable_tracking_fragment);
    }

    private AttendanceBoundarySettingFragment attendanceBoundaryFragment() {
        return (AttendanceBoundarySettingFragment) getChildFragmentManager().findFragmentById(R.id.setting_attendance_boundary_fragment);
    }

    private TrackingTimeSettingFragment trackingTimeFragment() {
        return (TrackingTimeSettingFragment) getChildFragmentManager().findFragmentById(R.id.setting_tracking_time_fragment);
    }

    @Override
    public void onPause() {
        enableTrackingFragment().unsetListener();
        attendanceBoundaryFragment().unsetListener();
        trackingTimeFragment().unsetListener();

        super.onPause();
    }

    //-----------------------------------------------------------------
    //SETTING CALLBACKS
    //-----------------------------------------------------------------

    @Override
    public void onTrackingEnabled() {
        applicationService().enableTracking(true);
        attendanceBoundaryFragment.enable();
        trackingTimeFragment.enable();
    }

    @Override
    public void onTrackingDisabled() {
        applicationService().enableTracking(false);
        attendanceBoundaryFragment.disable();
        trackingTimeFragment.disable();
    }

    @Override
    public void onTrackingTimeSelected(TimeRange timeRange) {
        applicationService().saveTrackingTime(timeRange);
    }

    @Override
    public void onAttendanceBoundarySelected(int boundary) {
        applicationService().saveAttendanceBoundary(boundary);
    }
}
