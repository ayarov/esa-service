package yarov.esa;

import yarov.esa.datamodel.SimilarityRequest;
import yarov.esa.datamodel.SimilarityResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Base64;

/**
 * Root resource (exposed at "esa" path)
 */
@Path("esa")
public class EsaResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimilarity(@QueryParam("textA") String textA,
                                  @QueryParam("textB") String textB,
                                  @DefaultValue("false") @QueryParam("encoded") Boolean encoded) {
        SimilarityResponse response = new SimilarityResponse();
        if (encoded)
        {
            byte[] bytesA = Base64.getDecoder().decode(textA);
            byte[] bytesB = Base64.getDecoder().decode(textB);

            response.score = EsaProvider.getInstance().findSemanticSimilarity(bytesA, bytesB);
            return Response.status(Response.Status.OK).entity(response).build();
        }
        else
        {
            response.score = EsaProvider.getInstance().findSemanticSimilarity(textA, textB);
            return Response.status(Response.Status.OK).entity(response).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSimilarity(SimilarityRequest request) {
        return getSimilarity(request.textA, request.textB, request.encoded);
    }

}
