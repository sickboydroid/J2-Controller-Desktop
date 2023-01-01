package com.tangledbytes.j2phonecontroller.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EntryScreen.class.getResource("/com/tangledbytes/j2phonecontroller/screens/entry-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("J2 Phone Controller");
        stage.setScene(scene);
        stage.show();
    }
}