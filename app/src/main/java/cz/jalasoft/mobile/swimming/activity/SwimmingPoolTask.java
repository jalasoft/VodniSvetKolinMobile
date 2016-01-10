package cz.jalasoft.mobile.swimming.activity;

import android.os.AsyncTask;

import cz.jalasoft.mobile.swimming.ValueOrException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;
import cz.jalasoft.mobile.swimming.infrastructure.DomainRegistry;

/**
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
final class SwimmingPoolTask extends AsyncTask<Void, Void, ValueOrException<SwimmingPool, SwimmingPoolException>> {

    private final SwimmingPoolDisplay display;

    SwimmingPoolTask(SwimmingPoolDisplay display) {
        this.display = display;
    }

    @Override
    protected ValueOrException<SwimmingPool, SwimmingPoolException> doInBackground(Void... params) {
        SwimmingPoolService service = DomainRegistry.swimmingPoolService();

        try {
            SwimmingPool pool = service.getSwimmingPool();
            return ValueOrException.success(pool);
        } catch (SwimmingPoolException exc) {
            return ValueOrException.exception(exc);
        }
    }


    @Override
    protected void onPostExecute(ValueOrException<SwimmingPool, SwimmingPoolException> result) {
        if (result.isFail()) {
            display.handleFail(result.exception());
        } else {
            display.update(result.value());
        }

    }
}
