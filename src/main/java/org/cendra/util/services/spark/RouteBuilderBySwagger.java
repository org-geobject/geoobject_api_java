package org.cendra.util.services.spark;

import static spark.Spark.before;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.patch;
import static spark.Spark.post;
import static spark.Spark.put;
import io.swagger.models.Swagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.cendra.util.service.swagger.PathJson;
import org.cendra.util.service.swagger.SwaggerJson;
import org.cendra.util.service.swagger.SwaggerParserJson;
import org.cendra.util.service.swagger.VerbJson;

import spark.Route;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RouteBuilderBySwagger {

	private static RouteBuilderBySwagger routeBuilderBySwagger;
	private IContext cx;
	private String pathJsonSwagger = "";
	private String jsonSwaggerIn = "";
	private String jsonSwaggerOut = "";
	private Swagger swagger;
	private SwaggerJson swaggerJson;

	private RouteBuilderBySwagger(IContext cx, String pathJsonSwagger)
			throws Exception {
		this.cx = cx;
		setupRoutes(pathJsonSwagger);
	}

	public static RouteBuilderBySwagger getInstance(IContext cx,
			String pathJsonSwagger) throws Exception {
		if (routeBuilderBySwagger == null) {
			routeBuilderBySwagger = new RouteBuilderBySwagger(cx,
					pathJsonSwagger);
		}
		return routeBuilderBySwagger;
	}

	public String getPathJsonSwagger() {
		return pathJsonSwagger;
	}

	public String getJsonSwaggerIn() {
		return jsonSwaggerIn;
	}

	public String getJsonSwaggerOut() throws JsonProcessingException {
		if (jsonSwaggerOut == null || jsonSwaggerOut.isEmpty()) {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			jsonSwaggerOut = objectMapper.writeValueAsString(swagger);
		}

		return jsonSwaggerOut;
	}

	public Swagger getSwagger() {
		return swagger;
	}

	private void setupRoutes(String pathJsonSwagger) throws Exception {

		// Quite unsafe!
		before(new CorsFilter());
		new OptionsController();

		this.pathJsonSwagger = pathJsonSwagger;

		this.jsonSwaggerIn = readTxt(pathJsonSwagger);
		swaggerJson = SwaggerParserJson.parse2(jsonSwaggerIn);
		swagger = SwaggerParserJson.parse(jsonSwaggerIn);

		get("/swagger_input", (req, res) -> {
			return this.jsonSwaggerIn;
		});

		get("/swagger", (req, res) -> {
			return getJsonSwaggerOut();
		});

		setupRoutes(swaggerJson);

		System.err.println("pathJsonSwagger = " + this.pathJsonSwagger + "\n");
		System.err.println("jsonSwaggerIn = \n" + this.jsonSwaggerIn + "\n");
		// System.err.println("jsonSwaggerOut = \n" + getJsonSwaggerOut() +
		// "\n");

	}

	private void setupRoutes(SwaggerJson swagger) {

		for (PathJson path : swagger.getPaths()) {

			String friendlyRoute = path.getPath().replaceAll("\\{(.*?)\\}",
					":$1");

			for (VerbJson verb : path.getVerbs()) {

				if (verb.getVerb().equalsIgnoreCase("GET")) {
					Route route = new GenericRoute(cx, pathJsonSwagger,
							friendlyRoute, verb);
					get(friendlyRoute, route);
					System.err.println("[GET] " + friendlyRoute + " "
							+ verb.getOperationId() + "}");
				}

				if (verb.getVerb().equalsIgnoreCase("POST")) {
					Route route = new GenericRoute(cx, pathJsonSwagger,
							friendlyRoute, verb);
					post(friendlyRoute, route);
					System.out.println("[POST] " + friendlyRoute + " "
							+ verb.getOperationId() + "}");
				}

				if (verb.getVerb().equalsIgnoreCase("PUT")) {
					put(friendlyRoute, (request, response) -> "{UP!! [PUT] "
							+ friendlyRoute + " " + verb.getOperationId() + "}");
					System.out.println("[PUT] " + friendlyRoute + " "
							+ verb.getOperationId() + "}");
					break;
				}

				if (verb.getVerb().equalsIgnoreCase("PATCH")) {
					patch(friendlyRoute,
							(request, response) -> "{UP!! [PATCH] "
									+ friendlyRoute + " "
									+ verb.getOperationId() + "}");
					System.out.println("[PATCH] " + friendlyRoute + " "
							+ verb.getOperationId() + "}");
					break;
				}

				if (verb.getVerb().equalsIgnoreCase("DELETE")) {
					delete(friendlyRoute,
							(request, response) -> "{UP!! [DELETE] "
									+ friendlyRoute + " "
									+ verb.getOperationId() + "}");
					System.out.println("[DELETE] " + friendlyRoute + " "
							+ verb.getOperationId() + "}");
					break;
				}
			}
		}

	}

	// private void setupRoutes(Swagger swagger) {
	//
	// Map<String, io.swagger.models.Path> map = swagger.getPaths();
	// Iterator<Map.Entry<String, io.swagger.models.Path>> entries = map
	// .entrySet().iterator();
	//
	// while (entries.hasNext()) {
	//
	// Map.Entry<String, io.swagger.models.Path> entry = entries.next();
	//
	// io.swagger.models.Path path = entry.getValue();
	//
	// String friendlyRoute = entry.getKey().replaceAll("\\{(.*?)\\}",
	// ":$1");
	//
	// if (path.getGet() != null) {
	// Route route = new GenericRoute(cx, pathJsonSwagger,
	// friendlyRoute, path.getGet());
	// get(friendlyRoute, route);
	// System.err.println("[GET] " + friendlyRoute + " "
	// + path.getGet().getOperationId() + "}");
	// }
	//
	// if (path.getPost() != null) {
	// Route route = new GenericRoute(cx, pathJsonSwagger,
	// friendlyRoute, path.getPost());
	// post(friendlyRoute, route);
	// System.out.println("[POST] " + friendlyRoute + " "
	// + path.getPost().getOperationId() + "}");
	// }
	//
	// if (path.getPut() != null) {
	// put(friendlyRoute, (request, response) -> "{UP!! [PUT] "
	// + friendlyRoute + " " + path.getPut().getOperationId()
	// + "}");
	// System.out.println("[PUT] " + friendlyRoute + " "
	// + path.getPut().getOperationId() + "}");
	// break;
	// }
	//
	// if (path.getPatch() != null) {
	// patch(friendlyRoute, (request, response) -> "{UP!! [PATCH] "
	// + friendlyRoute + " "
	// + path.getPatch().getOperationId() + "}");
	// System.out.println("[PATCH] " + friendlyRoute + " "
	// + path.getPatch().getOperationId() + "}");
	// break;
	// }
	//
	// if (path.getDelete() != null) {
	// delete(friendlyRoute, (request, response) -> "{UP!! [DELETE] "
	// + friendlyRoute + " "
	// + path.getDelete().getOperationId() + "}");
	// System.out.println("[DELETE] " + friendlyRoute + " "
	// + path.getDelete().getOperationId() + "}");
	// break;
	// }
	// }
	//
	// }

	private static String readTxt(String path) throws Exception {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;

		String txt = "";

		try {
			file = new File(path);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null)
				txt += line + "\n";
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}

		txt = txt.trim();

		return txt;
	}

}
