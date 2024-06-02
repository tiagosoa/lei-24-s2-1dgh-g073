package pt.ipp.isep.dei.esoft.project.config;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
