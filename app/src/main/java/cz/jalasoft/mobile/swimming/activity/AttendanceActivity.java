package cz.jalasoft.mobile.swimming.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 *
 */
public final class AttendanceActivity extends AppCompatActivity implements SwimmingPoolDisplay {


    private TextView attendanceTotalText;
    private TextView attendancePercentage;
    private ImageView openClosedImage;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_attendance);
        setSupportActionBar(initToolbar());
    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.attendance);
        toolbar.setTitleTextColor(Color.WHITE);

        return toolbar;
    }

    @Override
    protected void onStart() {
        super.onStart();

        initViews();
    }

    private void initViews() {
        attendanceTotalText = (TextView) findViewById(R.id.attendance_total);
        attendanceTotalText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), R.string.attendance_total, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        attendancePercentage = (TextView) findViewById(R.id.attendance_percentage);
        attendancePercentage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), R.string.attendance_percentage, Toast.LENGTH_LONG).show();
                return true;
            }
        });

        openClosedImage = (ImageView) findViewById(R.id.open_closed_image);

        progressBar = (ProgressBar) findViewById(R.id.attendance_progress);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                refresh();
                return true;

            case R.id.action_track:
                startTrackingActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refresh() {
        showProgress();

        try {
            new SwimmingPoolTask(this).execute();
        } catch (Exception exc) {
            handleFail(exc);
        }
    }

    private void startTrackingActivity() {
        Intent intent = new Intent(getBaseContext(), AttendanceTrackingActivity.class);
        startActivity(intent);
    }

    private void showProgress() {
        progressBar.setVisibility(VISIBLE);
    }

    private void hideProgress() {
        progressBar.setVisibility(INVISIBLE);
    }

    //--------------------------------------------------------------
    //SWIMMING POOL DISPLAY
    //--------------------------------------------------------------

    @Override
    public void update(SwimmingPool pool) {
        hideProgress();

        displayAttendanceTotal(pool);
        displayAttendancePercentage(pool);
        displayOpenClosed(pool);
    }

    private void displayAttendanceTotal(SwimmingPool pool) {
        attendanceTotalText.setText(formatAttendanceString(pool));
    }

    private String formatAttendanceString(SwimmingPool pool) {
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

    private void displayAttendancePercentage(SwimmingPool pool) {
        attendancePercentage.setText(pool.attendancePercentage() + "%");
    }

    private void displayOpenClosed(SwimmingPool pool) {
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
                Toast.makeText(getApplicationContext(), getString(R.string.pool_open), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void updateWithClosed() {
        openClosedImage.setImageResource(R.drawable.ic_semaphore_red);
        openClosedImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(getApplicationContext(), getString(R.string.pool_closed), Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    @Override
    public void handleFail(Exception exc) {
        hideProgress();

        Toast.makeText(getApplicationContext(), exc.getMessage(), Toast.LENGTH_LONG).show();
    }
}