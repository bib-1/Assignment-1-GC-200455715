package com.example.assignment1gc200455715;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("table-view.fxml"));
        // set icon
        //How to add image reference: https://stackoverflow.com/questions/10275841/how-to-change-the-icon-on-the-title-bar-of-a-stage-in-java-fx-2-0-of-my-applicat
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("img/chess.png"))));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chess Record");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}