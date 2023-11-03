module com.example.scene {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    //requires java.datatransfer;
    requires java.desktop;

    opens com.example.scene to javafx.fxml;
    exports com.example.scene;
}