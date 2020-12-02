package gr.nordicmarina.configurator.repository;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;

import gr.nordicmarina.configurator.config.FirebaseConfiguration;
import gr.nordicmarina.configurator.domain.Boat;
import gr.nordicmarina.configurator.domain.Equipment;

public class FirebaseBoatRepositoryTest {
	
	@Test
	public void test() throws IOException {
		BoatRepository repo = new FirebaseBoatRepository(new FirebaseConfiguration().fireStore("firebase.json"));
		
		repo.findAll();
	}

}
