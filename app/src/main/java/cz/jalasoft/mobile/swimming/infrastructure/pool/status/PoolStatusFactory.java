package cz.jalasoft.mobile.swimming.infrastructure.pool.status;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.jalasoft.mobile.swimming.domain.model.pool.status.PoolStatus;
import cz.jalasoft.mobile.swimming.infrastructure.pool.status.exception.SwimmingPoolContentPageDoesNotMatchException;

/**
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class PoolStatusFactory {

    private static final Pattern ATTENDANCE_TOTAL_PATTERN = Pattern.compile("<div class=\"bubble\">\\s*<div class=\"value\">\\s*(\\d+)\\s*</div>\\s*</div>");
    private static final Pattern ATTENDANCE_PERCENTAGE_PATTERN = Pattern.compile("setRunningText\\(.*V aquaparku je právě \\d+ návštěvníků \\((\\d{1,2}\\.\\d{1,2})% kapacity\\)");
    private static final Pattern IS_OPEN_PATTERN = Pattern.compile("<div class=\"semaphoreGreen\"></div>");
    private static final Pattern IS_CLOSE_TO_CLOSED = Pattern.compile("<div class=\"semaphoreOrange\"></div>");
    private static final Pattern IS_CLOSED_PATTERN = Pattern.compile("<div class=\"semaphoreRed\"></div>");

    static PoolStatus from(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        boolean isOpen = readOpenClosed(page);

        if (!isOpen) {
            return PoolStatus.closed();
        }

        int attendanceTotal = readAttendanceTotal(page);
        float attendancePercentage = readAttendancePercentage(page);

        return PoolStatus.open(attendanceTotal, attendancePercentage);
    }

    private static int readAttendanceTotal(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        Matcher m = ATTENDANCE_TOTAL_PATTERN.matcher(page.content());

        boolean found = m.find();

        if (!found) {
            throw new SwimmingPoolContentPageDoesNotMatchException("Cannot find occurrence of an attendance total pattern: " + ATTENDANCE_TOTAL_PATTERN);
        }

        String attendanceAsString = m.group(1);
        return Integer.parseInt(attendanceAsString);
    }

    private static float readAttendancePercentage(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        Matcher m = ATTENDANCE_PERCENTAGE_PATTERN.matcher(page.content());

        boolean found = m.find();
        if (!found) {
            throw new SwimmingPoolContentPageDoesNotMatchException("Cannot find occurrence of an attendance percentage pattern: " + ATTENDANCE_PERCENTAGE_PATTERN);
        }

        String percentageString = m.group(1);
        float result = Float.parseFloat(percentageString);

        return result;
    }

    private static boolean readOpenClosed(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        Matcher isOpenMatcher = IS_OPEN_PATTERN.matcher(page.content());

        boolean isOpen = isOpenMatcher.find();

        if (isOpen) {
            return true;
        }

        Matcher isClosedMatcher = IS_CLOSED_PATTERN.matcher(page.content());

        boolean isClosed = isClosedMatcher.find();

        if (isClosed) {
            return false;
        }

        Matcher isCloseToClosedMatcher = IS_CLOSE_TO_CLOSED.matcher(page.content());

        boolean isCloseToClosed = isCloseToClosedMatcher.find();

        if (isCloseToClosed) {
            return true;
        }

        throw new SwimmingPoolContentPageDoesNotMatchException("Cannot find occurrence of open/closed pattern: " + IS_OPEN_PATTERN + ", " + IS_CLOSED_PATTERN);
    }

}
