package com.example.csc325_firebase_webview_auth.view;

import com.example.csc325_firebase_webview_auth.model.FirestoreContext;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static Scene scene;
    private final FirestoreContext contxtFirebase = new FirestoreContext();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize Firestore + Firebase Auth
            fstore = contxtFirebase.firebase();
            fauth = FirebaseAuth.getInstance();

            // Debug: Check if FXML is found
            URL fxmlUrl = App.class.getResource("/files/AccessFBView.fxml");
            System.out.println("FXML path: " + fxmlUrl);

            if (fxmlUrl == null) {
                throw new IOException("FXML file not found at /files/AccessFBView.fxml");
            }

            // Load and show GUI
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();

            scene = new Scene(root);
            primaryStage.setTitle("Firebase JavaFX App");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        URL url = App.class.getResource(fxml);
        if (url == null) {
            throw new IOException("FXML file not found at: " + fxml);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
