package cz.jalasoft.mobile.swimming.activity;

import android.os.AsyncTask;

import cz.jalasoft.mobile.swimming.ValueOrException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.ServiceRegistry;

/**
 * Created by lastovicka on 1/2/16.
 */
final class SwimmingPoolTask extends AsyncTask<Void, Void, ValueOrException<SwimmingPool>> {

    private final MainActivity activity;

    SwimmingPoolTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        activity.showProgress();
    }

    @Override
    protected ValueOrException<SwimmingPool> doInBackground(Void... params) {
        SwimmingPoolService service = ServiceRegistry.swimmingPoolService();

        try {
            SwimmingPool pool = service.basicInfo();
            return ValueOrException.success(pool);
        } catch (SwimmingPoolException exc) {
            return ValueOrException.exception(exc);
        }
    }


    @Override
    protected void onPostExecute(ValueOrException<SwimmingPool> result) {
        activity.hideProgress();

        if (result.isError()) {
            activity.handleException(result.exception());
        } else {
            activity.displaySwimmingPool(result.value());
        }

    }
}
