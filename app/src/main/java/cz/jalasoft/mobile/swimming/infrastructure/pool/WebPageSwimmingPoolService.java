package cz.jalasoft.mobile.swimming.infrastructure.pool;

import java.net.MalformedURLException;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.pool.SwimmingPoolService;

/**
 * Created by lastovicka on 1/2/16.
 */
public final class WebPageSwimmingPoolService implements SwimmingPoolService {

    private URL pageUrl;

    public WebPageSwimmingPoolService(String pageUrl) {
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
    public SwimmingPool loadSwimmingPool() throws SwimmingPoolException {
        WebPage poolPage = WebPage.loadPage(pageUrl);
        SwimmingPool pool = SwimmingPoolConverter.swimmingPool(poolPage);
        return pool;
    }


    public static void main(String[] args) throws SwimmingPoolException {
        WebPageSwimmingPoolService service = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");

        SwimmingPool info = service.loadSwimmingPool();
        System.out.println(info);
    }
}
