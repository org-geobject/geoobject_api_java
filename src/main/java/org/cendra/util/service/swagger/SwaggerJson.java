package org.cendra.util.service.swagger;

import java.util.List;

public class SwaggerJson {

	private String swagger = "2.0";
	private InfoJson info;
	private String host;
	private String basePath;
	private List<TagJson> tags;
	private List<String> schemes;
	private List<String> consumes;
	private List<String> produces;
	private List<PathJson> paths;
	private List<ModelJson> definitions;

	public String getSwagger() {
		return swagger;
	}

	public void setSwagger(String swagger) {
		this.swagger = swagger;
	}

	public InfoJson getInfo() {
		return info;
	}

	public void setInfo(InfoJson info) {
		this.info = info;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public List<TagJson> getTags() {
		return tags;
	}

	public void setTags(List<TagJson> tags) {
		this.tags = tags;
	}

	public List<String> getSchemes() {
		return schemes;
	}

	public void setSchemes(List<String> schemes) {
		this.schemes = schemes;
	}

	public List<String> getConsumes() {
		return consumes;
	}

	public void setConsumes(List<String> consumes) {
		this.consumes = consumes;
	}

	public List<String> getProduces() {
		return produces;
	}

	public void setProduces(List<String> produces) {
		this.produces = produces;
	}

	public List<PathJson> getPaths() {
		return paths;
	}

	public void setPaths(List<PathJson> paths) {
		this.paths = paths;
	}

	public List<ModelJson> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(List<ModelJson> definitions) {
		this.definitions = definitions;
	}

	@Override
	public String toString() {
		return "SwaggerJson [swagger=" + swagger + ", info=" + info + ", host="
				+ host + ", basePath=" + basePath + ", tags=" + tags
				+ ", schemes=" + schemes + ", consumes=" + consumes
				+ ", produces=" + produces + ", paths=" + paths
				+ ", definitions=" + definitions + "]";
	}

}
