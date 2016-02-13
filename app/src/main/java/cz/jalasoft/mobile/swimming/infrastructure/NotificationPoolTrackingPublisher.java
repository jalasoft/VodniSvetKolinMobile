package cz.jalasoft.mobile.swimming.infrastructure;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;
import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTrackingPublisher;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public final class NotificationPoolTrackingPublisher implements PoolTrackingPublisher {

    private final Context context;

    public NotificationPoolTrackingPublisher(Context context) {
        this.context = context;
    }

    @Override
    public void publishPoolTracking(PoolTracking tracking) {
        //Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //vibrator.vibrate(500);

        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(context, notification);
        r.play();
    }

    @Override
    public void publishPoolTrackingError(Exception exc) {

        //do nothing
    }
}
