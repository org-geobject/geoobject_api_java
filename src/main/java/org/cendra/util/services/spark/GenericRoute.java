package org.cendra.util.services.spark;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.cendra.util.service.swagger.ParameterJson;
import org.cendra.util.service.swagger.VerbJson;

import spark.Request;
import spark.Response;
import spark.Route;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericRoute implements Route {

	private IContext cx;
	private String pathJsonSwagger;
	private String path;
	private VerbJson verbJson;

	public GenericRoute(IContext cx, String pathJsonSwagger, String path,
			VerbJson verbJson) {
		super();
		this.cx = cx;
		this.pathJsonSwagger = pathJsonSwagger;
		this.path = path;
		this.verbJson = verbJson;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object handle(Request request, Response response) throws Exception {

		Map<String, Object> paramValues = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Map<String, Class> paramClass = new HashMap<String, Class>();

		if (verbJson.getParameters() != null) {
			for (ParameterJson parameterJson : verbJson.getParameters()) {
				if (parameterJson.getIn().equalsIgnoreCase("query")) {
					paramValues.put(parameterJson.getName(),
							request.queryParams(parameterJson.getName()));

					paramClass.put(parameterJson.getName(),
							Class.forName(String.class.getCanonicalName()));

				} else if (parameterJson.getIn().equalsIgnoreCase("body")) {
					Object body = null;
					String bodyJson = request.body();
					@SuppressWarnings("rawtypes")
					Class clazz = Class.forName(parameterJson.getSchema()
							.get(0).get$ref()
							.replaceFirst("#/definitions/", ""));
					if (bodyJson != null) {
						ObjectMapper mapper = new ObjectMapper();
						body = mapper.readValue(bodyJson, clazz);
					}

					paramValues.put(parameterJson.getName(), body);
					paramClass.put(parameterJson.getName(), clazz);
				}
			}
		}

		String s = execute(verbJson.getVerb().toUpperCase(),
				verbJson.getOperationId(), paramValues, paramClass);

		return s;
	}

	private String execute(String verb, String operationId,
			Map<String, Object> paramsValues,
			@SuppressWarnings("rawtypes") Map<String, Class> paramClass)
			throws Exception {

		String beanName = null;
		String methodName = null;
		String[] paranNamesArray = null;
		Object[] paranValuesArray = null;
		@SuppressWarnings("rawtypes")
		Class[] parameterTypes = null;

		try {

			beanName = operationId.split("[.]")[0];
			methodName = operationId.split("[.]")[1];
			String paramNames = methodName.split("[(]")[1];
			paramNames = paramNames.split("[)]")[0];
			paranNamesArray = paramNames.split(",");
			paranValuesArray = new Object[paranNamesArray.length];
			parameterTypes = new Class[paranNamesArray.length];
			methodName = methodName.split("[(]")[0];

		} catch (Exception e) {
			throw new Exception("Error in path = \"[" + verb + "] " + path
					+ "\", operationId = \"" + operationId
					+ ".\" Please check your JSON swagger file ["
					+ this.pathJsonSwagger + "].");
		}

		for (int i = 0; i < paranNamesArray.length; i++) {

			paranNamesArray[i] = paranNamesArray[i].trim();

			if (paramsValues.containsKey(paranNamesArray[i])) {

				paranValuesArray[i] = paramsValues.get(paranNamesArray[i]);
				parameterTypes[i] = paramClass.get(paranNamesArray[i]);

			} else {
				throw new Exception("Param name \"" + paranNamesArray[i]
						+ "\" not found. Error in path = \"[" + verb + "] "
						+ path + "\", operationId = \"" + operationId
						+ ".\" Please check your JSON swagger file ["
						+ this.pathJsonSwagger + "].");
			}
		}

		try {
			Object bean = cx.getBean(beanName);

			Method method = bean.getClass().getMethod(methodName,
					parameterTypes);
			Object objReturn = method.invoke(bean, paranValuesArray);

			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(objReturn);
			return json;

		} catch (Exception e) {
			throw new Exception("Error in path = \"[" + verb + "] " + path
					+ "\", operationId = \"" + operationId
					+ ".\" Please check your JSON swagger file ["
					+ this.pathJsonSwagger + "].");
		}

	}
}
