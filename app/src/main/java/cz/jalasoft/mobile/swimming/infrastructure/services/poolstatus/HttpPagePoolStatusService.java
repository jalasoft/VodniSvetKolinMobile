package cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MalformedURLException;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolException;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.CallbackAsyncTask;
import cz.jalasoft.mobile.swimming.util.Optional;
import cz.jalasoft.mobile.swimming.util.Provider;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class HttpPagePoolStatusService implements PoolStatusService {

    private URL pageUrl;
    private ConnectivityManager connectivityManager;

    public HttpPagePoolStatusService(String pageUrl, Context context) {
        setUrl(pageUrl);
        this.connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    private  void setUrl(String pageUrl) {
        if (pageUrl == null) {
            throw new IllegalArgumentException("Page URL must not be null.");
        }
        if (pageUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Page URL must not be nut be empty.");
        }

        try {
            this.pageUrl = new URL(pageUrl);
        } catch (MalformedURLException exc) {
            throw new IllegalArgumentException("Page URL must be a valid URL.", exc);
        }
    }

    @Override
    public Optional<PoolStatus> getStatus() throws PoolException {

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return Optional.empty();
        }

        WebPage poolPage = WebPage.loadPage(pageUrl);
        PoolStatus pool = PoolStatusFactory.from(poolPage);

        return Optional.of(pool);
    }

    @Override
    public void getStatusAsynchronously(AsyncCallback<Optional<PoolStatus>> callback) {
        Provider<PoolStatus> provider = new Provider() {
            @Override
            public Object get() throws Exception {
                return getStatus();
            }
        };

        new CallbackAsyncTask(provider, callback).execute();
    }
}
