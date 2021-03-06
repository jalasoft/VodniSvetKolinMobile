package cz.jalasoft.mobile.swimming.domain.model.tracking;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public interface PoolTrackingDescriptorRepository {

    PoolTrackingDescriptor get();

    void enableTracking(boolean enabled);

    void saveTimeRange(TimeRange timeRange);

    void saveAttendance(int attendance);
}
