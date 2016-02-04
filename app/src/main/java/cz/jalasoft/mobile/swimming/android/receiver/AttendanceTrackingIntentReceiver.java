package cz.jalasoft.mobile.swimming.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static cz.jalasoft.mobile.swimming.android.Application.applicationService;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public final class AttendanceTrackingIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        applicationService().performTrackingStep();
    }
}
