package cz.jalasoft.mobile.swimming.infrastructure;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import cz.jalasoft.mobile.swimming.android.receiver.AlarmTrackingIntentReceiver;
import cz.jalasoft.mobile.swimming.android.receiver.BootReceiver;

import static android.app.AlarmManager.ELAPSED_REALTIME_WAKEUP;

/**
 * Created by Honza "Honzales" Lastovicka on 2/3/16.
 */
public final class AlarmManagerScheduler {

    private final Context context;
    private final AlarmManager alarmManager;
    private final PackageManager packageManager;

    private final PendingIntent schedulingIntent;

    public AlarmManagerScheduler(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        this.packageManager = context.getPackageManager();

        this.schedulingIntent = schedulingIntent(context);
    }

    private PendingIntent schedulingIntent(Context context) {
        Intent trackIntent = new Intent(context, AlarmTrackingIntentReceiver.class);
        return PendingIntent.getBroadcast(context, 1, trackIntent, 0);
    }

    public void scheduleRepeating(long intervalMillis) {
        alarmManager.setInexactRepeating(ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), intervalMillis, schedulingIntent);

        enableRebootBroadcastReceiver();
    }

    private void enableRebootBroadcastReceiver() {
        ComponentName receiver = new ComponentName(context, BootReceiver.class);

        packageManager.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    public void unschedule() {
        alarmManager.cancel(schedulingIntent);

        disableRebootBroadcastReceiver();
    }

    private void disableRebootBroadcastReceiver() {
        ComponentName receiver = new ComponentName(context, BootReceiver.class);

        packageManager.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }
}
