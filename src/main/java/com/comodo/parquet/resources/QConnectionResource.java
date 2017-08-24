package com.comodo.parquet.resources;

import com.comodo.parquet.service.QConnectionService;

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
@Path("/connection")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QConnectionResource {

    private final QConnectionService service;

    public QConnectionResource(QConnectionService service) {
        this.service = service;
    }

    @GET
    @Path("/queryTest")
    public Map<String, Double> test() {
        return new HashMap<String, Double>() {{
            put("QueryTest", new Random().nextDouble());
        }};
    }

}
