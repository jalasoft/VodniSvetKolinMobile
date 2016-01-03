package cz.jalasoft.mobile.swimming.infrastructure.webpage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.infrastructure.webpage.exception.SwimmingPoolContentPageDoesNotMatchException;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class SwimmingPoolConverter {

    private static final Pattern ATTENDANCE_PATTERN = Pattern.compile("<div class=\"bubble\">\\s*<div class=\"value\">\\s*(\\d+)\\s*</div>\\s*</div>");

    static SwimmingPool swimmingPool(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        int attendance = readAttendance(page);

        return new SwimmingPool(attendance);
    }

    private static int readAttendance(WebPage page) throws SwimmingPoolContentPageDoesNotMatchException {
        Matcher m = ATTENDANCE_PATTERN.matcher(page.content());

        boolean found = m.find();

        if (!found) {
            throw new SwimmingPoolContentPageDoesNotMatchException("Cannot find occurrence of an attendance pattern: " + ATTENDANCE_PATTERN);
        }

        String attendanceAsString = m.group(1);
        return Integer.parseInt(attendanceAsString);
    }
}
