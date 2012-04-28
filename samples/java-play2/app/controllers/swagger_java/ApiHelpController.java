package controllers.swagger_java;

import play.modules.swagger_java.ApiHelpInventory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller exposes swagger compatiable help apis.<br/>
 * The routing for the two apis supported by this controller is automatically injected by SwaggerPlugin
 *
 *
 */

public class ApiHelpController extends Controller {

    public static Result getResources() {
        return ok(ApiHelpInventory.getRootHelpJson());
    }

    public static Result getResource(String path) {
        return ok(ApiHelpInventory.getPathHelpJson(path));
    }
//        def getResources()=Action{
//        request=>
//        implicit val requestHeader:RequestHeader=request;
//        val resources=if(returnXml(request))ApiHelpInventory.getRootHelpXml()else ApiHelpInventory.getRootHelpJson()
//        System.out.println("Getting resources...");
//        returnValue(request,resources)
//        }
//
//        def getResource(path:String)=Action{
//        request=>
//        implicit val requestHeader:
//        RequestHeader =request;
//        val help=if(returnXml(request))ApiHelpInventory.getPathHelpXml(path)else ApiHelpInventory.getPathHelpJson(path)
//        returnValue(request,help)
//        }
//        }

//class SwaggerBaseApiController extends Controller {
//  protected def jaxbContext(): JAXBContext = JAXBContext.newInstance(classOf[String])
//
//  protected def returnXml(request: Request[_]) = request.path.contains(".xml")
//
//  protected val ok = "OK"
//  protected val AccessControlAllowOrigin = ("Access-Control-Allow-Origin", "*")
//
//  protected def XmlResponse(o: Any) = {
//    val xmlValue = {
//      if (o.getClass.equals(classOf[String])) {
//        o.asInstanceOf[String]
//      } else {
//        val stringWriter = new StringWriter()
//        jaxbContext.createMarshaller().marshal(o, stringWriter)
//        stringWriter.toString
//      }
//    }
//    new SimpleResult[String](header = ResponseHeader(200), body = play.api.libs.iteratee.Enumerator(xmlValue)).as("application/xml")
//  }
//
//  protected def returnValue(request: Request[_], obj: Any): Result = {
//    val response = if (returnXml(request)) XmlResponse(obj) else JsonResponse(obj)
//    response.withHeaders(AccessControlAllowOrigin)
//  }
//
//  protected def JsonResponse(data: Any) = {
//    val jsonValue: String = {
//      if (data.getClass.equals(classOf[String])) {
//        data.asInstanceOf[String]
//      } else {
//        val mapper = new ObjectMapper()
//        val w = new StringWriter()
//
//        mapper.getSerializationConfig().disable(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS)
//        mapper.writeValue(w, data)
//
//        w.toString()
//      }
//    }
//    new SimpleResult[String](header = ResponseHeader(200), body = play.api.libs.iteratee.Enumerator(jsonValue)).as("application/json")
//  }
}
