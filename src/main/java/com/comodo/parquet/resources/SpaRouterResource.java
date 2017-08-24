package com.comodo.parquet.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * can | 8/23/17.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SpaRouterResource {

    private final String spaPath;

    public SpaRouterResource(String spaPath) {
        this.spaPath = spaPath;
    }

    @GET
    public Response redirect() {
        return Response.seeOther(URI.create(spaPath)).build();
    }

}
