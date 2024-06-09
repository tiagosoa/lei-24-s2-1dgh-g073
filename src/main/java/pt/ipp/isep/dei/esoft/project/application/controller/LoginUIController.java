package pt.ipp.isep.dei.esoft.project.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.repository.AuthenticationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.App;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class LoginUIController {

    @FXML
    private TextField userIdField;

    @FXML
    private TextField passwordField;

    @FXML
    private CheckBox showPasswordCheckbox;

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    @FXML
    private void doLogin(ActionEvent event) {
        String userId = userIdField.getText();
        String password = passwordField.getText();

        if (!isValidEmail(userId)) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return;
        }

        AuthenticationRepository authRepo = Repositories.getInstance().getAuthenticationRepository();
        try {
            if (authRepo.doLogin(userId, password)) {
                List<UserRoleDTO> roles = authRepo.getUserRoles();
                if (roles != null && !roles.isEmpty()) {
                    UserRoleDTO role = roles.get(0);

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
                            showAlert("Role not recognized", "Role not recognized: " + role.getDescription());
                            break;
                    }
                } else {
                    showAlert("No Roles", "No roles assigned to the user.");
                }
            } else {
                showAlert("Login Failed", "Invalid credentials.");
            }
        } catch (IllegalArgumentException e) {
            showAlert("Invalid Email", "The email address provided is invalid: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
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
