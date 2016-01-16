package cz.jalasoft.mobile.swimming.infrastructure.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.jalasoft.mobile.swimming.domain.model.SwimmingPoolException;
import cz.jalasoft.mobile.swimming.infrastructure.service.exception.SwimmingPoolNotAvailableException;

/**
 * Created by lastovicka on 1/2/16.
 */
final class WebPage {

    static WebPage loadPage(URL url) throws SwimmingPoolException {
        String pageContent = readPageContent(connection(url));
        return new WebPage(pageContent);
    }

    private static HttpURLConnection connection(URL pageUrl) throws SwimmingPoolException {
        try {
            HttpURLConnection connection = (HttpURLConnection) pageUrl.openConnection();
            return connection;
        } catch (IOException exc) {
            throw new SwimmingPoolNotAvailableException(pageUrl, exc);
        }
    }

    private static String readPageContent(HttpURLConnection connection) throws SwimmingPoolException {
        InputStream input = null;
        IOException finalExc = null;
        StringBuilder bldr = new StringBuilder();

        try {
            input = connection.getInputStream();
            Reader reader = new InputStreamReader(input);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                bldr.append(line);
            }

        } catch (IOException exc) {
            finalExc = exc;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException exc) {
                    if (finalExc != null) {
                        throw new SwimmingPoolNotAvailableException(connection.getURL(), finalExc);
                    } else {
                        throw new SwimmingPoolNotAvailableException(connection.getURL(), exc);
                    }
                }
            }
        }
        return bldr.toString();
    }

    //-----------------------------------------------------------------------------------
    //INSTANCE SCOPE
    //-----------------------------------------------------------------------------------

    private final String pageContent;

    private WebPage(String pageContent) {
        this.pageContent = pageContent;
    }

    String content() {
        return pageContent;
    }

    //TODO
}
