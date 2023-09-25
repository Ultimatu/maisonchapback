package com.tonde.maisonchapback.services.mail;

import com.tonde.maisonchapback.domains.User;
import com.tonde.maisonchapback.exceptions.CustomLogger;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class MailService {

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";
    private final Logger log = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${mail.from}")
    private String fromAddress;
    @Value("${mail.baseUrl}")
    private String baseUrl;

    public MailService(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug(
                "Send email[multipart '{}' and html '{}'] to '{}' with subject '{}' and content={}",
                isMultipart,
                isHtml,
                to,
                subject,
                content
        );

        // Prepare message using a Spring helper
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setFrom(fromAddress);
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
            log.debug("Sent email to User '{}'", to);
        } catch (MailException | MessagingException e) {
            log.warn("Email could not be sent to user '{}'", to, e);
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String titleKey, String key) {
        if (user.getEmail() == null) {
            log.debug("Email doesn't exist for user '{}'", user.getEmail());
            return;
        }
        CustomLogger.log("INFO", "Sending email to '{}'" + user.getEmail());
        Locale locale = Locale.forLanguageTag(user.getLangkey());
        Context context = new Context(locale);
        context.setVariable(USER, user);
        context.setVariable("key", key);
        context.setVariable(BASE_URL, baseUrl); // Utilisation de la base URL définie dans la configuration
        String content = templateEngine.process(templateName, context);
        String subject = "Activation de compte";
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(User user, String key) {
        log.debug("Sending activation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/activationEmail", "email.activation.title", key);
    }

    @Async
    public void sendCreationEmail(User user, String key) {
        log.debug("Sending creation email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/creationEmail", "email.activation.title", key);
    }

    @Async
    public void sendPasswordResetMail(User user, String key) {
        log.debug("Sending password reset email to '{}'", user.getEmail());
        sendEmailFromTemplate(user, "mail/passwordResetEmail", "email.reset.title", key);
    }


}
