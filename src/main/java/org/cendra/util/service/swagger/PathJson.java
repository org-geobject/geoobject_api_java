package org.cendra.util.service.swagger;

import java.util.ArrayList;
import java.util.List;

public class PathJson {

	private String path;
	private List<VerbJson> verbs = new ArrayList<VerbJson>();

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<VerbJson> getVerbs() {
		return verbs;
	}

	public void setVerbs(List<VerbJson> verbs) {
		this.verbs = verbs;
	}

	@Override
	public String toString() {
		return "PathJson [path=" + path + ", verbs=" + verbs + "]";
	}

}
