package com.example.assignment1gc200455715;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;

public class PlayersCountryChartViewController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart; //datatype of x-axis and y-axis

    @FXML
    private CategoryAxis countryAxIS;

    @FXML
    private NumberAxis numberOfPlayersAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      barchart.getData().addAll(DBUtility.getCountryAndPlayer());

    }
}
