package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.CheckBox;

public class LoginUIController {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private CheckBox showPasswordCheckbox;

    @FXML
    private void doLogin(ActionEvent event) {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        AuthenticationRepository authRepo = Repositories.getInstance().getAuthenticationRepository();
        if (authRepo.doLogin(userId, password)) {
            try {
                List<UserRoleDTO> roles = authRepo.getUserRoles();
                if (roles != null && !roles.isEmpty()) {
                    UserRoleDTO role = roles.get(0);
                    App app = new App();

                    // Show the corresponding UI based on the user role
                    switch (role.getDescription()) {
                        case AuthenticationController.ROLE_GSM:
                            App.showGSMUI();
                            break;
                        case AuthenticationController.ROLE_COLLABORATOR:
                            App.showCollaboratorUI();
                            break;
                        // Add other roles and corresponding UIs here
                        default:
                            System.out.println("Role not recognized: " + role.getDescription());
                            break;
                    }
                } else {
                    System.out.println("No roles assigned to the user");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid credentials");
        }
    }

    @FXML
    private void togglePasswordVisibility() {
        if (showPasswordCheckbox.isSelected()) {
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
            passwordField.setDisable(true);
        } else {
            passwordField.setText(passwordField.getPromptText());
            passwordField.setPromptText("");
            passwordField.setDisable(false);
        }
    }
}

