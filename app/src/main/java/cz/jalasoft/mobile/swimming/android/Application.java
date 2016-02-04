package cz.jalasoft.mobile.swimming.android;

import cz.jalasoft.mobile.swimming.application.PoolApplicationService;
import cz.jalasoft.mobile.swimming.infrastructure.ServiceRegistry;

/**
 * Created by Honza "Honzales" Lastovicka on 1/16/16.
 */
public final class Application extends android.app.Application {

    private static Application instance;

    private final ServiceRegistry serviceRegistry;

    public Application() {
        this.serviceRegistry = new ServiceRegistry();
    }

    @Override
    public void onCreate() {
        serviceRegistry.init(this);
        Application.instance = this;

        super.onCreate();
    }

    public static PoolApplicationService applicationService() {
        return Application.instance.serviceRegistry.applicationService();
    }
}
