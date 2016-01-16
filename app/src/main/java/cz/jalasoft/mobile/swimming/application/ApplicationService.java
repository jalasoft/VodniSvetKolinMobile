package cz.jalasoft.mobile.swimming.application;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.DomainRegistry;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.android.CallbackAsyncTask;
import cz.jalasoft.mobile.swimming.util.Provider;

/**
 * Created by Honza "Honzales" Lastovicka on 1/16/16.
 */
public final class ApplicationService {

    private final DomainRegistry domainRegistry;

    public ApplicationService() {
        this.domainRegistry = new DomainRegistry();
        this.domainRegistry.init();
    }

    public void getSwimmingPool(AsyncCallback<SwimmingPool> callback) {
        final SwimmingPoolService service = registry().swimmingPoolService();

        Provider<SwimmingPool> poolProvider = new Provider() {
            @Override
            public Object get() throws Exception {
                return service.getSwimmingPool();
            }
        };

       asynchronously(poolProvider, callback);
    }

    private void asynchronously(Provider<SwimmingPool> provider, AsyncCallback<SwimmingPool> callback) {
        Void[] p = null;
        new CallbackAsyncTask(provider, callback).execute();
    }

    private DomainRegistry registry() {
        return domainRegistry;
    }
}
