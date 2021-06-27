module org.infor {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.graphics;
    requires java.base;
    requires java.net.http;
    requires json.simple;
    opens org.infor to javafx.fxml;
    exports org.infor;
}