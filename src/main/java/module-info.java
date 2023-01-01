

module com.tangledbytes.j2utils {
        requires javafx.controls;
        requires javafx.fxml;

        opens com.tangledbytes.j2phonecontroller to javafx.fxml;
        exports com.tangledbytes.j2phonecontroller;
        exports com.tangledbytes.j2phonecontroller.controllers;
        opens com.tangledbytes.j2phonecontroller.controllers to javafx.fxml;
        exports com.tangledbytes.j2phonecontroller.screens;
        opens com.tangledbytes.j2phonecontroller.screens to javafx.fxml;
        exports com.tangledbytes.j2phonecontroller.utils;
        opens com.tangledbytes.j2phonecontroller.utils to javafx.fxml;
}