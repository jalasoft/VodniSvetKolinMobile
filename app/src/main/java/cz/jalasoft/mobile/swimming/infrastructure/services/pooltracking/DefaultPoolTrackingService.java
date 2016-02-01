package cz.jalasoft.mobile.swimming.infrastructure.services.pooltracking;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;

import java.util.Date;

import cz.jalasoft.mobile.swimming.android.receiver.AttendanceTrackingIntentReceiver;
import cz.jalasoft.mobile.swimming.domain.model.track.PoolTrackingService;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class DefaultPoolTrackingService implements PoolTrackingService {

    private final Context context;
    private final AlarmManager alarmManager;

    public DefaultPoolTrackingService(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public void startTracking() {
        Intent trackIntent = new Intent(context, AttendanceTrackingIntentReceiver.class);
        PendingIntent p = PendingIntent.getBroadcast(context, 1, trackIntent, 0);

        alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, new Date().getTime(), 5000, p);
    }

    @Override
    public void stopTracking() {
        Intent trackIntent = new Intent(context, AttendanceTrackingIntentReceiver.class);
        PendingIntent p = PendingIntent.getBroadcast(context, 1, trackIntent, 0);

        alarmManager.cancel(p);
    }

    @Override
    public void trackNow() {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }
}
