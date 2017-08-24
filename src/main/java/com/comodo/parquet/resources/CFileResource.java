package com.comodo.parquet.resources;

import com.comodo.parquet.service.CFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * can | 8/23/17.
 */
@Api("/fileUpload")
@Path("/fileUpload")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CFileResource {

    private final CFileServiceImpl service;

    public CFileResource(CFileServiceImpl service) {
        this.service = service;
    }

    @POST
    @ApiOperation(
        value = "parquet file upload",
        notes = "parquet file upload api",
        response = Response.class
    )
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response uploadFile(
        @FormDataParam("file") InputStream inputStream,
        @FormDataParam("file") FormDataContentDisposition contentDisposition
    ) throws Exception {
        java.nio.file.Path tempDirectory = Files.createTempDirectory("parquet-upload-");
        java.nio.file.Path tempFile = Paths.get(tempDirectory.toString(), contentDisposition.getFileName());
        Files.copy(inputStream, tempFile);
        service.read(tempFile);
        return Response.ok("File upload successful").build();
    }

}
