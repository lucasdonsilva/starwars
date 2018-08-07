package br.com.starwars.business;

import java.util.List;

import br.com.starwars.model.Planet;

public interface PlanetBusiness {

	Planet create(Planet planet);
	List<Planet> findAll();
	Planet findByName(String name);
	Planet findById(Long id);
	void deleteByName(String name);
	void deleteById(Long id);
}
