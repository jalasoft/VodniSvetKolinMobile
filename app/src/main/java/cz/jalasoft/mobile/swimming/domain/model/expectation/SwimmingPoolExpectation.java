package cz.jalasoft.mobile.swimming.domain.model.expectation;

/**
 * Created by Honza "Honzales" Lastovicka on 1/29/16.
 */
public interface SwimmingPoolExpectation {

    int maxAttendanceBoundary();

    int attendanceBoundary();

    void changeAttendanceBoundary(int boundary);

    TimeRange timeRange();

    void changeTimeRange(TimeRange range);
}
