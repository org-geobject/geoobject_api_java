package org.geoobject.model;

public class Currency {

	@SuppressWarnings("unused")
	private String id;
	private String code;

	public String getId() {
		return code;
	}

	public void setId(String id) {
		this.code = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Currency [code=" + code + "]";
	}

}
