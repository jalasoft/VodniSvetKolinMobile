package cz.jalasoft.mobile.swimming.android.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;

import cz.jalasoft.mobile.swimming.R;

public final class AttendanceTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_tracking);

        Toolbar toolbar = initToolbar();
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Switch trackingSwitch = (Switch) findViewById(R.id.turnTrackingOnOff);

    }

    private Toolbar initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.tracking);
        toolbar.setTitleTextColor(Color.WHITE);

        return toolbar;
    }

}
