module com.mycompany.hellojasper {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires java.sql;
    requires jasperreports;

    opens com.mycompany.examen to javafx.fxml, javafx.swing, java.sql;
    opens models to javafx.fxml;
    exports com.mycompany.examen;
    exports models;
}
