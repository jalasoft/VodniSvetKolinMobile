package cz.jalasoft.mobile.swimming.domain.model.pool.track;

/**
 * A value representing a range of time in a day. For example 06:45 - 21:34.
 *
 * Created by Honza "Honzales" Lastovicka on 1/29/16.
 */
public final class TimeRange {


    public static TimeRange from(TimeOfDay startTime, TimeOfDay endTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Start time must not be null.");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time must not be null.");
        }

        if (startTime.compareTo(endTime) > 0) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
        /*if (startTime.compareTo(endTime) == 0) {
            throw new IllegalArgumentException("Start time and end time must not be equal.");
        }*/

        return new TimeRange(startTime, endTime);
    }

    //--------------------------------------------------------------------------
    //INSTANCE SCOPE
    //--------------------------------------------------------------------------

    private final TimeOfDay startTime;
    private final TimeOfDay endTime;

    private TimeRange(TimeOfDay startTime, TimeOfDay endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeOfDay startTime() {
        return startTime;
    }

    public TimeOfDay endTime() {
        return endTime;
    }

    public int asMinutes() {
        int hours = endTime().hour() - startTime().hour();
        int minutes = endTime().minute() - startTime().minute();

        int result = (hours * 60) + minutes;
        return result;
    }

    @Override
    public String toString() {
        return startTime() + " - " + endTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TimeRange)) {
            return false;
        }

        TimeRange that = (TimeRange) o;

        if (!this.startTime().equals(that.startTime())){
            return false;
        }

        return this.endTime().equals(that.endTime());
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + startTime().hashCode();
        result = result * 37 + endTime().hashCode();

        return result;
    }
}
