package org.dylan.demo.rest.resources;

import com.sun.jersey.spi.resource.Singleton;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dylan.demo.rest.RestProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(tags = "测试接口")
@Singleton
@Path("/test")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class TestResources {

    @GET
    @ApiOperation(value = "测试日志注解", notes = "日志注解功能测试方法", httpMethod = "GET")
    public Response connect() {
        return Response.ok().entity(RestProvider.test.test()).build();
    }

    @POST
    @ApiOperation(value = "无注解方法")
    public Response test() {
        return Response.ok().entity(RestProvider.test.test01()).build();
    }
}
