package cz.jalasoft.mobile.swimming.util;

import android.os.AsyncTask;

/**
 * Created by Honza "Honzales" Lastovicka on 1/2/16.
 */
public final class CallbackAsyncTask<T> extends AsyncTask<Void, Void, ValueOrException<T, Exception>> {

    private final Provider<T> provider;
    private final AsyncCallback<T> callback;

    public CallbackAsyncTask(Provider<T> provider, AsyncCallback<T> callback) {
        this.provider = provider;
        this.callback = callback;
    }

    @Override
    protected ValueOrException<T, Exception> doInBackground(Void... params) {
        try {
            T result = provider.get();
            return ValueOrException.success(result);
        } catch (Exception exc) {
            return ValueOrException.exception(exc);
        }
    }

    @Override
    protected void onPostExecute(ValueOrException<T, Exception> result) {
        if (result.isFail()) {
            callback.processFail(result.exception());
        } else {
            callback.process(result.value());
        }
    }

    public void execute() {
        Void[] voids = null;
        super.execute(voids);
    }
}
