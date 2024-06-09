package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.config.Config;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        App.primaryStage = primaryStage;
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
        showLoginUI();

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(500);
    }

    public static void showLoginUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("LoginUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        //setupFullscreen();

        primaryStage.show();
    }


    public static void showGSMUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("GSMUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Green Space Manager");
       // setupFullscreen();
        primaryStage.show();
    }

    public static void showRegisterGSUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("RegisterGSUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register Green Space");
        //setupFullscreen();
        primaryStage.show();
    }

    public static void showAddEntryToDoListUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AddEntryToDoListUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Entry To Do List");
        primaryStage.show();
    }

    public static void showAddEntryAgendaUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AddEntryAgendaUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Entry to Agenda");
        primaryStage.show();
    }

    public static void showAssignTeamUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("AssignTeamUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Assign Team to Agenda Entry");
        primaryStage.show();
    }

    public static void showPostponeEntryAgendaUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("PostponeEntryAgendaUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Postpone Agenda Entry");
        primaryStage.show();
    }

    public static void showCancelEntryAgendaUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CancelEntryAgendaUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cancel Agenda Entry");
        primaryStage.show();
    }

    public static void showAssignVehicleUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/pt/ipp/isep/dei/esoft/project/ui/gui/AssignVehicleUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Assign Vehicle to Agenda Entry");
        primaryStage.show();
    }

    public void showListManagedGSUI(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListManagedGSUI.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Managed Green Spaces");
        stage.show();
    }


    public static void showCollaboratorUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("CollaboratorUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Collaborator Menu");
        primaryStage.show();
    }

    public static void showConsultTasksUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("ConsultTasksUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Consult Tasks");
        primaryStage.show();
    }

    public static void showRecordTaskUI() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("RecordTaskUI.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Record Task");
        primaryStage.show();
    }

    private static void setupFullscreen() {
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint(""); // Disable the exit hint
        primaryStage.setFullScreenExitKeyCombination(null); // Disable the exit key combination
    }

    public static void main(String[] args) {
        launch(args);
    }
}

