package controllers;

import com.wordnik.swagger.core.*;
import models.Pet;
import play.mvc.Controller;
import play.mvc.Result;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Api(value = "/pet", description = "Operations about pets")
public class PetApiController extends Controller {

    @Path("/{id}")
    @ApiOperation(value = "Find pet by ID", notes = "Returns a pet when ID < 10. " +
            "ID > 10 or nonintegers will simulate API error conditions", responseClass = "Pet", httpMethod = "GET")
//    @ApiParamsImplicit(
//            @ApiParamImplicit(name = "id", value = "ID of pet that needs to be fetched", required = true, dataType = "String", paramType = "path",
//                    allowableValues = "range[0,10]"))
    @ApiErrors({@ApiError(code = 400, reason = "Invalid ID supplied"),
                @ApiError(code = 404, reason = "Pet not found")})

    @QueryParam("status")
    public static Result getPetById(@ApiParam(value = "Status values that need to be considered for filter", required = true, defaultValue = "available",
            allowableValues = "available,pending,sold", allowMultiple = true) String id) {
        Pet pet = new Pet();
//        return JsonResponse(pet);
        return ok();
    }
}
