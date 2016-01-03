package cz.jalasoft.mobile.swimming.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;

public final class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ImageButton refreshButton;
    private TextView attendanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initViews();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    private void initViews() {
        progressBar = (ProgressBar) findViewById(R.id.attendance_progress);
        refreshButton = (ImageButton) findViewById(R.id.visitor_count_refresh);
        attendanceText = (TextView) findViewById(R.id.attendance_count);
    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    private void refresh() {
        try {
            new SwimmingPoolTask(this).execute();
        } catch (Exception exc) {
            handleException(exc);
        }
    }

    void displaySwimmingPool(SwimmingPool pool) {
        attendanceText.setText(formatAttendanceString(pool));
    }

    private String formatAttendanceString(SwimmingPool pool) {
        String attendanceString = String.valueOf(pool.attendance());

        switch (attendanceString.length()) {
            case 1:
                return "  " + attendanceString;
            case 2:
                return "  " + attendanceString;
            default:
                return "  " + attendanceString;
        }
    }

    void handleException(Exception exc) {
        Toast.makeText(getApplicationContext(), exc.getMessage(), Toast.LENGTH_LONG).show();
    }

    void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
