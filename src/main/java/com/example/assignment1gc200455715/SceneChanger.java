package com.example.assignment1gc200455715;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {
    public static void changeScene (ActionEvent event, String fxmlFilename, String title) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Main.class.getResource(fxmlFilename)));
        //determining the stage based on the action button
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}
