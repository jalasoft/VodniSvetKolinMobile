package cz.jalasoft.mobile.swimming.domain.model.tracking;

import cz.jalasoft.mobile.swimming.domain.model.tracking.PoolTracking;

/**
 * Created by Honza "Honzales" Lastovicka on 2/10/16.
 */
public interface PoolTrackingPublisher {

    void publishPoolTracking(PoolTracking tracking);

    void publishPoolTrackingError(Exception exc);
}
