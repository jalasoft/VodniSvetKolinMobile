package cz.jalasoft.mobile.swimming.android.activity.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.activity.widget.RangeSeekBar;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingConfiguration;

import static cz.jalasoft.mobile.swimming.android.Application.applicationService;

/**
 * Created by Honza "Honzales" Lastovicka on 1/28/16.
 */
public final class SetAttendanceBoundarySettingFragment extends Fragment {

    private TextView numberView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_set_attendance_boundary, container);

        numberView = numberView(view);

        PoolTrackingConfiguration configuration = applicationService().trackingConfiguration();
        int currentAttendanceBoundary = configuration.currentAttendanceBoundary();
        int totalAttendanceBoundary = configuration.totalAttendanceBoundary();

        displayAttendance(currentAttendanceBoundary);

        RangeSeekBar<?> attendanceSeekBar = seekBar(currentAttendanceBoundary, totalAttendanceBoundary);
        layout(view).addView(attendanceSeekBar);

        return view;
    }

    private TextView numberView(View view) {
        return (TextView) view.findViewById(R.id.setting_set_attendance_boundary_number);
    }

    private void displayAttendance(int attendance) {
        numberView.setText("" + attendance);
    }

    private void saveAttendance(int attendance) {
        applicationService().saveTrackingAttendance(attendance);
    }

    private LinearLayout layout(View view) {
        return (LinearLayout) view.findViewById(R.id.setting_set_attendance_boundary_layout);
    }

    private RangeSeekBar<?> seekBar(int currentValue, int totalValue) {
        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.maxOnly(0, totalValue, getContext());
        timeSeekBar.setSelectedMaxValue(currentValue);

        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                displayAttendance(maxValue);
                saveAttendance(maxValue);
            }
        });

        return timeSeekBar;
    }
}
