package cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus.dummy;

import java.util.Arrays;
import java.util.List;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolException;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.CallbackAsyncTask;
import cz.jalasoft.mobile.swimming.util.Provider;

/**
 * Created by Honza "Honzales" Lastovicka on 2/20/16.
 */
public final class DummyPoolStatusService implements PoolStatusService {

    private static final List<PoolStatus> STATUSES = Arrays.asList(
            PoolStatus.open(100, 50),
            PoolStatus.open(102, 50),
            PoolStatus.open(103, 50),
            PoolStatus.open(104, 50),
            PoolStatus.open(105, 50),
            PoolStatus.open(106, 50),
            PoolStatus.open(107, 50),
            PoolStatus.open(108, 50),
            PoolStatus.open(109, 50),
            PoolStatus.open(110, 50),
            PoolStatus.open(111, 50),
            PoolStatus.open(112, 50),
            PoolStatus.open(113, 50)
            );

    private int position = 0;

    public DummyPoolStatusService() {

    }

    @Override
    public PoolStatus getStatus() throws PoolException {
        int index = position % STATUSES.size();
        PoolStatus result = STATUSES.get(index);

        position++;
        return result;
    }

    @Override
    public void getStatusAsynchronously(AsyncCallback<PoolStatus> callback) {
        Provider<PoolStatus> provider = new Provider() {
            @Override
            public Object get() throws Exception {
                return getStatus();
            }
        };

        new CallbackAsyncTask(provider, callback).execute();
    }
}
