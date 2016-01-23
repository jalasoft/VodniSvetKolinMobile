package cz.jalasoft.mobile.swimming.android.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.activity.ApplicationFlow;

public final class AttendanceTrackingFragment extends Fragment {

    public static AttendanceTrackingFragment newInstance() {
        AttendanceTrackingFragment fragment = new AttendanceTrackingFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return fragment;
    }

    //-----------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------

    private ApplicationFlow flow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof ApplicationFlow)) {
            throw new IllegalStateException("An actibity is supposed to be an application flow.");
        }

        this.flow = (ApplicationFlow) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atrendance_tracking, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.attendance_tracking_fragment_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_back_to_display) {
            flow.moveToAttendanceDisplay();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    private TextView trackingTimeTextView;
    private TextView trackingAttendanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_tracking);

        initViews();

        Toolbar toolbar = initToolbar();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Switch trackingSwitch = (Switch) findViewById(R.id.turnTrackingOnOff);

        insertTimeSeekBar();
        insertAttendanceSeekBar();
    }

    private void initViews() {
        this.trackingAttendanceTextView = (TextView) findViewById(R.id.trackingAttendanceView);
        this.trackingTimeTextView = (TextView) findViewById(R.id.trackingAttendanceTimeView);
    }

    private void insertTimeSeekBar() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.trackingTimeLayout);

        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.classic(0, 132, getApplicationContext());
        layout.addView(timeSeekBar);

        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                String startTime = time(minValue);
                String stopTime = time(maxValue);

                updateTrackingTime(startTime + " - " + stopTime);
            }
        });
    }

    private void insertAttendanceSeekBar() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.trackingAttendanceLayout);

        RangeSeekBar<Integer> timeSeekBar = RangeSeekBar.maxOnly(0, 230, getApplicationContext());
        layout.addView(timeSeekBar);

        timeSeekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                updateTrackingAttendance(maxValue);
            }
        });
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tracking);
        toolbar.setTitleTextColor(Color.WHITE);

        return toolbar;
    }

    private String time(int fiveMinutes) {
        int hoursOffset = fiveMinutes / 12;
        int minutesOffset = (fiveMinutes % 12) * 5;

        return "" + (10 + hoursOffset) + ":" + minutesOffset;
    }

    private void updateTrackingTime(String value) {
        trackingTimeTextView.setText(value);
    }

    private void updateTrackingAttendance(int value) {
        trackingAttendanceTextView.setText("" + value);
    }
     */
}
