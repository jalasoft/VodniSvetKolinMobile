package cz.jalasoft.mobile.swimming.android;

import cz.jalasoft.mobile.swimming.application.ApplicationService;
import cz.jalasoft.mobile.swimming.infrastructure.DomainRegistry;

/**
 * Created by Honza "Honzales" Lastovicka on 1/16/16.
 */
public final class Application extends android.app.Application {

    private static Application instance;

    private final ApplicationService applicationService;

    public Application() {
        this.applicationService = new ApplicationService();
    }

    @Override
    public void onCreate() {
        Application.instance = this;

        super.onCreate();
    }

    public static ApplicationService applicationService() {
        return Application.instance.applicationService;
    }
}
