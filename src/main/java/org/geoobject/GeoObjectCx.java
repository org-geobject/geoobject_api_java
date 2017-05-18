package org.geoobject;

import org.cendra.util.services.spark.AbstractContext;
import org.cendra.util.services.spark.IContext;
import org.geoobject.bo.ContinentBO;
import org.geoobject.dao.ContinentDAOFileImpl;
import org.geoobject.dao.IContinentDAO;

public class GeoObjectCx extends AbstractContext {

	private static IContext cx;

	private boolean cache;
	private String filePathContinents;
	
	private GeoObjectCx() {
		filePathContinents = "C:\\Users\\Usuario\\Downloads\\geo2\\geo_model\\continents.json";
	}

	public static IContext getInstance() {
		if (cx == null) {
			cx = new GeoObjectCx();
		}

		return cx;
	}

	public Object getBean(String name) {
		if ("continentBO".equals(name)) {
			IContinentDAO continentDAO = new ContinentDAOFileImpl(filePathContinents, cache);
			return new ContinentBO(continentDAO);
		}

		return null;
	}

}
