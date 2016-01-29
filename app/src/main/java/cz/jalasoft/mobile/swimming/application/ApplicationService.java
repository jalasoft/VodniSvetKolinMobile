package cz.jalasoft.mobile.swimming.application;

import android.app.Application;
import android.content.Context;

import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.domain.model.expectation.SwimmingPoolExpectation;
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
    }

    public void init(Context context) {
        this.domainRegistry.init(context);
    }

    public int maxSwimmingPoolAttendanceExpectation() {
        return registry().swimmingPoolExpectation().maxAttendanceBoundary();
    }

    public int swimmingPoolAttendanceExpectation() {
        return registry().swimmingPoolExpectation().attendanceBoundary();
    }

    public void changeSwimmingPoolAttendanceExpectation(int newExpectation) {
        registry().swimmingPoolExpectation().changeAttendanceBoundary(newExpectation);
    }

    public void loadSwimmingPool(AsyncCallback<SwimmingPool> callback) {
        final SwimmingPoolService service = registry().swimmingPoolService();

        Provider<SwimmingPool> poolProvider = new Provider() {
            @Override
            public Object get() throws Exception {
                return service.loadSwimmingPool();
            }
        };

       asynchronously(poolProvider, callback);
    }

    private void asynchronously(Provider<SwimmingPool> provider, AsyncCallback<SwimmingPool> callback) {
        new CallbackAsyncTask(provider, callback).execute();
    }

    private DomainRegistry registry() {
        return domainRegistry;
    }
}
