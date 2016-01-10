package cz.jalasoft.mobile.swimming.domain.model;

/**
 * A service that provides a snapshot of a swimming pool status.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public interface SwimmingPoolService {

    /**
     * Gets a swimming pool ststaus
     * @return never null
     * @throws SwimmingPoolException if there is a connection issue or data format issue.
     */
    SwimmingPool getSwimmingPool() throws SwimmingPoolException;
}
