package cz.jalasoft.mobile.swimming.infrastructure.service;

import java.net.MalformedURLException;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPool;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolService;

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
    public SwimmingPool getSwimmingPool() throws SwimmingPoolException {
        WebPage poolPage = WebPage.loadPage(pageUrl);
        SwimmingPool pool = SwimmingPoolConverter.swimmingPool(poolPage);
        return pool;
    }


    public static void main(String[] args) throws SwimmingPoolException {
        WebPageSwimmingPoolService service = new WebPageSwimmingPoolService("http://vodnisvetkolin.cz/Default.aspx");

        SwimmingPool info = service.getSwimmingPool();
        System.out.println(info);
    }
}
