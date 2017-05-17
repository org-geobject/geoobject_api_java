package org.cendra.util.service.swagger;

public class RefJson {

	private String $ref;

	public String get$ref() {
		return $ref;
	}

	public void set$ref(String $ref) {
		this.$ref = $ref;
	}

	@Override
	public String toString() {
		return "RefJson [$ref=" + $ref + "]";
	}

}
