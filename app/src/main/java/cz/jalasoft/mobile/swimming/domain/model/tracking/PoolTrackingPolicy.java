package cz.jalasoft.mobile.swimming.domain.model.tracking;

import java.util.Date;

/**
 * Created by Honza "Honzales" Lastovicka on 2/13/16.
 */
public interface PoolTrackingPolicy {

    boolean canPoolBeTracked(PoolTrackingDescriptor descriptor);

    boolean canPoolTrackingBePublished(PoolTracking poolTracking);

    void trackingEnabled();
}
