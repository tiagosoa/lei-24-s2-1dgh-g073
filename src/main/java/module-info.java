module pt.ipp.isep.dei.esoft.project.ui.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires AuthLib;
    requires java.logging;
    requires org.apache.commons.lang3;
    requires java.desktop;

    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;

    opens pt.ipp.isep.dei.esoft.project.ui.console.authorization to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.console.authorization;

    opens pt.ipp.isep.dei.esoft.project.application.controller to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.application.controller to javafx.fxml;
}