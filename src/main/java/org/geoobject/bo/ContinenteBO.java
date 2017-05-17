package org.geoobject.bo;

import java.util.ArrayList;
import java.util.List;

import org.geoobject.model.Continente;

public class ContinenteBO {

	public List<Continente> findAll(String id, Continente continenteArg) throws Exception {
		
		System.out.println("!!!!!!!!!!!!!!!!!!!! " + id + " " + continenteArg);

		List<Continente> continentes = new ArrayList<Continente>();

		Continente continente = new Continente();
		continente.setCode("AF");
		continente.setName("Africa");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("AS");
		continente.setName("Asia");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("EU");
		continente.setName("Europe");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("NA");
		continente.setName("North America");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("OC");
		continente.setName("Oceania");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("SA");
		continente.setName("South America");
		continentes.add(continente);

		continente = new Continente();
		continente.setCode("AN");
		continente.setName("Antarctica");
		continentes.add(continente);

		return continentes;
	}

}
