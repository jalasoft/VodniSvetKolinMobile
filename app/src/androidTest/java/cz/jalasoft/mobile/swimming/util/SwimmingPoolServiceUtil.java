package cz.jalasoft.mobile.swimming.util;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolException;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.HttpPagePoolStatusService;

/**
 * Created by lastovicka on 1/3/16.
 */
public class SwimmingPoolServiceUtil {

    public static void main(String[] args) throws PoolException {

        PoolStatusService service = new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx");
        PoolStatus pool = service.getStatus();

        System.out.println("Attendance: " + pool.attendanceTotal());
    }
}
