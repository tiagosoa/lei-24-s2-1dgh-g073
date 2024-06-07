package pt.ipp.isep.dei.esoft.project.ui.gui;

import pt.ipp.isep.dei.esoft.project.application.controller.RegisterGSUIController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.config.Config;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import javafx.scene.Parent;

import java.io.IOException;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        try {
            Config config = new Config("config.properties");
            String emailService = config.getProperty("email.service");
            System.out.println("Email Service: " + emailService);

            // Use the config values as needed
        } catch (IOException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));
        Pane root = loader.load();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showGSMUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GSMUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Green Space Manager");
        stage.show();
    }

    public void showRegisterGSUI(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterGSUI.fxml"));
        try {
            Parent root = loader.load();
            RegisterGSUIController controller = loader.getController();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Register Green Space");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAddEntryToDoListUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEntryToDoListUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Entry To Do List");
        stage.show();
    }

    public void showAddEntryAgendaUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddEntryAgendaUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add Entry to Agenda");
        stage.show();
    }

    public void showAssignTeamUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignTeamUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Assign Team to Agenda Entry");
        stage.show();
    }
    public void showPostponeEntryAgendaUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PostponeEntryAgendaUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Postpone Agenda Entry");
        stage.show();
    }

    public void showCancelEntryAgendaUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CancelEntryAgendaUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cancel Agenda Entry");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
