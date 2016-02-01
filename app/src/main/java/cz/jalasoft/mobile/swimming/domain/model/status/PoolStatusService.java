package cz.jalasoft.mobile.swimming.domain.model.status;

/**
 * A service that provides a snapshot of a swimming pool status.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public interface PoolStatusService {

    /**
     * Gets a swimming pool status
     *
     * @return never null
     * @throws PoolException if there is a connection issue or data format issue.
     */
    PoolStatus getStatus() throws PoolException;
}
