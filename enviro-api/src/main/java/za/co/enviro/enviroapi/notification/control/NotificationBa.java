package za.co.enviro.enviroapi.notification.control;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import za.co.enviro.enviroapi.accesscontrol.control.exceptions.SystemException;
import za.co.enviro.enviroapi.notification.entity.EmailTemplateEntity;
import za.co.enviro.enviroapi.usermanagement.entity.UserEntity;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Log
@Component
public class NotificationBa {

    private EmailTemplateRepository emailTemplateRepository;
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationBa(EmailTemplateRepository emailTemplateRepository,
                          JavaMailSender javaMailSender) {
        this.emailTemplateRepository = emailTemplateRepository;
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(UserEntity userEntity) {
        int userAge = userEntity.getAge();
        Optional<EmailTemplateEntity> emailTemplateEntity;
        if (userAge <= 28) {
            emailTemplateEntity = emailTemplateRepository.findByName("THE_BLIND");
        } else if (userAge <= 40) {
            emailTemplateEntity = emailTemplateRepository.findByName("MIDLIFE");
        } else {
            emailTemplateEntity = emailTemplateRepository.findByName("DARK_DAYS");
        }

        emailTemplateEntity.ifPresent(t -> {
            try {
                log.info("Sending email.....");
                MimeMessage message = javaMailSender.createMimeMessage();
                MimeMessageHelper helper;
                helper = new MimeMessageHelper(message, true);
                helper.setTo(userEntity.getEmail());
                helper.setSubject("Welcome - Enviro 365");
                helper.setText(t.getContent(), true);
                javaMailSender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
                throw new SystemException("Could not send email");
            }
        });
    }
}
