package com.example.assignment1gc200455715;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class PlayersCountryChartViewController implements Initializable {

    @FXML
    private RadioButton btnCountry;

    @FXML
    private RadioButton btnGender;

    @FXML
    private RadioButton btnRating;


    @FXML
    private BarChart<String, Integer> barchart; //datatype of x-axis and y-axis

    @FXML
    private CategoryAxis countryAxIS;

    @FXML
    private NumberAxis numberOfPlayersAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      barchart.getData().addAll(DBUtility.getCountryAndPlayer());
      btnCountry.setSelected(true); //showing the focus on button


    }

    /**
     *Change scene to the table view
     */
    @FXML
    void loadTableView(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "table-view.fxml", "Chess Players");
    }
    /**
     * Change scene to rating
     */
    @FXML
    void loadRatings(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-rating-chart-view.fxml", "Top Ten Country with Highest Number of Players");
    }

    /**
     * Change scene to gender
     */
    @FXML
    void loadGender(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-gender-chart-view.fxml", "Top Ten Country with Highest Number of Players");
    }

    /**
     * Change scene to countries
     */
    @FXML
    void loadCountries(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-country-chart-view.fxml", "Top Ten Country with Highest Number of Players");
    }
}
