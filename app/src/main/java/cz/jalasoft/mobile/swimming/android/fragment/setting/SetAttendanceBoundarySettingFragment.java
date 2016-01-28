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

/**
 * Created by Honza "Honzales" Lastovicka on 1/28/16.
 */
public final class SetAttendanceBoundarySettingFragment extends Fragment {

    private static final int MAX_ATTENDACE = 230;

    private String titlePattern;
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_set_attendance_boundary, container);

        title = title(view);
        titlePattern = titlePattern();

        setTitle(MAX_ATTENDACE);
        layout(view).addView(seekBar());

        return view;
    }

    private TextView title(View view) {
        return (TextView) view.findViewById(R.id.setting_set_attendance_boundary_title);
    }

    private String titlePattern() {
        return getResources().getString(R.string.setting_set_attendance_boundary_title_pattern);
    }

    private void setTitle(int number) {
        String newTitle = String.format(titlePattern, number);
        title.setText(newTitle);
    }

    private LinearLayout layout(View view) {
        return (LinearLayout) view.findViewById(R.id.setting_set_attendance_boundary_layout);
    }

    private RangeSeekBar<?> seekBar() {
        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.maxOnly(0, MAX_ATTENDACE, getContext());

        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                setTitle(maxValue);
            }
        });

        return timeSeekBar;
    }
}
