package org.geoobject.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.geoobject.model.Continent;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ContinentDAOFileImpl implements IContinentDAO {

	private String filePath;
	private ObjectMapper mapper;
	private boolean cache;
	private List<Continent> continents;

	public ContinentDAOFileImpl(String filePath, boolean cache) {
		super();
		this.filePath = filePath;
		this.cache = cache;
		mapper = new ObjectMapper();
	}

	@Override
	public List<Continent> findAll() throws Exception {

		if (cache && this.continents != null) {
			return continents;
		}

		List<Continent> continents = mapper.readValue(new File(filePath),
				mapper.getTypeFactory().constructCollectionType(List.class, Continent.class));

		if (continents == null) {
			continents = new ArrayList<Continent>();
		}
		
//		for (Continent item : continents) {
//			if(item.getOrthographicProjectionFile() != null){
//				item.getOrthographicProjectionFile().setXml(readTxt(item.getOrthographicProjectionFile().getPath()));
//			}
//		}

		Collections.sort(continents);

		// Collections.sort(continents, new Comparator<Continent>() {
		//
		// @Override
		// public int compare(Continent o1, Continent o2) {
		// return (int) o2.;
		// }
		// });

		if (cache) {
			this.continents = continents;
		}

		return continents;
	}

	@Override
	public List<Continent> findAll(String name) throws Exception {

		List<Continent> continents = findAll();

		List<Continent> continentsReturn = new ArrayList<Continent>();
		for (Continent item : continents) {
			if (item.allNames().toLowerCase().contains(name.toLowerCase())) {
				continentsReturn.add(item);
			}
		}

		return continentsReturn;
	}

	@Override
	public List<Continent> findAll(int start, int maxresults) throws Exception {

		List<Continent> continents = findAll();

		int end = start + maxresults;
		if (end > continents.size()) {
			end = continents.size();
		}
		return continents.subList(start, end);
	}

	@Override
	public List<Continent> findAll(String name, int start, int maxresults) throws Exception {
		List<Continent> continents = findAll(name);

		int end = start + maxresults;
		if (end > continents.size()) {
			end = continents.size();
		}
		return continents.subList(start, end);
	}

	@Override
	public Continent findById(String id) throws Exception {

		List<Continent> continents = findAll();

		for (Continent item : continents) {

			if (item.getId().equals(id)) {
				return item;
			}
		}

		for (Continent item : continents) {
			if (item.getGeonameId().equals(id)) {
				return item;
			}
		}

		return null;

	}

	private static String readTxt(String path) throws Exception {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;

		String txt = "";

		try {
			file = new File(path);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null)
				txt += line + "\n";
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				throw e2;
			}
		}

		txt = txt.trim();

		return txt;
	}

}
