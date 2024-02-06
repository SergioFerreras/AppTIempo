module org.example.apptiempo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens org.example.apptiempo to javafx.fxml;
    exports org.example.apptiempo;
    exports controller;
    opens controller to javafx.fxml;
}