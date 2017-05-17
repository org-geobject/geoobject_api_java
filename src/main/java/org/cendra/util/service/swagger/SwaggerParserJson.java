package org.cendra.util.service.swagger;

import io.swagger.models.Contact;
import io.swagger.models.Info;
import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.HeaderParameter;
import io.swagger.models.parameters.Parameter;
import io.swagger.models.parameters.PathParameter;
import io.swagger.models.parameters.QueryParameter;
import io.swagger.models.properties.AbstractProperty;
import io.swagger.models.properties.ArrayProperty;
import io.swagger.models.properties.DateTimeProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SwaggerParserJson {

	public static SwaggerJson parse2(String jsonSwagger) throws Exception {

		String jsonRoot = null;
		ObjectMapper mapper = null;
		JsonNode jsonNodeRoot = null;
		SwaggerJson swaggerJson = null;

		jsonRoot = jsonSwagger;

		mapper = new ObjectMapper();
		jsonNodeRoot = mapper.readTree(jsonRoot);

		Iterator<Entry<String, JsonNode>> fields = jsonNodeRoot.fields();

		String json = "{\n";

		while (fields.hasNext()) {

			Entry<String, JsonNode> field = fields.next();
			String fieldName = field.getKey();
			if (fieldName.equals("paths") == false
					&& fieldName.equals("definitions") == false) {
				json += "\n\t\"" + fieldName + "\":" + field.getValue() + ",";
			}
		}

		json = json.trim();
		json = json.substring(0, json.length() - 1);

		json += "\n}";

		// objectMapper.setSerializationInclusion(Include.NON_EMPTY);
		swaggerJson = mapper.readValue(json, SwaggerJson.class);

		JsonNode jsonNodeDefinitions = jsonNodeRoot.get("definitions");

		fields = jsonNodeDefinitions.fields();

		List<ModelJson> definitions = new ArrayList<ModelJson>();

		while (fields.hasNext()) {

			Entry<String, JsonNode> fieldModel = fields.next();
			String fieldNameModel = fieldModel.getKey();
			JsonNode jsonNodeModel = fieldModel.getValue();

			ModelJson model = new ModelJson();
			model.setName(fieldNameModel);
			model.setType(jsonNodeModel.get("type").textValue());
			definitions.add(model);

			JsonNode jsonNodeProperties = jsonNodeModel.get("properties");

			Iterator<Entry<String, JsonNode>> fieldsProperties = jsonNodeProperties
					.fields();

			List<PropertyJson> properties = new ArrayList<PropertyJson>();

			while (fieldsProperties.hasNext()) {

				Entry<String, JsonNode> fieldProprty = fieldsProperties.next();
				String fieldNameProprty = fieldProprty.getKey();
				JsonNode jsonNodeProprty = fieldProprty.getValue();

				PropertyJson propertyJson = new PropertyJson();
				propertyJson.setName(fieldNameProprty);

				if (jsonNodeProprty.get("type") != null) {
					propertyJson.setType(jsonNodeProprty.get("type")
							.textValue());
				}
				if (jsonNodeProprty.get("format") != null) {
					propertyJson.setFormat(jsonNodeProprty.get("format")
							.textValue());
				}

				if (jsonNodeProprty.get("enum") != null) {

					List<String> enumList = new ArrayList<String>();

					Iterator<JsonNode> it = jsonNodeProprty.get("enum")
							.elements();
					while (it.hasNext()) {
						JsonNode jsonNodeEnum = it.next();
						enumList.add(jsonNodeEnum.toString().replaceAll("\"",
								""));
					}

					propertyJson.setEnum(enumList);
				}

				if (jsonNodeProprty.get("items") != null) {

					List<RefJson> items = new ArrayList<RefJson>();

					Iterator<JsonNode> it = jsonNodeProprty.get("items")
							.elements();

					while (it.hasNext()) {

						JsonNode jsonNodeItem = it.next();

						RefJson refJson = new RefJson();
						refJson.set$ref(jsonNodeItem.toString().replaceAll(
								"\"", ""));

						items.add(refJson);
					}

					propertyJson.setItems(items);
				}

				properties.add(propertyJson);

			}

			model.setProperties(properties);
		}

		swaggerJson.setDefinitions(definitions);

		JsonNode jsonNodePaths = jsonNodeRoot.get("paths");

		fields = jsonNodePaths.fields();

		List<PathJson> paths = new ArrayList<PathJson>();

		while (fields.hasNext()) {

			Entry<String, JsonNode> fieldPath = fields.next();
			String fieldNamePath = fieldPath.getKey();
			JsonNode jsonNodePath = fieldPath.getValue();

			PathJson pathJson = new PathJson();
			pathJson.setPath(fieldNamePath);
			paths.add(pathJson);

			Iterator<Entry<String, JsonNode>> fieldsVerbs = jsonNodePath
					.fields();

			List<VerbJson> verbs = new ArrayList<VerbJson>();

			while (fieldsVerbs.hasNext()) {

				Entry<String, JsonNode> fieldVerb = fieldsVerbs.next();
				String fieldNameVerb = fieldVerb.getKey();
				JsonNode jsonNodeVerb = fieldVerb.getValue();

				VerbJson verbJson = new VerbJson();
				verbJson.setVerb(fieldNameVerb);
				if (jsonNodeVerb.get("summary") != null) {
					verbJson.setSummary(jsonNodeVerb.get("summary").textValue());
				}
				if (jsonNodeVerb.get("operationId") != null) {
					verbJson.setOperationId(jsonNodeVerb.get("operationId")
							.textValue());
				}
				if (jsonNodeVerb.get("produces") != null) {
					List<String> produces = new ArrayList<String>();
					Iterator<JsonNode> it = jsonNodeVerb.get("produces")
							.elements();
					while (it.hasNext()) {
						JsonNode jsonNodeProduce = it.next();
						produces.add(jsonNodeProduce.toString().replaceAll(
								"\"", ""));
					}
					verbJson.setProduces(produces);
				}
				if (jsonNodeVerb.get("parameters") != null) {

					List<ParameterJson> parameters = new ArrayList<ParameterJson>();

					Iterator<JsonNode> it = jsonNodeVerb.get("parameters")
							.elements();
					while (it.hasNext()) {
						JsonNode jsonNodeParameter = it.next();

						ParameterJson parameterJson = new ParameterJson();

						if (jsonNodeParameter.get("name") != null) {
							parameterJson.setName(jsonNodeParameter.get("name")
									.textValue());
						}
						if (jsonNodeParameter.get("in") != null) {
							parameterJson.setIn(jsonNodeParameter.get("in")
									.textValue());
						}
						if (jsonNodeParameter.get("required") != null) {
							parameterJson.setRequired(jsonNodeParameter.get(
									"required").booleanValue());
						}
						if (jsonNodeParameter.get("type") != null) {
							parameterJson.setType(jsonNodeParameter.get("type")
									.textValue());
						}
						if (jsonNodeParameter.get("schema") != null) {

							List<RefJson> schema = new ArrayList<RefJson>();

							Iterator<JsonNode> itSchema = jsonNodeParameter
									.get("schema").elements();

							while (itSchema.hasNext()) {

								JsonNode jsonNodeItem = itSchema.next();

								RefJson refJson = new RefJson();
								refJson.set$ref(jsonNodeItem.toString()
										.replaceAll("\"", ""));

								schema.add(refJson);
							}

							parameterJson.setSchema(schema);
						}

						parameters.add(parameterJson);

					}

					verbJson.setParameters(parameters);

				}
				if (jsonNodeVerb.get("responses") != null) {

					List<ResponseJson> responses = new ArrayList<ResponseJson>();

					Iterator<Entry<String, JsonNode>> fieldsResponses = jsonNodePath
							.fields();

					while (fieldsResponses.hasNext()) {

						Entry<String, JsonNode> fieldResponse = fieldsResponses
								.next();
						String fieldNameResponse = fieldResponse.getKey();
						JsonNode jsonNodeResponse = fieldResponse.getValue();

						ResponseJson responseJson = new ResponseJson();
						responseJson.setCode(fieldNameResponse);
						if (jsonNodeResponse.get("description") != null) {
							responseJson.setDescription(jsonNodeResponse.get(
									"description").textValue());
						}
						if (jsonNodeResponse.get("schema") != null) {

							List<RefJson> schema = new ArrayList<RefJson>();

							Iterator<JsonNode> itSchema = jsonNodeResponse.get(
									"schema").elements();

							while (itSchema.hasNext()) {

								JsonNode jsonNodeItem = itSchema.next();

								RefJson refJson = new RefJson();
								refJson.set$ref(jsonNodeItem.toString()
										.replaceAll("\"", ""));

								schema.add(refJson);
							}

							responseJson.setSchema(schema);
						}

						responses.add(responseJson);

					}
					verbJson.setResponses(responses);
				}

				verbs.add(verbJson);

			}

			pathJson.setVerbs(verbs);

		}

		swaggerJson.setPaths(paths);

		return swaggerJson;

	}

	public static Swagger parse(String jsonSwagger) throws Exception {

		return buildSawgger(parse2(jsonSwagger));

	}

	private static Swagger buildSawgger(SwaggerJson swaggerJson)
			throws Exception {
		Swagger swagger = new Swagger();

		swagger.setSwagger(swaggerJson.getSwagger());
		if (swaggerJson.getInfo() != null) {
			Info info = new Info();
			info.setDescription(swaggerJson.getInfo().getDescription());
			info.setVersion(swaggerJson.getInfo().getVersion());
			info.setTitle(swaggerJson.getInfo().getTitle());
			if (swaggerJson.getInfo().getContact() != null) {
				Contact contact = new Contact();
				contact.setName(swaggerJson.getInfo().getContact().getName());
				contact.setUrl(swaggerJson.getInfo().getContact().getUrl());
				info.setContact(contact);
			}
			swagger.setInfo(info);
		}

		swagger.setHost(swaggerJson.getHost());
		if (swaggerJson.getTags() != null) {
			for (TagJson tagJson : swaggerJson.getTags()) {
				Tag tag = new Tag();
				tag.setName(tagJson.getName());
				swagger.addTag(tag);
			}
		}
		if (swaggerJson.getSchemes() != null) {
			for (String schemeName : swaggerJson.getSchemes()) {
				boolean b = false;
				for (Scheme item : Scheme.values()) {
					if (item.toValue().equalsIgnoreCase(schemeName)) {
						swagger.addScheme(item);
						b = true;
					}
				}
				if (b == false) {
					throw new Exception("Scheme " + schemeName + " not found.");
				}
			}
		}
		if (swaggerJson.getProduces() != null) {
			for (String produceName : swaggerJson.getProduces()) {
				swagger.addProduces(produceName);
			}
		}
		if (swaggerJson.getConsumes() != null) {
			for (String consumeName : swaggerJson.getConsumes()) {
				swagger.addConsumes(consumeName);
			}
		}
		if (swaggerJson.getDefinitions() != null) {
			for (ModelJson modelJson : swaggerJson.getDefinitions()) {

				ModelImpl model = new ModelImpl();
				model.setType(modelJson.getType());
				if (modelJson.getProperties() != null) {
					for (PropertyJson propertyJson : modelJson.getProperties()) {
						String type = propertyJson.getType();
						String format = propertyJson.getFormat();
						if (type == null) {
							type = "";
						}
						if (format == null) {
							format = "";
						}

						AbstractProperty abstractProperty = null;

						if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("uuid")) {
							// UUIDProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("ref")) {
							// RefProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("password")) {
							// PasswordProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("object")) {
							// ObjectProperty
							// MapProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("integer")
								&& format.equalsIgnoreCase("int64")) {

							abstractProperty = new LongProperty();
						} else if (type.equalsIgnoreCase("integer")
								&& format.equalsIgnoreCase("int32")) {

							abstractProperty = new IntegerProperty();

						} else if (type.equalsIgnoreCase("number")
								&& format.equalsIgnoreCase("float")) {
							// FloatProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("number")
								&& format.equalsIgnoreCase("double")) {
							// DoubleProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("number")) {
							// DecimalProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("file")) {
							// FileProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("email")) {
							// EmailProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("date-time")) {

							abstractProperty = new DateTimeProperty();

						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("date")) {
							// DateProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("byte")) {
							// ByteArrayProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("boolean")) {
							// BooleanProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("string")
								&& format.equalsIgnoreCase("binary")) {
							// BinaryProperty
							throw new RuntimeException("type: " + type
									+ ", format:" + format + ", no soportado.");
						} else if (type.equalsIgnoreCase("array")) {
							abstractProperty = new ArrayProperty();
							ArrayProperty arrayProperty = (ArrayProperty) abstractProperty;
							if (propertyJson.getItems() != null) {
								for (RefJson refJson : propertyJson.getItems()) {
									if (refJson.get$ref() != null) {
										String ref = refJson.get$ref()
												.replaceFirst("#/definitions/",
														"");
										RefProperty refProperty = new RefProperty(
												ref);
										arrayProperty.setItems(refProperty);
									}
								}
							}
						} else if (type.equalsIgnoreCase("string")) {
							abstractProperty = new StringProperty();
							StringProperty stringProperty = (StringProperty) abstractProperty;
							if (propertyJson.getEnum() != null) {
								stringProperty.setEnum(propertyJson.getEnum());
							}
						}

						model.addProperty(propertyJson.getName(),
								abstractProperty);

					}

				}

				swagger.addDefinition(modelJson.getName(), model);
			}
		}
		if (swaggerJson.getPaths() != null) {

			for (PathJson pathJson : swaggerJson.getPaths()) {

				Path path = new Path();

				for (VerbJson verbJson : pathJson.getVerbs()) {

					Operation op = new Operation();
					op.setSummary(verbJson.getSummary());
					op.setOperationId(verbJson.getOperationId());
					op.setProduces(verbJson.getProduces());

					if (verbJson.getParameters() != null) {
						for (ParameterJson parameterJson : verbJson
								.getParameters()) {

							Parameter parameter = null;
							if (parameterJson.getIn()
									.equalsIgnoreCase("header")) {
								parameter = new HeaderParameter();
								((HeaderParameter) parameter)
										.setType(parameterJson.getType());
								parameter.setRequired(parameterJson
										.getRequired());
							} else if (parameterJson.getIn().equalsIgnoreCase(
									"body")) {
								parameter = new BodyParameter();
								parameter.setRequired(parameterJson
										.getRequired());
								Model schema = swagger.getDefinitions().get(
										parameterJson
												.getSchema()
												.get(0)
												.get$ref()
												.replaceFirst("#/definitions/",
														""));
								((BodyParameter) parameter).setSchema(schema);
							} else if (parameterJson.getIn().equalsIgnoreCase(
									"path")) {
								parameter = new PathParameter();
								((PathParameter) parameter)
										.setType(parameterJson.getType());
							} else if (parameterJson.getIn().equalsIgnoreCase(
									"query")) {
								parameter = new QueryParameter();
								((QueryParameter) parameter)
										.setType(parameterJson.getType());
								parameter.setRequired(parameterJson
										.getRequired());
							}

							op.addParameter(parameter);
						}
					}

					if (verbJson.getResponses() != null) {
						for (ResponseJson responseJson : verbJson
								.getResponses()) {
							Response response = new Response();
							response.setDescription(responseJson
									.getDescription());
							if (responseJson.getSchema() != null
									&& responseJson.getSchema().size() > 0) {
								String ref = responseJson.getSchema().get(0)
										.get$ref()
										.replaceFirst("#/definitions/", "");
								RefProperty refProperty = new RefProperty(ref);
								response.setSchema(refProperty);
							}

							op.addResponse(responseJson.getCode(), response);
						}
					}

					if (verbJson.getVerb().equalsIgnoreCase("GET")) {
						path.setGet(op);
					} else if (verbJson.getVerb().equalsIgnoreCase("POST")) {
						path.setPost(op);
					} else if (verbJson.getVerb().equalsIgnoreCase("PUT")) {
						path.setPut(op);
					} else if (verbJson.getVerb().equalsIgnoreCase("PATCH")) {
						path.setPatch(op);
					} else if (verbJson.getVerb().equalsIgnoreCase("DELETE")) {
						path.setDelete(op);
					} else {
						throw new Exception(verbJson.getVerb()
								+ " no esperado !.");
					}

				}

				swagger.path(pathJson.getPath(), path);

			}

		}

		return swagger;

	}

}
