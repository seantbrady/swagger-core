package play.modules.swagger_java;

import com.wordnik.swagger.core.*;
import scala.collection.immutable.List;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Reads swaggers annotations, play route information and uses reflection to build API information on a given class
 */

public class PlayApiSpecParser extends ApiSpecParserTrait$class implements ApiSpecParserTrait {

    Class hostClass;
    String apiVersion;
    String swaggerVersion;
    String basePath;
    String resourcePath;
    String apiPath;
    Api apiEndpoint;
    Documentation documentation;

    private PlayApiSpecParser() {
        super();
    }

    public PlayApiSpecParser(Class hostClass, String apiVersion, String swaggerVersion, String basePath, String apiPath) {
        super();
        this.hostClass = hostClass;
        this.apiVersion = apiVersion;
        this.swaggerVersion = swaggerVersion;
        this.basePath = basePath;
        this.apiPath = apiPath;
    }

    @Override
    public void $init$() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String readString(String s, String existingValue, String ignoreValue) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> toObjectList(String csvString, String paramType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DocumentationAllowableValues convertToAllowableValues(String csvString, String paramType) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Class<?> hostClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Documentation documentation() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Api apiEndpoint() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String apiVersion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String swaggerVersion() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String basePath() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String resourcePath() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getPath(Method method) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean processParamAnnotations(DocumentationParameter docParam, Annotation[] paramAnnotations, Method method) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String parseHttpMethod(Method method, ApiOperation apiOperation) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Documentation parse() {
        if (apiEndpoint != null)
            for (Method method : hostClass.getMethods()) {
                //parseMethod(method);
                ApiSpecParserTrait$class.parseMethod(this, method);
            }
        documentation.setApiVersion(apiVersion);
        documentation.setSwaggerVersion(swaggerVersion);
        documentation.setBasePath(basePath);
        documentation.setResourcePath(resourcePath);
        return documentation;
    }

    @Override
    public void parseApiParam(DocumentationParameter docParam, ApiParam apiParam, Method method) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object parseMethod(Method method) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DocumentationOperation processOperation(Method method, DocumentationOperation o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

//    private void parseMethod(Method method) {
//        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
//        ApiErrors apiErrors = method.getAnnotation(ApiErrors.class);
//        Deprecated isDeprecated = method.getAnnotation(Deprecated.class);
//
//        if (apiOperation != null && method.getName() != "getHelp()") {
//            // Read the Operation
//            DocumentationOperation docOperation = new DocumentationOperation();
//
//            // check if its deprecated
//            //if (isDeprecated != null) docOperation.setDeprecated(true);
//
//            if (apiOperation != null) {
//                docOperation.setHttpMethod(parseHttpMethod(method, apiOperation));
//                docOperation.setSummary(readString(apiOperation.value));
//                docOperation.setNotes(readString(apiOperation.notes));
//                docOperation.setTags(toObjectList(apiOperation.tags));
//                docOperation.setNickname(method.getName);
//                String apiResponseValue = readString(apiOperation.responseClass());
//                Boolean isResponseMultiValue = apiOperation.multiValueResponse();
//
//                docOperation.setResponseTypeInternal(apiResponseValue);
//                try {
//                    Class cls = SwaggerContext.loadClass(apiResponseValue);
//                    String annotatedName = ApiPropertiesReader.readName(cls);
//                    if (isResponseMultiValue) {
//                        docOperation.setResponseClass("List<" + annotatedName + ">");
//                    } else {
//                        docOperation.setResponseClass(annotatedName);
//                    }
//                } catch (Exception e) {
//                    //case e: ClassNotFoundException => docOperation.responseClass = apiResponseValue
//                }
//            }
//
//            // Read method annotations for implicit api params which are not eclared as actual argments to the method
//            // Essentially ApiParamImplicit annotations on method
//            Annotation[] methodAnnotations = method.getAnnotations();
//            for (Annotation ma : methodAnnotations) {
//                if (ma instanceof ApiParamsImplicit) {
//                    // TODO
//                }
//            }
//
//            // Read the params and add to Operation
//            Annotation[][] paramAnnotationDoubleArray = method.getParameterAnnotations();
//            Class<?>[] paramTypes = method.getParameterTypes();
//            Type[] genericParamTypes = method.getGenericParameterTypes();
//            int counter = 0;
//            boolean ignoreParam = false;
//
//
//
//            for (Annotation[] paramAnnotations : paramAnnotationDoubleArray) {
//                DocumentationParameter docParam = new DocumentationParameter();
//                docParam.setRequired(true);
//
//                // determine value type
//                try {
//                    Class paramTypeClass = paramTypes[counter];
//                    String paramTypeName = ApiPropertiesReader.getDataType(genericParamTypes[counter], paramTypeClass);
//                    docParam.setDataType(paramTypeName);
//                    if (!paramTypeClass.isPrimitive() && !paramTypeClass.getName().contains("java.lang")) {
//                        docParam.setValueTypeInternal(ApiPropertiesReader.getGenericTypeParam(genericParamTypes[counter], paramTypeClass));
//                    }
//                } catch (Exception e) {
////                    case e: Exception => LOGGER.error("Unable to determine datatype for param " + counter + " in method " + method, e)
//                }
//
//                ignoreParam = processParamAnnotations(docParam, paramAnnotations, method);
//
//                if (paramAnnotations.length == 0) {
//                    ignoreParam = true;
//                }
//
//                counter = counter + 1;
//
//                // Set the default paramType, if nothing is assigned
//                docParam.setParamType(new BaseApiParser().readString(TYPE_BODY, docParam.paramType(), null));
//                if (!ignoreParam) docOperation.addParameter(docParam);
//
//            }
//
//            // Get Endpoint
//            val docEndpoint = ApiSpecParserTrait$class.getEndPoint(documentation, getPath(method));
//
//            // Add Operation to Endpoint
//            docEndpoint.addOperation(processOperation(method, docOperation))
//            LOGGER.debug("added operation " + docOperation + " from method " + method.getName)
//
//            // Read the Errors and add to Response
//            if (apiErrors != null) {
//                for (apiError <- apiErrors.value) {
//                    val docError = new DocumentationError
//                    docError.code = apiError.code
//                    docError.reason = readString(apiError.reason)
//                    docOperation.addErrorResponse(docError)
//                }
//            }
//        } else LOGGER.debug("skipping method " + method.getName)
//
//        }
//
//}
//
//        public Boolean processParamAnnotations(DocumentationParameter docParam, Annotation[] paramAnnotations, Method method) {
////    var ignoreParam = false
////    for (pa <- paramAnnotations) {
////      pa match {
////        case apiParam: ApiParam => parseApiParam(docParam, apiParam, method)
////        case wsParam: QueryParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.paramType = readString(TYPE_QUERY, docParam.paramType)
////        }
////        case wsParam: PathParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.required = true
////          docParam.paramType = readString(TYPE_PATH, docParam.paramType)
////        }
////        case wsParam: MatrixParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.paramType = readString(TYPE_MATRIX, docParam.paramType)
////        }
////        case wsParam: HeaderParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.paramType = readString(TYPE_HEADER, docParam.paramType)
////        }
////        case wsParam: FormParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.paramType = readString(TYPE_FORM, docParam.paramType)
////        }
////        case wsParam: CookieParam => {
////          docParam.name = readString(wsParam.value, docParam.name)
////          docParam.paramType = readString(TYPE_COOKIE, docParam.paramType)
////        }
////        case wsParam: Context => ignoreParam = true
////        case _ => Unit
////      }
////    }
////    ignoreParam
//            return true;
//  }

    @Override
    public String TRAIT() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String NEGATIVE_INFINITY_STRING() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String POSITIVE_INFINITY_STRING() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}