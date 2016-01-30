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
import cz.jalasoft.mobile.swimming.domain.model.pool.track.PoolTrackingConfiguration;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.TimeOfDay;
import cz.jalasoft.mobile.swimming.domain.model.pool.track.TimeRange;

import static cz.jalasoft.mobile.swimming.android.Application.serviceRegistry;

/**
 * Created by Honza "Honzales" Lastovicka on 1/28/16.
 */
public final class SetTrackingTimeSettingFragment extends Fragment {

    private TextView timeRangeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_set_tracking_time, container);

        timeRangeView = timeRangeView(view);

        PoolTrackingConfiguration configuration = serviceRegistry().poolTrackingService().configuration();

        TimeRange currentTimeRange = configuration.currentTimeRange();
        TimeRange totalTimeRange = configuration.totalTimeRange();

        RangeSeekBar<?> timeRangeBar = seekBar(currentTimeRange, totalTimeRange);
        layout(view).addView(timeRangeBar);

        displayTimeRange(currentTimeRange);

        return view;
    }

    private TextView timeRangeView(View view) {
        return (TextView) view.findViewById(R.id.setting_set_tracking_time_time);
    }

    private void displayTimeRange(TimeRange timeRange) {
        timeRangeView.setText(timeRange.toString());
    }

    private void updateCurrentTimeRange(TimeRange newTimeRange) {
        serviceRegistry().poolTrackingService().saveTimeRange(newTimeRange);
    }

    private LinearLayout layout(View view) {
        return (LinearLayout) view.findViewById(R.id.setting_set_tracking_time_layout);
    }

    private RangeSeekBar<?> seekBar(TimeRange currentRange, TimeRange totalRange) {

        final TimeRangeSeekBarConverter converter = new TimeRangeSeekBarConverter(currentRange, totalRange);

        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.classic(converter.totalMinValue(), converter.totalMaxValue(), getContext());


        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                converter.updateCurrentMinValue(minValue);
                converter.updateCurrentMaxValue(maxValue);

                TimeRange newTimeRange = converter.currentTimeRange();

                displayTimeRange(newTimeRange);
                updateCurrentTimeRange(newTimeRange);
            }
        });

        timeSeekBar.setSelectedMinValue(converter.currentMinValue());
        timeSeekBar.setSelectedMaxValue(converter.currentMaxValue());

        return timeSeekBar;
    }

    //---------------------------------------------------------------------------------
    //TIME RANGE SEEK BAR DRIVER
    //---------------------------------------------------------------------------------

    private static final class TimeRangeSeekBarConverter {

        private static final int MINIMAL_TIME_UNIT_MINUTES = 5;

        private final int totalMin;
        private final int totalMax;
        private int currentMin;
        private int currentMax;

        private final TimeRange totalTimeRange;

        TimeRangeSeekBarConverter(TimeRange currentRange, TimeRange totalRange) {
            this.totalMin = 0;
            this.totalMax = totalRange.asMinutes() / MINIMAL_TIME_UNIT_MINUTES;
            this.currentMin = initializeCurrentValue(totalRange.startTime(), currentRange.startTime());
            this.currentMax = initializeCurrentValue(totalRange.startTime(), currentRange.endTime());

            this.totalTimeRange = totalRange;
        }

        private int initializeCurrentValue(TimeOfDay totalStartTime, TimeOfDay endTime) {
            int minutes = TimeRange.from(totalStartTime, endTime).asMinutes();
            int result = minutes / MINIMAL_TIME_UNIT_MINUTES;

            return result;
        }

        int currentMinValue() {
            return currentMin;
        }

        int currentMaxValue() {
            return currentMax;
        }

        int totalMinValue() {
            return totalMin;
        }

        int totalMaxValue() {
            return totalMax;
        }

        void updateCurrentMinValue(int value) {
            this.currentMin = value;
        }

        void updateCurrentMaxValue(int value) {
            this.currentMax = value;
        }

        private TimeOfDay startTime() {
            int minutes = currentMin * MINIMAL_TIME_UNIT_MINUTES;
            TimeOfDay result = totalTimeRange.startTime().plusMinute(minutes);
            return result;
        }

        private TimeOfDay endTime() {
            int minutes = currentMax * MINIMAL_TIME_UNIT_MINUTES;
            TimeOfDay result = totalTimeRange.startTime().plusMinute(minutes);
            return result;
        }

        TimeRange currentTimeRange() {
            return TimeRange.from(startTime(), endTime());
        }
    }
}


