package cz.jalasoft.mobile.swimming.infrastructure;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

import cz.jalasoft.mobile.swimming.R;
import cz.jalasoft.mobile.swimming.android.activity.ApplicationActivity;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingPublisher;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public final class NotificationPoolTrackingPublisher implements PoolTrackingPublisher {

    private final Context context;
    private final NotificationManager notificationManager;

    public NotificationPoolTrackingPublisher(Context context) {
        this.context = context;
        this.notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void publishPoolTracking(PoolTracking tracking) {
        String title = title(tracking);
        String text = text(tracking);

        Notification notification = new NotificationCompat.Builder(context)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(intent())
                //.setSmallIcon()
                //.setLargeIcon()
                .build();

        notificationManager.notify(0, notification);

        playSoundOrVibrate();
    }

    private String title(PoolTracking tracking) {
        if (tracking.isAttendanceBelowOrEqualToBoundary()) {
            return context.getString(R.string.notification_title_below);
        } else if (tracking.isAttendanceAboveBoundary()) {
            return context.getString(R.string.notification_title_below);
        }
        return null;
    }

    private String text(PoolTracking tracking) {
        if (tracking.isAttendanceBelowOrEqualToBoundary()) {
            return context.getString(R.string.notification_text_below);
        } else if (tracking.isAttendanceAboveBoundary()) {
            return context.getString(R.string.notification_text_below);
        }
        return null;
    }

    private PendingIntent intent() {
        Intent intent = new Intent(context, ApplicationActivity.class);
        PendingIntent appIntent = PendingIntent.getActivity(context, 0, intent, 0);

        return appIntent;
    }

    private void playSoundOrVibrate() {
        AudioManager audio = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        int mode = audio.getRingerMode();
        if (mode == AudioManager.RINGER_MODE_VIBRATE) {
            vibrate();
        }
        if (mode == AudioManager.RINGER_MODE_NORMAL) {
            playSound();
        }
        if (mode == AudioManager.RINGER_MODE_SILENT) {
            //DO NOTHING
        }
    }

    private void playSound() {
        Uri sound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notification_attendance);
        Ringtone tone = RingtoneManager.getRingtone(context, sound);
        tone.play();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(500);
    }

    @Override
    public void publishPoolTrackingError(Exception exc) {
        //do nothing
    }
}
