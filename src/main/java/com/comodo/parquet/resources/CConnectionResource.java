package com.comodo.parquet.resources;

import com.comodo.parquet.service.CConnectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * can | 8/23/17.
 */
@Api("/connection")
@Path("/connection")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CConnectionResource {

    private final CConnectionService service;

    public CConnectionResource(CConnectionService service) {
        this.service = service;
    }

    @GET
    @Path("/commandTest")
    @ApiOperation(
        value = "Command Connection Test",
        notes = "Connection's command api, returns a random double as value for 'CommandTest' key.",
        response = Map.class
    )
    public Map<String, Double> test() {
        return new HashMap<String, Double>() {{
            put("CommandTest", new Random().nextDouble());
        }};
    }

}
