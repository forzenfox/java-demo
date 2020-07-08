package org.dylan.demo.rest;

import org.apache.aries.blueprint.annotation.service.Reference;
import org.dylan.demo.api.ITest;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RestProvider {
    public static ITest test;

    @Inject
    public RestProvider(
            @Reference(filter = "(type=proxy)") ITest test) {
        RestProvider.test = test;
    }
}
