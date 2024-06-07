package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.config.Config;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

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

    public static void main(String[] args) {
        launch(args);
    }
}
