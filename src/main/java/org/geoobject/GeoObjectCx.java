package org.geoobject;

import org.cendra.util.services.spark.AbstractContext;
import org.cendra.util.services.spark.IContext;
import org.geoobject.bo.ContinenteBO;

public class GeoObjectCx extends AbstractContext {

	private static IContext cx;

	private GeoObjectCx() {

	}

	public static IContext getInstance() {
		if (cx == null) {
			cx = new GeoObjectCx();
		}

		return cx;
	}

	public Object getBean(String name) {
		if ("continenteBO".equals(name)) {
			return new ContinenteBO();
		}

		return null;
	}

}
