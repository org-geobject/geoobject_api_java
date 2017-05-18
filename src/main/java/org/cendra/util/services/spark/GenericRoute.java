package org.cendra.util.services.spark;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.cendra.util.service.swagger.ParameterJson;
import org.cendra.util.service.swagger.VerbJson;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import spark.Response;
import spark.Route;

public class GenericRoute implements Route {

	private IContext cx;
	private String pathJsonSwagger;
	private String path;
	private VerbJson verbJson;

	public GenericRoute(IContext cx, String pathJsonSwagger, String path, VerbJson verbJson) {
		super();
		this.cx = cx;
		this.pathJsonSwagger = pathJsonSwagger;
		this.path = path;
		this.verbJson = verbJson;
	}

	@Override
	public Object handle(Request request, Response response) throws Exception {

		Map<String, Object> paramValues = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Map<String, Class> paramClass = new HashMap<String, Class>();

		if (verbJson.getParameters() != null) {
			for (ParameterJson parameterJson : verbJson.getParameters()) {

				if (parameterJson.getName() == null || parameterJson.getName().isEmpty()) {
					throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
							+ "\", operationId = \"" + verbJson.getOperationId() + "\". The name parameter is null."
							+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
				}

				if (paramValues.containsKey(parameterJson.getName())) {
					throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
							+ "\", operationId = \"" + verbJson.getOperationId() + "\". The \""
							+ parameterJson.getName() + "\" parameter is repeated."
							+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
				}

				if (parameterJson.getIn().equalsIgnoreCase("query")) {

					if (parameterJson.getType() == null || parameterJson.getType().isEmpty()) {
						throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
								+ "\", operationId = \"" + verbJson.getOperationId() + "\". The \""
								+ parameterJson.getName() + "\" parameter is not well defined."
								+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
					}

					if (parameterJson.getType().equalsIgnoreCase("string")) {
						paramClass.put(parameterJson.getName(), Class.forName(String.class.getCanonicalName()));
						paramValues.put(parameterJson.getName(), request.queryParams(parameterJson.getName()));
					} else if (parameterJson.getType().equalsIgnoreCase("integer")) {
						paramClass.put(parameterJson.getName(), Class.forName(Integer.class.getCanonicalName()));
						String value = request.queryParams(parameterJson.getName());
						Integer intValue = null;
						if (value != null) {
							intValue = Integer.valueOf(value);
						}
						paramValues.put(parameterJson.getName(), intValue);
					} else {
						paramClass.put(parameterJson.getName(), Class.forName(String.class.getCanonicalName()));
						paramValues.put(parameterJson.getName(), request.queryParams(parameterJson.getName()));
					}

				} else if (parameterJson.getIn().equalsIgnoreCase("path")) {

					if (parameterJson.getType() == null || parameterJson.getType().isEmpty()) {
						throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
								+ "\", operationId = \"" + verbJson.getOperationId() + "\". The \""
								+ parameterJson.getName() + "\" parameter is not well defined."
								+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
					}

					if (parameterJson.getType().equalsIgnoreCase("string")) {
						paramClass.put(parameterJson.getName(), Class.forName(String.class.getCanonicalName()));
						paramValues.put(parameterJson.getName(), request.params(parameterJson.getName()));
					} else if (parameterJson.getType().equalsIgnoreCase("integer")) {
						paramClass.put(parameterJson.getName(), Class.forName(Integer.class.getCanonicalName()));
						String value = request.params(parameterJson.getName());
						Integer intValue = null;
						if (value != null) {
							intValue = Integer.valueOf(value);
						}
						paramValues.put(parameterJson.getName(), intValue);
					} else {
						paramClass.put(parameterJson.getName(), Class.forName(String.class.getCanonicalName()));
						paramValues.put(parameterJson.getName(), request.params(parameterJson.getName()));
					}

				} else if (parameterJson.getIn().equalsIgnoreCase("body")) {

					buildParamBody(request, paramValues, paramClass, parameterJson);

				}
			}
		}

		String s = execute(verbJson.getVerb().toUpperCase(), verbJson.getOperationId(), paramValues, paramClass);

		response.type("application/json");

		return s;
	}

