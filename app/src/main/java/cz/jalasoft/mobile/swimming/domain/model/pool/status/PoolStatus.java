package cz.jalasoft.mobile.swimming.domain.model.pool.status;

/**
 * A value class representing a swimming pool, providing
 * basic information, as a snapshot of the status of the
 * real swimming pool in time.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class PoolStatus {

    /**
     * Creates a new swimming pool based on information about current attendanceTotal.
     *
     * @param attendanceTotal exact number of people in the swimming pool, must not be negative or zero.
     * @param attendancePercentage percentual occupacy of the swimming pool
     * @return never null
     * @throws IllegalArgumentException if attendanceTotal is negative or zero.
     */
    public static PoolStatus open(int attendanceTotal, float attendancePercentage) {
        if (attendanceTotal <= 0) {
            throw new IllegalArgumentException("Attendance total must not be negative or zero");
        }
        if (attendancePercentage <= 0 || 100 < attendancePercentage) {
            throw new IllegalArgumentException("Attendance percentage must not be breater than 100% or lower than 0%");
        }
        return new PoolStatus(true, attendanceTotal, attendancePercentage);
    }

    /**
     * Creates a new swimming pool that indicates that it is closed.
     *
     * @return never null
     */
    public static PoolStatus closed() {
        return new PoolStatus(false, 0, 0);
    }

    //--------------------------------------------------
    //INSTANCE SCOPE
    //--------------------------------------------------

    private final int attendanceTotal;
    private final float attendancePercentage;
    private final boolean isOpen;

    private PoolStatus(boolean isOpen, int attendanceTotal, float attendacePercentage) {
        this.isOpen = isOpen;
        this.attendanceTotal = attendanceTotal;
        this.attendancePercentage = attendacePercentage;
    }

    /**
     * Gets an attendanceTotal, might be zero in case the swimming pool is closed.
     * @return never negative.
     */
    public int attendanceTotal() {
        return attendanceTotal;
    }

    public float attendancePercentage() {
        return attendancePercentage;
    }
    /**
     * Gets whether the swimming pool is closed.
     * @return
     */
    public boolean isOpen() {
        return isOpen;
    }

    //--------------------------------------------------
    //OBJECT OVERRIDINGS
    //--------------------------------------------------

    @Override
    public String toString() {
        return "PoolStatus[status=" + (isOpen() ? "open" : "closed") + "attendanceTotal=" + attendanceTotal() + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(getClass().equals(o.getClass()))) {
            return false;
        }

        PoolStatus that = (PoolStatus) o;

        if (this.isOpen() != that.isOpen()) {
            return false;
        }

        return this.attendanceTotal() == that.attendanceTotal();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = result * 37 + (isOpen() ? 1 : 0);
        result = result * 37 + attendanceTotal();

        return result;
    }
}
