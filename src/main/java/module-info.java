module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafaker;

    exports org.example.ui;
    opens org.example.ui to javafx.fxml;
    exports org.example;
    opens org.example to javafx.fxml;
}