	@SuppressWarnings("unchecked")
	private void buildParamBody(Request request, Map<String, Object> paramValues,
			@SuppressWarnings("rawtypes") Map<String, Class> paramClass, ParameterJson parameterJson) throws Exception {

		Object body = null;
		String bodyJson = request.body();

		if (parameterJson.getSchema() == null) {
			throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
					+ "\", operationId = \"" + verbJson.getOperationId() + "\". The schema of the \""
					+ parameterJson.getName() + "\" parameter is not defined."
					+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		if (parameterJson.getSchema().get(0) == null) {
			throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
					+ "\", operationId = \"" + verbJson.getOperationId() + "\". The schema of the \""
					+ parameterJson.getName() + "\" parameter is not defined."
					+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		if (parameterJson.getSchema().get(0).get$ref() == null
				|| parameterJson.getSchema().get(0).get$ref().isEmpty()) {
			throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
					+ "\", operationId = \"" + verbJson.getOperationId() + "\". The schema of the \""
					+ parameterJson.getName() + "\" parameter is not defined."
					+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		@SuppressWarnings("rawtypes")
		Class clazz = null;
		try {

			clazz = Class.forName(parameterJson.getSchema().get(0).get$ref().replaceFirst("#/definitions/", ""));

		} catch (Exception e) {
			throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
					+ "\", operationId = \"" + verbJson.getOperationId() + "\". The schema of the \""
					+ parameterJson.getName() + "\" parameter is not well defined."
					+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		if (bodyJson != null) {

			try {

				ObjectMapper mapper = new ObjectMapper();
				body = mapper.readValue(bodyJson, clazz);

			} catch (Exception e) {
				throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
						+ "\", operationId = \"" + verbJson.getOperationId() + "\". The schema of the \""
						+ parameterJson.getName() + "\" parameter is not well defined."
						+ " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
			}

		}

		paramValues.put(parameterJson.getName(), body);
		paramClass.put(parameterJson.getName(), clazz);
	}

	private String execute(String verb, String operationId, Map<String, Object> paramsValues,
			@SuppressWarnings("rawtypes") Map<String, Class> paramClass) throws Exception {

		String beanName = null;
		String methodName = null;
		String[] paranNamesArray = null;
		Object[] paranValuesArray = null;
		@SuppressWarnings("rawtypes")
		Class[] parameterTypes = null;

		try {

			beanName = operationId.split("[.]")[0].trim();
			methodName = operationId.split("[.]")[1].trim();
			String paramNames = methodName.split("[(]")[1].trim();
			if (paramNames.split("[)]").length > 0) {
				paramNames = paramNames.split("[)]")[0].trim();
			} else {
				paramNames = "";
			}
			if (paramNames.isEmpty()) {
				paranNamesArray = new String[0];
			} else {
				paranNamesArray = paramNames.split(",");

			}
			paranValuesArray = new Object[paranNamesArray.length];
			parameterTypes = new Class[paranNamesArray.length];

			methodName = methodName.split("[(]")[0].trim();

		} catch (Exception e) {
			throw new Exception("Error in path = \"[" + verb + "] " + path + "\", operationId = \"" + operationId
					+ ".\" Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		for (int i = 0; i < paranNamesArray.length; i++) {

			paranNamesArray[i] = paranNamesArray[i].trim();

			if (paramsValues.containsKey(paranNamesArray[i])) {

				paranValuesArray[i] = paramsValues.get(paranNamesArray[i]);
				parameterTypes[i] = paramClass.get(paranNamesArray[i]);

			} else {
				throw new Exception("Param name \"" + paranNamesArray[i] + "\" not found. Error in path = \"[" + verb
						+ "] " + path + "\", operationId = \"" + operationId
						+ ".\" Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
			}
		}

		Object bean = cx.getBean(beanName);

		if (bean == null) {
			// que hacemos ? no implementado ?
			throw new Exception("Error in path = \"[" + verbJson.getVerb().toUpperCase() + "] " + path
					+ "\", operationId = \"" + verbJson.getOperationId() + "\". The bean of the \"" + beanName
					+ "\" not found." + " Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

		try {
			Method method = bean.getClass().getMethod(methodName, parameterTypes);
			Object objReturn = method.invoke(bean, paranValuesArray);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(objReturn);
			return json;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error in path = \"[" + verb + "] " + path + "\", operationId = \"" + operationId
					+ ".\" Please check your JSON swagger file [" + this.pathJsonSwagger + "].");
		}

	}
}
