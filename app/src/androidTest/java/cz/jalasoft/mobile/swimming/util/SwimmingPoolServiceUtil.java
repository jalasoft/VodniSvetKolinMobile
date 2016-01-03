package cz.jalasoft.mobile.swimming.util;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.ServiceRegistry;

/**
 * Created by lastovicka on 1/3/16.
 */
public class SwimmingPoolServiceUtil {

    public static void main(String[] args) throws SwimmingPoolException {

        SwimmingPoolService service = ServiceRegistry.swimmingPoolService();
        SwimmingPool pool = service.basicInfo();

        System.out.println("Attendance: " + pool.attendance());
    }
}
