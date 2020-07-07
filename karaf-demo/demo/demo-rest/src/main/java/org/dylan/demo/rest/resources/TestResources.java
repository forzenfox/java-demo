package org.dylan.demo.rest.resources;

import com.sun.jersey.spi.resource.Singleton;
import org.dylan.demo.rest.RestProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("/test")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class TestResources {

    @GET
    public Response connect() {
        return Response.ok().entity(RestProvider.test.test()).build();
    }

    @POST
    public Response test() {
        return Response.ok().entity(RestProvider.test.test01()).build();
    }
}
