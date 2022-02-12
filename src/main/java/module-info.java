module com.example.assignment1gc200455715 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assignment1gc200455715 to javafx.fxml;
    exports com.example.assignment1gc200455715;
}