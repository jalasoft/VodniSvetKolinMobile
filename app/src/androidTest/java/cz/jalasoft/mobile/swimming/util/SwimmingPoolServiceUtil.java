package cz.jalasoft.mobile.swimming.util;

import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.DomainRegistry;

/**
 * Created by lastovicka on 1/3/16.
 */
public class SwimmingPoolServiceUtil {

    public static void main(String[] args) throws SwimmingPoolException {

        DomainRegistry registry = new DomainRegistry();
        registry.init();
        SwimmingPoolService service = registry.swimmingPoolService();
        SwimmingPool pool = service.loadSwimmingPool();

        System.out.println("Attendance: " + pool.attendanceTotal());
    }
}
