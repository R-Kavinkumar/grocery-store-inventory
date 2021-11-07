module com.example.ksr_glocery {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ksr_glocery to javafx.fxml;
    exports com.example.ksr_glocery;
}