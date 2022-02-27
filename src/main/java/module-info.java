module com.example.assignment1gc200455715 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.assignment1gc200455715 to javafx.fxml;
    exports com.example.assignment1gc200455715.Model;
    opens com.example.assignment1gc200455715.Model to javafx.fxml;
    exports com.example.assignment1gc200455715.Controllers;
    opens com.example.assignment1gc200455715.Controllers to javafx.fxml;
    exports com.example.assignment1gc200455715.Utiliites;
    opens com.example.assignment1gc200455715.Utiliites to javafx.fxml;
    exports com.example.assignment1gc200455715;
}