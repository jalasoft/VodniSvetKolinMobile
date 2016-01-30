package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.android.CallbackAsyncTask;
import cz.jalasoft.mobile.swimming.domain.model.pool.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.pool.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.Provider;

/**
 * Created by Honza "Honzales" Lastovicka on 1/30/16.
 */
public final class PoolStatusApplicationService {

    private final PoolStatusService statusService;

    public PoolStatusApplicationService(PoolStatusService statusService) {
        this.statusService = statusService;
    }

    public void status(AsyncCallback<PoolStatus> callback) {
        Provider<PoolStatus> poolProvider = new Provider() {
            @Override
            public Object get() throws Exception {
                return statusService.getStatus();
            }
        };

        asynchronously(poolProvider, callback);
    }

    private void asynchronously(Provider<PoolStatus> provider, AsyncCallback<PoolStatus> callback) {
        new CallbackAsyncTask(provider, callback).execute();
    }
}
