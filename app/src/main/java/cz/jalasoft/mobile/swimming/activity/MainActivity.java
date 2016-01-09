package cz.jalasoft.mobile.swimming.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public final class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView attendanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = initToolbar();
        setSupportActionBar(toolbar);
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
        progressBar = (ProgressBar) findViewById(R.id.attendance_progress);
        attendanceText = (TextView) findViewById(R.id.attendance_count);
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

            default:
                return super.onOptionsItemSelected(item);
        }
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
        progressBar.setVisibility(VISIBLE);
    }

    void hideProgress() {
        progressBar.setVisibility(INVISIBLE);
    }
}
