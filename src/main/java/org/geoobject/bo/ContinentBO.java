package org.geoobject.bo;

import java.util.List;

import org.geoobject.dao.IContinentDAO;
import org.geoobject.model.Continent;

public class ContinentBO {

	private IContinentDAO continentDAO;

	public ContinentBO(IContinentDAO continentDAO) {
		super();
		this.continentDAO = continentDAO;
	}

	public List<Continent> findAll(String name, Integer start, Integer maxResults) throws Exception {

		if (start == null && maxResults == null) {
			if (name == null || name.isEmpty()) {
				return continentDAO.findAll();
			} else {
				return continentDAO.findAll(name);
			}
		}		
		
		if (start == null || start < 0) {
			start = 0;
		}

		if (maxResults == null || maxResults < 0 || maxResults > 50) {
			maxResults = 50;
		}

		if (name == null || name.isEmpty()) {
			return continentDAO.findAll(start, maxResults);
		} else {
			return continentDAO.findAll(name, start, maxResults);
		}

	}

	public Continent findById(String id) throws Exception {

		return continentDAO.findById(id);

	}

}
