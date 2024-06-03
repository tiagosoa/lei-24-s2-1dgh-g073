package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.config.Config;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
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

        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}