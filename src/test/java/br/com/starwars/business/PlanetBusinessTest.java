package br.com.starwars.business;

import br.com.starwars.component.Validate;
import br.com.starwars.document.PlanetDocument;
import br.com.starwars.exception.PlanetAlreadyExistsException;
import br.com.starwars.exception.PlanetNotFoundException;
import br.com.starwars.repository.PlanetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.starwars.support.ScenarioBuilder.generatePlanet;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanetBusinessTest {

    @Mock
    private PlanetRepository repository;
    @Mock
    private PlanetSwapiBusiness swapiBusiness;
	@Mock
	private Validate validate;
    @InjectMocks
	private PlanetBusiness business;

    @Test
    public void methodCreateShouldReturnAPlanet() {

		PlanetDocument planet = generatePlanet();
		when(repository.findByName(anyString())).thenReturn(empty());
		when(repository.insert(planet)).thenReturn(planet);
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
		
		PlanetDocument p = business.create(planet);

		assertThat(p.getName()).isEqualTo(planet.getName());
		assertThat(p.getClimate()).isEqualTo(planet.getClimate());
		assertThat(p.getTerrain()).isEqualTo(planet.getTerrain());
		assertThat(p.getApparitionsCount()).isEqualTo(1);
    }
    
    @Test(expected = PlanetAlreadyExistsException.class)
    public void methodCreateShouldReturnAPlanetAlreadyExistsException() {

		PlanetDocument planet = generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));
		
		business.create(planet);
    }
    
    @Test
    public void methodFindAllShouldReturnAListOfPlanet() {

    	List<PlanetDocument> planets = singletonList(generatePlanet());
    	when(repository.findAll()).thenReturn(planets);
    	when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);

		List<PlanetDocument> ps = business.findAll();

		assertThat(planets.get(0).getName()).isEqualTo(ps.get(0).getName());
		assertThat(planets.get(0).getClimate()).isEqualTo(ps.get(0).getClimate());
		assertThat(planets.get(0).getTerrain()).isEqualTo(ps.get(0).getTerrain());
		assertThat(planets.get(0).getApparitionsCount()).isEqualTo(1);
    }

    @Test
    public void methodFindByNameShouldReturnAPlanet() {

		PlanetDocument planet = generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);

		PlanetDocument p = business.findByName("test");

		assertThat(p.getName()).isEqualTo(planet.getName());
		assertThat(p.getClimate()).isEqualTo(planet.getClimate());
		assertThat(p.getTerrain()).isEqualTo(planet.getTerrain());
		assertThat(p.getApparitionsCount()).isEqualTo(1);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void methodFindByNameShouldReturnAPlanetNotFoundException() {

		when(repository.findByName(anyString())).thenReturn(empty());
		business.findByName("test");
    }
    
    @Test
    public void methodFindByIdShouldReturnAPlanet() {
    	
		PlanetDocument planet = generatePlanet();
		when(repository.findById(anyString())).thenReturn(of(planet));
		when(swapiBusiness.getApparitionsCount(anyString())).thenReturn(1);
		
		PlanetDocument p = business.findById("test");
		
		assertThat(p.getName()).isEqualTo(planet.getName());
		assertThat(p.getClimate()).isEqualTo(planet.getClimate());
		assertThat(p.getTerrain()).isEqualTo(planet.getTerrain());
		assertThat(p.getApparitionsCount()).isEqualTo(1);
    }

    @Test(expected = PlanetNotFoundException.class)
    public void methodFindByIdShouldReturnAPlanetNotFoundException() {
    	
		when(repository.findById(anyString())).thenReturn(empty());
		PlanetDocument planet = business.findById("test");
    }
    
    @Test
    public void methodDeleteByNameShouldReturnSuccess() {
    	
    	PlanetDocument planet = generatePlanet();
		when(repository.findByName(anyString())).thenReturn(of(planet));

		business.deleteByName("teste");
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodDeleteByNameShouldReturnAPlanetNotFoundException() {

		when(repository.findByName(anyString())).thenReturn(empty());

		business.deleteByName("teste1");
    }

    @Test
    public void methodDeleteByIdShouldReturnSuccess() {
    	
    	PlanetDocument planet = generatePlanet();
		when(repository.findById(anyString())).thenReturn(of(planet));

		business.deleteById("teste");
    }
    
    @Test(expected = PlanetNotFoundException.class)
    public void methodDeleteByIdShouldReturnAPlanetNotFoundException() {
    	
		when(repository.findById(anyString())).thenReturn(empty());
		
		business.deleteById("teste");
    }
}
