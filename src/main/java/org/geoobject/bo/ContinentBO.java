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

	public List<Continent> findAll(String name, Integer start, Integer maxresults) throws Exception {

		if (start == null && maxresults == null) {
			if (name == null || name.isEmpty()) {
				return continentDAO.findAll();
			} else {
				return continentDAO.findAll(name);
			}
		}		
		
		if (start == null || start < 0) {
			start = 0;
		}

		if (maxresults == null || maxresults < 0 || maxresults > 100) {
			maxresults = 100;
		}

		if (name == null || name.isEmpty()) {
			return continentDAO.findAll(start, maxresults);
		} else {
			return continentDAO.findAll(name, start, maxresults);
		}

	}

	public Continent findById(String id) throws Exception {

		return continentDAO.findById(id);

	}

}
