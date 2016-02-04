package cz.jalasoft.mobile.swimming.android.activity.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.activity.ApplicationFlow;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static cz.jalasoft.mobile.swimming.android.Application.*;

public final class AttendanceDisplayFragment extends Fragment {

    public static AttendanceDisplayFragment newInstance() {
        AttendanceDisplayFragment fragment = new AttendanceDisplayFragment();
        return fragment;
    }

    private TextView attendanceTotalText;
    private TextView attendancePercentage;
    private ImageView openClosedImage;

    private ProgressBar progressBar;

    private ApplicationFlow flow;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof ApplicationFlow)) {
            throw new IllegalStateException("Parent activity is supposed to be an application flow.");
        }

        this.flow = (ApplicationFlow) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViews();
    }

    private void initViews() {
        attendanceTotalText = (TextView) getView().findViewById(R.id.attendance_total);
        attendanceTotalText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), R.string.attendance_total, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        attendancePercentage = (TextView) getView().findViewById(R.id.attendance_percentage);
        attendancePercentage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), R.string.attendance_percentage, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        openClosedImage = (ImageView) getView().findViewById(R.id.open_closed_image);

        progressBar = (ProgressBar) getView().findViewById(R.id.attendance_progress);
    }

    @Override
    public void onResume() {
        super.onResume();

        refresh();
    }

    private void refresh() {
        showProgress();

        applicationService().poolStatus(new AsyncCallback<PoolStatus>() {

            @Override
            public void process(PoolStatus pool) {
                hideProgress();

                displayAttendanceTotal(pool);
                displayAttendancePercentage(pool);
                displayOpenClosed(pool);
            }

            @Override
            public void processFail(Exception exc) {
                hideProgress();

                Toast.makeText(getContext(), exc.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(INVISIBLE);
    }

    private void displayAttendanceTotal(PoolStatus pool) {
        attendanceTotalText.setText(formatAttendanceString(pool));
    }

    private String formatAttendanceString(PoolStatus pool) {
        String attendanceString = String.valueOf(pool.attendanceTotal());

        switch (attendanceString.length()) {
            case 1:
                return "  " + attendanceString;
            case 2:
                return "  " + attendanceString;
            default:
                return "  " + attendanceString;
        }
    }

    private void displayAttendancePercentage(PoolStatus pool) {
        attendancePercentage.setText(pool.attendancePercentage() + "%");
    }

    private void displayOpenClosed(PoolStatus pool) {
        if (pool.isOpen()) {
            updateWithOpen();
        } else {
            updateWithClosed();
        }
    }

    private void updateWithOpen() {
        openClosedImage.setImageResource(R.drawable.ic_semaphore_green);
        openClosedImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), getString(R.string.pool_open), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void updateWithClosed() {
        openClosedImage.setImageResource(R.drawable.ic_semaphore_red);
        openClosedImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getContext(), getString(R.string.pool_closed), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance_display, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.attendance_display_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectedItem = item.getItemId();

        if (selectedItem == R.id.action_refresh) {
            refresh();
            return true;
        }

        if (selectedItem == R.id.action_track) {
            flow.moveToAttendanceTracking();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
