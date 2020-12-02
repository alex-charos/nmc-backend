package gr.nordicmarina.configurator.config;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Configuration
public class FirebaseConfiguration {
	Logger logger = LoggerFactory.getLogger(FirebaseConfiguration.class);

	 
	@Bean
	public Firestore fireStore(@Value("${config.firebase.location}") String firebaseConfigLocation ) throws IOException {
		logger.info("Loading from {}", firebaseConfigLocation);
		InputStream serviceAccount = FirebaseConfiguration.class.getClassLoader()
				.getResourceAsStream(firebaseConfigLocation);
		GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
		FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(credentials).build();
		FirebaseApp.initializeApp(options);

		return FirestoreClient.getFirestore();
	}

}
