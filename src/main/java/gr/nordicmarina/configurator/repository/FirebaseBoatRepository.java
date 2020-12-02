package gr.nordicmarina.configurator.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import gr.nordicmarina.configurator.domain.Boat;
import gr.nordicmarina.configurator.domain.Equipment;
@Component
public class FirebaseBoatRepository implements BoatRepository {
	
	private static final String COLLECTION_NAME = "boat";

	Logger logger = LoggerFactory.getLogger(FirebaseBoatRepository.class);

	private final  Firestore db;

	

	public FirebaseBoatRepository(Firestore db) {
		this.db = db;
	}



	@Override
	public List<Boat> findAll() {
	
		ApiFuture<QuerySnapshot> query = db.collection(COLLECTION_NAME).get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot;
		try {
			querySnapshot = query.get();
		
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot s : documents) {
				s.getId();
			}
			return documents.stream().map(Boat::new).collect(Collectors.toList());
		
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Error loading documents: ", e);
			throw new RuntimeException(e);
		}
	}

	

	@Override
	public void deleteAll() {
		findAll().stream().forEach(p-> {
			db.collection(COLLECTION_NAME).document(p.getId()).delete();
		});
		
	}



	@Override
	public Boat add(Boat e) {
		try {
			DocumentSnapshot snapshot = db.collection(COLLECTION_NAME).add(e.toMap()).get().get().get();
			return new Boat(snapshot.getData());
		} catch (InterruptedException | ExecutionException e1) {
			throw new RuntimeException(e1);
		}
	}

}
