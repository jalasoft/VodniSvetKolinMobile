package cz.jalasoft.mobile.swimming.domain.model;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class SwimmingPool {

    private final int attendance;

    public SwimmingPool(int attendance) {
        if (attendance < 0) {
            throw new IllegalArgumentException("Attendance must not be negative");
        }
        this.attendance = attendance;
    }

    public int attendance() {
        return attendance;
    }

    @Override
    public String toString() {
        return "SwimmingPool[attendance=" + attendance() + "]";
    }
}
