package cz.jalasoft.mobile.swimming.domain.model.status;

import cz.jalasoft.mobile.swimming.util.AsyncCallback;

/**
 * A applicationService that provides a snapshot of a swimming pool poolStatus.
 *
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public interface PoolStatusService {

    /**
     * Gets a swimming pool poolStatus
     *
     * @return never null
     * @throws PoolException if there is a connection issue or data format issue.
     */
    PoolStatus getStatus() throws PoolException;

    void getStatusAsynchronously(AsyncCallback<PoolStatus> callback);
}
