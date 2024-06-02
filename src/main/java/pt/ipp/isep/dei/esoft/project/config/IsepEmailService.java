package pt.ipp.isep.dei.esoft.project.config;

public class IsepEmailService implements EmailService {
    private String username;
    private String password;

    public IsepEmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        // Implementation to send email using Gmail SMTP
    }
}

