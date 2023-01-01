package com.tangledbytes.j2phonecontroller.controllers;

import com.tangledbytes.j2phonecontroller.utils.PhoneScreenViewer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EntryScreenController {
    public void viewScreenshots(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setOnCloseRequest((windowEvent) -> PhoneScreenViewer.setStop(true));
        stage.setScene(new Scene(FXMLLoader.load(EntryScreenController.class.getResource("/com/tangledbytes/j2phonecontroller/screens/phone-screen.fxml"))));
    }

}