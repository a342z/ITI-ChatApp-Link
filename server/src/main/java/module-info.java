module gov.iti.link {
    requires javafx.controls;
    requires java.sql;
    requires java.rmi;
    requires transitive javafx.graphics;
    exports gov.iti.link;

    exports gov.iti.link.business.services to java.rmi;
}