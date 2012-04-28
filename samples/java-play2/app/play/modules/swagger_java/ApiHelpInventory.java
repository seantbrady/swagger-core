package play.modules.swagger_java;

import com.wordnik.swagger.core.Api;
import com.wordnik.swagger.core.Documentation;
import com.wordnik.swagger.core.DocumentationEndPoint;
import com.wordnik.swagger.core.util.JsonUtil;
import play.Logger;
import play.Play;

import java.io.IOException;
import java.util.*;

/**
 * Exposes two primay methods: to get a list of available resources and to get details on a given resource
 */
public class ApiHelpInventory {

    private static final String swaggerVersion = "1.0";

    private static List<Class> controllerClasses = new ArrayList<Class>();
    private static Map<String, Class> resourceMap = new HashMap<String, Class>();


    public static String getRootHelpJson() {
        String resources = null;
        try {
            resources = JsonUtil.getJsonMapper().writeValueAsString(getRootResources("json"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return resources;
    }

    private static Object getRootResources(String format) {
        Documentation allApiDoc = new Documentation();

        for (Class clazz : getControllerClasses()) {
            Api apiAnnotation = (Api)clazz.getAnnotation(Api.class);
            if (null != apiAnnotation) {
                DocumentationEndPoint api = new DocumentationEndPoint(apiAnnotation.value() + ".{format}", apiAnnotation.description());
                if (!isApiAdded(allApiDoc, api)) {
                    //if (null == apiFilter || apiFilter.authorizeResource(api.path)) {
                        allApiDoc.addApi(api);
                    //}
                }
            }
        }

        allApiDoc.setSwaggerVersion(swaggerVersion);
//    allApiDoc.basePath = basePath
//    allApiDoc.apiVersion = apiVersion

        return allApiDoc;
    }
//    for (clazz <- getControllerClasses) {
//      val apiAnnotation = clazz.getAnnotation(classOf[Api])
//      if (null != apiAnnotation) {
//        val api = new DocumentationEndPoint(apiAnnotation.value + ".{format}", apiAnnotation.description())
//        if (!isApiAdded(allApiDoc, api)) {
//          if (null == apiFilter || apiFilter.authorizeResource(api.path)) {
//            allApiDoc.addApi(api)
//          }
//        }
//      }
//    }
//
//    allApiDoc.swaggerVersion = swaggerVersion
//    allApiDoc.basePath = basePath
//    allApiDoc.apiVersion = apiVersion
//
//    allApiDoc
//  }


//  // Add the Play classloader to Swagger
//  SwaggerContext.registerClassLoader(current.classloader)
//
//  // Get a list of all controller classes
//  private val controllerClasses = ListBuffer.empty[Class[_]]
//
//  // Initialize the map from Api annotation value to play controller class
//  private val resourceMap = scala.collection.mutable.Map.empty[String, Class[_]]
//
//  // Read various configurable properties. These can be specified in application.conf
//  private val apiVersion = current.configuration.getString("api.version") match { case None => "beta" case Some(value) => value }
//  private val basePath = current.configuration.getString("swagger.api.basepath") match { case None => "http://localhost" case Some(value) => value }
//  private val swaggerVersion = SwaggerSpec.version
//  private val apiFilterClassName = current.configuration.getString("swagger.security.filter") match { case None => null case Some(value) => value }
//
    private static Boolean filterOutTopLevelApi = true;
//
//  def getResourceNames: swagger_java.util.List[String] = getResourceMap.keys.toList
//
//  private val jaxbContext = JAXBContext.newInstance(classOf[Documentation]);
//
//  /**
//   * Get a list of all top level resources
//   */
//  private def getRootResources(format: String)(implicit requestHeader: RequestHeader) = {
//    var apiFilter: ApiAuthorizationFilter = null
//    if (null != apiFilterClassName) {
//      try {
//        apiFilter = SwaggerContext.loadClass(apiFilterClassName).newInstance.asInstanceOf[ApiAuthorizationFilter]
//      } catch {
//        case e: ClassNotFoundException => Logger.error("Unable to resolve apiFilter class " + apiFilterClassName);
//        case e: ClassCastException => Logger.error("Unable to cast to apiFilter class " + apiFilterClassName);
//      }
//    }
//
//    val allApiDoc = new Documentation
//    for (clazz <- getControllerClasses) {
//      val apiAnnotation = clazz.getAnnotation(classOf[Api])
//      if (null != apiAnnotation) {
//        val api = new DocumentationEndPoint(apiAnnotation.value + ".{format}", apiAnnotation.description())
//        if (!isApiAdded(allApiDoc, api)) {
//          if (null == apiFilter || apiFilter.authorizeResource(api.path)) {
//            allApiDoc.addApi(api)
//          }
//        }
//      }
//    }
//
//    allApiDoc.swaggerVersion = swaggerVersion
//    allApiDoc.basePath = basePath
//    allApiDoc.apiVersion = apiVersion
//
//    allApiDoc
//  }
//
//  /**
//   * Get detailed API/models for a given resource
//   */
//  private def getResource(resourceName: String)(implicit requestHeader: RequestHeader) = {
//    println("getting resource " + resourceName)
//    getResourceMap.get(resourceName) match {
//      case Some(clazz) => {
//        val currentApiEndPoint = clazz.getAnnotation(classOf[Api])
//        val currentApiPath = if (currentApiEndPoint != null && filterOutTopLevelApi) currentApiEndPoint.value else null
//
//        Logger.debug("Loading resource " + resourceName + " from " + clazz + " @ " + currentApiPath)
//
//        val docs = new HelpApi(apiFilterClassName).filterDocs(PlayApiReader.read(clazz, apiVersion, swaggerVersion, basePath, currentApiPath), currentApiPath)
//        Option(docs)
//      }
//      case None => None
//    }
//  }

    /**
    * Get detailed API/models for a given resource
    */
    private static Documentation getResource(String resourceName) {
        Documentation docs = null;
        Class clazz = getResourceMap().get(resourceName);
        if (clazz != null) {
            Api currentApiEndPoint = (Api)clazz.getAnnotation(Api.class);
            String currentApiPath = (currentApiEndPoint != null && filterOutTopLevelApi) ? currentApiEndPoint.value() : null;
            docs = PlayApiReader.read(clazz, "alpha", swaggerVersion, "/base/", currentApiPath);
        }
        return docs;
    }

//
//  def getPathHelpJson(apiPath: String)(implicit requestHeader: RequestHeader): String = {
//    getResource(apiPath) match {
//      case Some(docs) => JsonUtil.getJsonMapper.writeValueAsString(docs)
//      case None => null
//    }
//  }

    public static String getPathHelpJson(String apiPath) {
        Documentation docs = getResource(apiPath);
        if (docs != null) {
            try {
                return JsonUtil.getJsonMapper().writeValueAsString(docs);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return null;
    }

//
//  def getPathHelpXml(apiPath: String)(implicit requestHeader: RequestHeader): String = {
//    getResource(apiPath) match {
//      case Some(docs) => {
//        val stringWriter = new StringWriter()
//        jaxbContext.createMarshaller().marshal(docs, stringWriter);
//        stringWriter.toString
//      }
//      case None => null
//    }
//  }
//
//  def getRootHelpJson()(implicit requestHeader: RequestHeader): String = {
//    JsonUtil.getJsonMapper.writeValueAsString(getRootResources("json"))
//  }
//
//  def getRootHelpXml()(implicit requestHeader: RequestHeader): String = {
//    val stringWriter = new StringWriter()
//    jaxbContext.createMarshaller().marshal(getRootResources("xml"), stringWriter);
//    stringWriter.toString
//  }
//

    /**
     * Get a list of all controller classes in Play
     */

    private static List<Class> getControllerClasses() {
        if (controllerClasses.size() == 0) {
            Set<String> swaggerControllers = Play.application().getTypesAnnotatedWith("controllers", Api.class);

            if (swaggerControllers.size() > 0) {
                for (String clazzName : swaggerControllers) {
                    Class clazz = null;
                    try {
                        clazz = Play.application().classloader().loadClass(clazzName);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    controllerClasses.add(clazz);
                    Api apiAnnotation = (Api)clazz.getAnnotation(Api.class);
                    if (apiAnnotation != null && play.mvc.Controller.class.isAssignableFrom(clazz)) {
                        Logger.debug("Found Resource " + apiAnnotation.value() + " @ " + clazzName);
                        resourceMap.put(apiAnnotation.value(), clazz);
                    }

                }

            }

        }

        return controllerClasses;
    }

    private static Map<String, Class> getResourceMap() {
        // check if resources and controller info has already been loaded
        if (controllerClasses.size() == 0) {
          getControllerClasses();
        }

        return resourceMap;
    }

    private static Boolean isApiAdded(Documentation allApiDoc, DocumentationEndPoint endpoint) {
        Boolean isAdded = false;
        if (allApiDoc.getApis() != null) {
            for (DocumentationEndPoint addedApi : allApiDoc.getApis()) {
                if (endpoint.path().equals(addedApi.path())) isAdded = true;
            }
        }
        return isAdded;
    }
}