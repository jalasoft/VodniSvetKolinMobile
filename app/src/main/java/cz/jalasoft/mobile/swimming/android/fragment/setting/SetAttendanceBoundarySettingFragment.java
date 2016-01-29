package cz.jalasoft.mobile.swimming.android.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.widget.RangeSeekBar;

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

        int currentExpectation = applicationService().swimmingPoolAttendanceExpectation();
        displayNumber(currentExpectation);
        layout(view).addView(seekBar(currentExpectation));

        return view;
    }

    private TextView numberView(View view) {
        return (TextView) view.findViewById(R.id.setting_set_attendance_boundary_number);
    }

    private void displayNumber(int number) {
        numberView.setText("" + number);
    }

    private LinearLayout layout(View view) {
        return (LinearLayout) view.findViewById(R.id.setting_set_attendance_boundary_layout);
    }

    private RangeSeekBar<?> seekBar(int initialValue) {
        final int maxAttendance = applicationService().maxSwimmingPoolAttendanceExpectation();
        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.maxOnly(0, maxAttendance, getContext());
        timeSeekBar.setSelectedMaxValue(initialValue);

        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                displayNumber(maxValue);
                applicationService().changeSwimmingPoolAttendanceExpectation(maxValue);
            }
        });

        return timeSeekBar;
    }
}
