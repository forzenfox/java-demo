package org.dylan.demo.web;

import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLoaderResources {
    private static Logger logger = LoggerFactory.getLogger(WebLoaderResources.class);

    public void loaderResource(HttpService httpService) {
        logger.info("WebLoaderResources{} start");
        try {
            httpService.registerResources("/swagger", "/swagger", null);
        } catch (NamespaceException e) {
            logger.error("loaderResource error:", e);
        }
    }

    public void unLoaderResource(HttpService httpService) {
        try {
            httpService.unregister("/swagger");
        } catch (Exception e) {
            logger.error("unLoaderResource error:", e);
        }
    }
}
