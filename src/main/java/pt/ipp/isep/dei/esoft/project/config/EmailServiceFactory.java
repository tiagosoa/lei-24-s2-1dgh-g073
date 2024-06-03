package pt.ipp.isep.dei.esoft.project.config;

import java.io.IOException;

public class EmailServiceFactory {
    public static EmailService getEmailService(Config config) throws IOException {
        String service = config.getProperty("email.service");

        if ("gmail".equalsIgnoreCase(service)) {
            return new GmailEmailService(
                    config.getProperty("gmail.username"),
                    config.getProperty("gmail.password")
            );
        } else if ("isep".equalsIgnoreCase(service)) {
            return new IsepEmailService(
                    config.getProperty("isep.username"),
                    config.getProperty("isep.password")
            );
        }

        throw new IllegalArgumentException("Unknown email service: " + service);
    }
}
