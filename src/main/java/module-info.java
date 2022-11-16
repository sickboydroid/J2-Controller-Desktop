module com.tangledbytes.j2utils {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tangledbytes.j2utils to javafx.fxml;
    exports com.tangledbytes.j2utils;
}