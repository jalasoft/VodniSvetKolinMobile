package cz.jalasoft.mobile.swimming.android.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cz.jalasoft.mobile.swimming.R;

public final class ApplicationActivity extends AppCompatActivity implements ApplicationFlow {

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new AttendancePagerAdapter(getSupportFragmentManager()));

    }

    @Override
    public void moveToAttendanceDisplay() {
        pager.setCurrentItem(0);
    }

    @Override
    public void moveToAttendanceTracking() {
        pager.setCurrentItem(1);
    }
}
