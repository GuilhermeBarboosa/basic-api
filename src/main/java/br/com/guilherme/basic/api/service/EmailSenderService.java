package br.com.guilherme.basic.api.service;

import lombok.var;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String message) throws MessagingException {
        var mensagem = this.mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(mensagem, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message, true);

//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setTo(to);
//        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(message);

        this.mailSender.send(mensagem);
    }
}
