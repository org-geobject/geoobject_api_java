package org.geoobject.dao;

import java.util.List;

import org.geoobject.model.Continent;

public interface IContinentDAO {
	
	public List<Continent> findAll() throws Exception;
	
	public List<Continent> findAll(int start, int maxresults) throws Exception;

	List<Continent> findAll(String name) throws Exception;
	
	List<Continent> findAll(String name, int start, int maxresults) throws Exception;

	Continent findById(String id) throws Exception;

}
