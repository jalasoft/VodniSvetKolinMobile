package cz.jalasoft.mobile.swimming.domain.model.track;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public interface PoolTrackingConfigurationRepository {

    PoolTrackingConfiguration get();

    void enableTracking(boolean enabled);

    void saveTimeRange(TimeRange timeRange);

    void saveAttendance(int attendance);
}
