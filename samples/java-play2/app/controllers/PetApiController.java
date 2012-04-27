package controllers;

import com.wordnik.swagger.core.*;
import play.libs.Json;
import play.mvc.Result;

import javax.ws.rs.Path;

import static play.mvc.Results.ok;

@Api(value = "/pet", description = "Operations about pets")
public class PetApiController {

    @Path("/{id}")
    @ApiOperation(value = "Find pet by ID", notes = "Returns a pet when ID < 10. " +
            "ID > 10 or nonintegers will simulate API error conditions", responseClass = "Pet", httpMethod = "GET")
    @ApiParamsImplicit(
            @ApiParamImplicit(name = "id", value = "ID of pet that needs to be fetched", required = true, dataType = "String", paramType = "path",
                    allowableValues = "range[0,10]"))
    @ApiErrors(value = {@ApiError(code = 400, reason = "Invalid ID supplied"),
                @ApiError(code = 404, reason = "Pet not found")})
    public static Result getPetById(Long id) {
        return ok(Json.toJson("hello pets!"));
    }

}
