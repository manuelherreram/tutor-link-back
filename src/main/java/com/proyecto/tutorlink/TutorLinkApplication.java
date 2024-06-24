package com.proyecto.tutorlink;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

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
	@EventListener(ApplicationReadyEvent.class)

	public void applicationReady() {
		System.out.println("\n \uD83D\uDE80 TutorLink is live at port 8080! Let's go!\n" +
				"\uD83D\uDCDA API documentation is available on Swagger at http://localhost:8080/swagger-ui.html\n \n");
	}
}