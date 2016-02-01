package cz.jalasoft.mobile.swimming.domain.model.track;

/**
 * Created by Honza "Honzales" Lastovicka on 2/1/16.
 */
public interface PoolTrackingService {

    void startTracking();

    void stopTracking();

    void trackNow();
}
