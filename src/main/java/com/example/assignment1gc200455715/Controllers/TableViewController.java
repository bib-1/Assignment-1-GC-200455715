package com.example.assignment1gc200455715.Controllers;

import com.example.assignment1gc200455715.Utiliites.DBUtility;
import com.example.assignment1gc200455715.Model.ChessPlayer;
import com.example.assignment1gc200455715.Utiliites.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TableViewController implements Initializable {

    @FXML
    private TableView<ChessPlayer> chessPlayerTableView;

    @FXML
    private TableColumn<ChessPlayer, Integer> playerIdColumn;

    @FXML
    private TableColumn<ChessPlayer, String> nameColumn;

    @FXML
    private TableColumn<ChessPlayer, String> countryColumn;

    @FXML
    private TableColumn<ChessPlayer, String> genderColumn;

    @FXML
    private TableColumn<ChessPlayer, Integer> ratingColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //adding the player object to populate in the table
        try {
            chessPlayerTableView.getItems().addAll(DBUtility.getChessPlayersFromDB()); //getting the players object to populate in the table
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // This lines sets the value in the columns of the table by getting the properties from the ChessPlayer class
        // the value inside the braces should be the name of the instance variables of the object you are using for that table
        playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("sex"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<>("rating"));
    }

    /**
     *Change scene to the chart view
     */
    @FXML
    void loadChartData(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-country-chart-view.fxml", "Top Ten Country with Highest Number of Players");
    }
}
