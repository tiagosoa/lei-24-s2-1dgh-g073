package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;

import java.io.IOException;

public class LoginUIController {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;


    @FXML
    private void doLogin(ActionEvent event) {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        AuthenticationRepository authRepo = Repositories.getInstance().getAuthenticationRepository();
        if (authRepo.doLogin(userId, password)) {
            try {
                App app = new App();
                app.showGSMUI((Stage) ((Node) event.getSource()).getScene().getWindow());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }
}


