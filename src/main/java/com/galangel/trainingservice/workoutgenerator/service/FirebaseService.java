package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Connects to Firebase Realtime Database to fetch and cache available exercises.
 */
@Service
public class FirebaseService {
    private DatabaseReference database;
    private List<ExerciseDTO> cachedExercises; // Cache for exercises

    @PostConstruct
    public void init() {
        try {
            Dotenv dotenv = Dotenv.load();

            String firebaseCredentialsPath = dotenv.get("FIREBASE_CREDENTIALS");
            String databaseUrl = dotenv.get("FIREBASE_DB_URL");

            FileInputStream serviceAccount = new FileInputStream(firebaseCredentialsPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(databaseUrl)
                    .build();

            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance().getReference("exercisesWarehouse");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ExerciseDTO> getExercises() {
        if (cachedExercises != null && !cachedExercises.isEmpty()) {
            return cachedExercises; // Return cached exercises if available
        }

        List<ExerciseDTO> exercises = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    ExerciseDTO exercise = data.getValue(ExerciseDTO.class);
                    if (exercise != null) {
                        exercises.add(exercise);
                    }
                }
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cachedExercises = exercises; // Save exercises to cache
        return exercises;
    }

    // Method to refresh the cached exercises
    public void refreshExercisesCache() {
        cachedExercises = null;
    }
}

