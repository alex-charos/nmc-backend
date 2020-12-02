package gr.nordicmarina.configurator.repository;

import java.util.List;

import gr.nordicmarina.configurator.domain.Boat;

public interface BoatRepository {

	List<Boat> findAll();
	void deleteAll();
	Boat add(Boat e);
	

}
