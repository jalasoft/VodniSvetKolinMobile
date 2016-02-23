package cz.jalasoft.mobile.swimming.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static cz.jalasoft.mobile.swimming.android.Application.*;

/**
 * A receiver of reboot broad cast that schedules tracking in case it is enabled.
 *
 * Created by Honza "Honzales" Lastovicka on 2/23/16.
 */
public final class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            return;
        }

        boolean enabled = applicationService().trackingConfiguration().isEnabled();
        if (!enabled) {
            return;
        }

        applicationService().scheduleTracking();
    }
}
