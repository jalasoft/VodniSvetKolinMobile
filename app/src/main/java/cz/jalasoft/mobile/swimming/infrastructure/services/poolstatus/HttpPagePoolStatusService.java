package cz.jalasoft.mobile.swimming.infrastructure.services.poolstatus;

import java.net.MalformedURLException;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.status.PoolException;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatus;
import cz.jalasoft.mobile.swimming.domain.model.status.PoolStatusService;
import cz.jalasoft.mobile.swimming.util.AsyncCallback;
import cz.jalasoft.mobile.swimming.util.CallbackAsyncTask;
import cz.jalasoft.mobile.swimming.util.Provider;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class HttpPagePoolStatusService implements PoolStatusService {

    private URL pageUrl;

    public HttpPagePoolStatusService(String pageUrl) {
        setUrl(pageUrl);
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
    public PoolStatus getStatus() throws PoolException {
        WebPage poolPage = WebPage.loadPage(pageUrl);
        PoolStatus pool = PoolStatusFactory.from(poolPage);
        return pool;
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

    public static void main(String[] args) throws PoolException {
        HttpPagePoolStatusService service = new HttpPagePoolStatusService("http://vodnisvetkolin.cz/Default.aspx");

        PoolStatus info = service.getStatus();
        System.out.println(info);
    }
}
