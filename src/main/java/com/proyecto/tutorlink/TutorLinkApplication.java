package com.proyecto.tutorlink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.InputStream;

@SpringBootApplication
public class TutorLinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(TutorLinkApplication.class, args);
		initializeFirebase();

	}
	private static void initializeFirebase() {
		try (InputStream serviceAccount = TutorLinkApplication.class.getClassLoader().getResourceAsStream("firebase-admin-sdk.json")) {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
			}
		} catch (Exception e) {
			throw new IllegalStateException("Failed to initialize Firebase", e);
		}
	}
}