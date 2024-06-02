package pt.ipp.isep.dei.esoft.project.config;

public class GmailEmailService implements EmailService {
    private String username;
    private String password;

    public GmailEmailService(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        // Implementation to send email using Gmail SMTP
    }
}

