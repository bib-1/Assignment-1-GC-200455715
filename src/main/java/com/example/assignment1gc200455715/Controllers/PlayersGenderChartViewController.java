package com.example.assignment1gc200455715.Controllers;

import com.example.assignment1gc200455715.Utiliites.DBUtility;
import com.example.assignment1gc200455715.Utiliites.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlayersGenderChartViewController implements Initializable {

    @FXML
    private Label lblTitle;

    @FXML
    private Button btnSceneChanger;

    @FXML
    private RadioButton btnCountry;

    @FXML
    private RadioButton btnGender;

    @FXML
    private RadioButton btnRating;


    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            pieChart.setData(DBUtility.getPieChartData());
            btnGender.setSelected(true); //showing the focus on button

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
        SceneChanger.changeScene(event, "players-rating-chart-view.fxml", " Chess Player By Ratings");
    }

    /**
     * Change scene to gender
     */
    @FXML
    void loadGender(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-gender-chart-view.fxml", "Chess Player By Gender");
    }

    /**
     * Change scene to countries
     */
    @FXML
    void loadCountries(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "players-country-chart-view.fxml", "Chess Player By Countries");
    }
}
