package cz.jalasoft.mobile.swimming.android.activity.fragment;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.util.Optional;

/**
 * Helper class whose responsibility is to render attendance
 * on a freagment or when no attendance is available.
 *
 * Created by Honza "Honzales" Lastovicka on 2/22/16.
 */
final class PoolStatusRenderer {

    final AttendanceDisplayFragment fragment;

    PoolStatusRenderer(AttendanceDisplayFragment fragment) {
        this.fragment = fragment;
    }

    void render(Optional<PoolStatus> maybeStatus) {
        if (maybeStatus.isNotPresent()) {
            renderNoStatus();
        } else {
            renderStatus(maybeStatus.get());
        }
    }

    private void renderNoStatus() {
        fragment.displayAttendancePercentage("");
        fragment.displayAttendanceTotal("  ?");
    }

    private void renderStatus(PoolStatus status) {
        fragment.displayAttendanceTotal(formatAttendanceString(status));
        fragment.displayAttendancePercentage(formatAttendancePercentage(status));

        if (status.isOpen()) {
            fragment.displayOpen();
        } else {
            fragment.displayClosed();
        }
    }

    private String formatAttendanceString(PoolStatus status) {
        String attendanceString = String.valueOf(status.attendanceTotal());
        return " " + attendanceString;
    }

    private String formatAttendancePercentage(PoolStatus status) {
        return status.attendancePercentage() + "%";
    }
}
