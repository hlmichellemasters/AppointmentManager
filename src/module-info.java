module MichelleMastersC195 {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;

    opens model;
    opens controller;
    opens view;
}