package gr.nordicmarina.configurator.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class Updater {
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
	
		// Use a service account
		Updater.class.getClassLoader().getResourceAsStream("firebase.json");
		InputStream serviceAccount = new FileInputStream("src/main/resources/firebase.json");
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(credentials)
		    .build();
		FirebaseApp.initializeApp(options);

		Firestore db = FirestoreClient.getFirestore();
		// asynchronously retrieve all users
		for (int i=0; i < 100; i++) {
		DocumentReference docRef = db.collection("Equipment").document();
		// Add document data  with id "alovelace" using a hashmap
		Map<String, Object> data = new HashMap<>();
		data.put("description", "a boat"+i); 
		data.put("priceInCents", 1815*i);
		//asynchronously write data
		ApiFuture<WriteResult> result = docRef.set(data);
		// ...
		// result.get() blocks on response
		System.out.println("Update time : " + result.get().getUpdateTime());
		}
		// asynchronously retrieve all users
		ApiFuture<QuerySnapshot> query = db.collection("Equipment").get();
		// ...
		// query.get() blocks on response
		QuerySnapshot querySnapshot = query.get();
		List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
		for (QueryDocumentSnapshot document : documents) {
		  System.out.println("id: " + document.getId());
		  System.out.println("desc: " + document.getString("description"));
		  
		  System.out.println("price: " + document.getLong("priceInCents")); 
		}
		
	}

}
