module org.pharmacy.pharmacymanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.pharmacy.pharmacymanagementsystem to javafx.fxml;
    exports org.pharmacy.pharmacymanagementsystem;
}