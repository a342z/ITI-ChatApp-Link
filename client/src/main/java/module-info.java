module gov.iti.link{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.rmi;
    requires transitive javafx.graphics;
    requires mysql.connector.j;
    requires java.sql.rowset;
    opens gov.iti.link.presentation.controllers to javafx.fxml;
    exports gov.iti.link.business.services to java.rmi;
    exports gov.iti.link.business.DTOs;

    exports gov.iti.link;
}
