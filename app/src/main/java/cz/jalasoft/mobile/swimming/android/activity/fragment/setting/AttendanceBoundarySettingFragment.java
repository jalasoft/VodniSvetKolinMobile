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
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingDescriptor;

import static cz.jalasoft.mobile.swimming.android.Application.applicationService;

/**
 * Created by Honza "Honzales" Lastovicka on 1/28/16.
 */
public final class AttendanceBoundarySettingFragment extends Fragment {

    private TextView numberView;
    private RangeSeekBar<?> boundaryBar;

    private Listener listener = Listener.DUMMY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_attendance_boundary, container);

        numberView = numberView(view);

        PoolTrackingDescriptor configuration = applicationService().trackingConfiguration();
        int currentAttendanceBoundary = configuration.currentAttendanceBoundary();
        int totalAttendanceBoundary = configuration.totalAttendanceBoundary();
        boolean enabled = configuration.isEnabled();

        displayAttendance(currentAttendanceBoundary);

        boundaryBar = seekBar(currentAttendanceBoundary, totalAttendanceBoundary);
        layout(view).addView(boundaryBar);

        if (!enabled) {
            disable();
        } else {
            enable();
        }

        return view;
    }

    private TextView numberView(View view) {
        return (TextView) view.findViewById(R.id.setting_set_attendance_boundary_number);
    }

    private void displayAttendance(int attendance) {
        numberView.setText("" + attendance);
    }

    private void notifyListener(int attendance) {
        listener.onAttendanceBoundarySelected(attendance);
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
                notifyListener(maxValue);
            }
        });

        return timeSeekBar;
    }

    //---------------------------------------------------------------------------------
    //PUBLIC INTERFACE
    //---------------------------------------------------------------------------------

    public void setListener(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener must not be null.");
        }
        this.listener = listener;
    }

    public void unsetListener() {
        this.listener = Listener.DUMMY;
    }

    public void enable() {
        boundaryBar.setVisibility(View.INVISIBLE);
    }

    public void disable() {
        boundaryBar.setVisibility(View.VISIBLE);
    }

    //---------------------------------------------------------------------------------------------
    //LISTENER
    //---------------------------------------------------------------------------------------------

    public interface Listener {

        Listener DUMMY = new Listener() {
            @Override
            public void onAttendanceBoundarySelected(int boundary) {

            }
        };

        void onAttendanceBoundarySelected(int boundary);
    }
}
