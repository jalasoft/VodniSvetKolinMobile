package cz.jalasoft.mobile.swimming.domain.model.track;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

/**
 * A value representing a point in time of a day, like 04:56.
 *
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class TimeOfDay implements Comparable<TimeOfDay> {

    private static final NumberFormat FORMAT = NumberFormat.getIntegerInstance();
    static {
        FORMAT.setMaximumIntegerDigits(2);
        FORMAT.setMinimumIntegerDigits(2);
    }

    private static final Pattern PATTERN = Pattern.compile("(\\d{2}):(\\d{2})");

    public static TimeOfDay from(String iso) {
        if (iso == null || iso.trim().isEmpty()) {
            throw new IllegalArgumentException("Time must not be null or empty.");
        }

        Matcher matcher = PATTERN.matcher(iso);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Time must match pattern '\\d{2}:\\d{2}");
        }

        String hour = matcher.group(1);
        String minute = matcher.group(2);

        return from(parseInt(hour), parseInt(minute));
    }

    public static TimeOfDay from(int hour, int minute) {
        if (!(0 <= hour && hour <= 23)) {
            throw new IllegalArgumentException("Hour must be between 0 and 23 including");
        }
        if (!(0 <= minute && minute <= 59)) {
            throw new IllegalArgumentException("Minute must be between 0 and 59 inclusing");
        }

        return new TimeOfDay(hour, minute);
    }

    //---------------------------------------------------------------
    //INSTANCE SCOPE
    //---------------------------------------------------------------

    private final int hour;
    private final int minute;

    private TimeOfDay(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }

    public TimeOfDay plusMinute(int minutes) {
        int ultimateMinutes = minute() + minutes;

        int additionalHours = ultimateMinutes / 60;
        int additionalMinutes = ultimateMinutes % 60;

        int newHour = hour() + additionalHours;
        int newMinute = minute() + additionalMinutes;

        return TimeOfDay.from(newHour, newMinute);
    }

    @Override
    public int compareTo(TimeOfDay that) {
        if (this.hour() > that.hour()) {
            return 1;
        }

        if (this.hour() < that.hour()) {
            return -1;
        }

        if (this.minute() > that.minute()) {
            return 1;
        }

        if (this.minute() < that.minute()) {
            return -1;
        }

        return 0;
    }

    @Override
    public String toString() {
        return FORMAT.format(hour()) + ":" + FORMAT.format(minute());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof TimeOfDay)) {
            return false;
        }

        TimeOfDay that = (TimeOfDay) o;

        if (this.hour() != that.hour()) {
            return false;
        }

        return this.minute() == that.minute();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + hour();
        result = result * 37 + minute();

        return result;
    }
}
