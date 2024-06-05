package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import pt.ipp.isep.dei.esoft.project.application.authorization.AuthenticationController;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MenuItem;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.AdminUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.HRMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.VFMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.GSMUI;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.CollaboratorUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AuthenticationGUI {

    private AuthenticationController ctrl = new AuthenticationController();

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void doLogin(ActionEvent event) {
        String id = userIdField.getText();
        String pwd = passwordField.getText();
        boolean success = ctrl.doLogin(id, pwd);

        if (success) {
            List<UserRoleDTO> roles = ctrl.getUserRoles();
            if (roles == null || roles.isEmpty()) {
                showAlert(AlertType.ERROR, "Login Failed", "No role assigned to user.");
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (role != null) {
                    List<MenuItem> rolesUI = getMenuItemForRoles();
                    redirectToRoleUI(rolesUI, role);
                } else {
                    showAlert(AlertType.WARNING, "Role Selection", "No role selected.");
                }
            }
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid UserId and/or Password.");
        }
    }

    private List<MenuItem> getMenuItemForRoles() {
        List<MenuItem> rolesUI = new ArrayList<>();
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_ADMIN, new AdminUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_HRM, new HRMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_VFM, new VFMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_GSM, new GSMUI()));
        rolesUI.add(new MenuItem(AuthenticationController.ROLE_COLLABORATOR, new CollaboratorUI()));

        //TODO: Complete with other user roles and related RoleUI
        return rolesUI;
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        List<String> roleDescriptions = new ArrayList<>();
        for (UserRoleDTO role : roles) {
            roleDescriptions.add(role.getDescription());
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(roleDescriptions.get(0), roleDescriptions);
        dialog.setTitle("Role Selection");
        dialog.setHeaderText("Select the role you want to adopt in this session:");
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String selectedRole = result.get();
            for (UserRoleDTO role : roles) {
                if (role.getDescription().equals(selectedRole)) {
                    return role;
                }
            }
        }
        return null;
    }

    private void redirectToRoleUI(List<MenuItem> rolesUI, UserRoleDTO role) {
        boolean found = false;
        for (MenuItem item : rolesUI) {
            if (item.hasDescription(role.getDescription())) {
                found = true;
                item.run();
                break;
            }
        }
        if (!found) {
            showAlert(AlertType.WARNING, "Role UI", "There is no UI for users with role '" + role.getDescription() + "'");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}