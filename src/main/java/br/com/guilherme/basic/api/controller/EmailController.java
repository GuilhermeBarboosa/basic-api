package br.com.guilherme.basic.api.controller;

import br.com.guilherme.basic.api.service.EmailSenderService;
import br.com.guilherme.basic.api.service.JwtService;
import br.com.guilherme.basic.model.entity.User;
import br.com.guilherme.basic.model.output.EmailOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
public class EmailController {
    @Autowired
    private final EmailSenderService emailSenderService;
    @Autowired
    private JwtService jwtService;
    @Value("${urlAtivacao}")
    String uriAtivacao;
    @Value("${urlRecuperacao}")
    String uriRecuperacao;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }

    public ResponseEntity enviaEmail(EmailOutput dados) throws MessagingException {
        this.emailSenderService.sendEmail(dados.getEmailUsuario(), dados.getAssunto(), dados.getMensagem());
        return ResponseEntity.ok("Success");
    }

//    EmailOutput createEmailInscricao(InscricaoWithArquivosOutput inscricaoWithArquivosOutput) {
//        LocalDateTime dataInscricao = inscricaoWithArquivosOutput.getInscricaoOutput().getCreated();
//        DateTimeFormatter formato = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//        String dataFormatada = dataInscricao.format(formato);
//
//        String mensagem = "<body style=\"margin: 0; padding: 0;\">"+
//                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#eeeeee\">"+
//                "<tr>"+
//                "<td style=\"padding: 32px 0;\">"+
//                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">"+
//                "<tr>"+
//                "<td align=\"center\" bgcolor=\"#12295d\" style=\"padding: 32px 32px; color: #ffffff; border-collapse: collapse; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">"+
//                "<b>FETI</b>"+
//                "</td>"+
//                "</tr>"+
//                "<tr>"+
//                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">"+
//                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
//                "<tr>"+
//                "<td align=\"center\" style=\"color: #12295d; font-family: Arial, sans-serif; font-size: 24px;\">"+
//                "</td>"+
//                "</tr>"+
//                "<tr>"+
//                "<td style=\"padding: 0 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">"+
//                "<p>Prezado candidato, " + inscricaoWithArquivosOutput.getInscricaoOutput().getUser() +" </p>"+
//                "<p>Dados de inscrição do processo seletivo:</p>"+
//                "</td>"+
//                "</tr>"+
//                "</table>"+
//                "</td>"+
//                "</tr>"+
//                "<tr>"+
//                "<td bgcolor=\"#12295d\" style=\"padding: 32px 32px;\">"+
//                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
//                "<tr>"+
//                "<td style=\"font-family: Arial, sans-serif; font-size: 16px; line-height: 20px; color: #ffffff\">"+
//                "<p>Inscrição: " + inscricaoWithArquivosOutput.getInscricaoOutput().getId() +" </p>" +
//                "         <p>Edital:" + inscricaoWithArquivosOutput.getInscricaoOutput().getEdital() +" </p>" +
//                "         <p>Cargo:" + inscricaoWithArquivosOutput.getInscricaoOutput().getFuncao() +" </p>" +
//                "         <p>Data:" + dataFormatada +" </p>" +
//                "</td>"+
//                "</tr>"+
//                "</table>"+
//                "</td>"+
//                "</tr>"+
//                "<tr>"+
//                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">"+
//                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
//                "<tr>"+
//                "<td style=\"padding: 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 14px; line-height: 20px;\">"+
//                "Por favor, não responda este e-mail. Para maiores informações, entre em contato com a FETI."+
//                "</td>"+
//                "</tr>"+
//                "</table>"+
//                "</td>"+
//                "</tr>"+
//                "</table>"+
//                "</td>"+
//                "</tr>"+
//                "</table>"+
//                "</body>";
//
//        EmailOutput emailOutput = new EmailOutput();
//        emailOutput.setEmailUsuario(inscricaoWithArquivosOutput.getInscricaoOutput().getEmail());
//        emailOutput.setAssunto("Inscrição " + inscricaoWithArquivosOutput.getInscricaoOutput().getEdital() + " - " + inscricaoWithArquivosOutput.getInscricaoOutput().getFuncao());
//        emailOutput.setMensagem(mensagem);
//        return emailOutput;
//    }

    EmailOutput createEmailUser(User createdUser) {
        String jwt = jwtService.gerarTokenEmail(createdUser);
        String mensagem = "<body style=\"margin: 0; padding: 0;\">"+
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#eeeeee\">"+
                "<tr>"+
                "<td style=\"padding: 32px 0;\">"+
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">"+
                "<tr>"+
                "<td align=\"center\" bgcolor=\"#12295d\" style=\"padding: 32px 32px; color: #ffffff; border-collapse: collapse; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">"+
                "<b>NOME DO SISTEMA</b>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">"+
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                "<tr>"+
                "<td align=\"center\" style=\"color: #12295d; font-family: Arial, sans-serif; font-size: 24px;\">"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td style=\"padding: 0 0 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">"+
                "<p>Prezado candidato, " + createdUser.getName() +" </p>"+
                "<p>Clique no link abaixo para ativar a sua conta.</p>"+
                "</td>"+
                "</tr>"+
                "</table>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor=\"#12295d\" style=\"padding: 32px 32px;\">"+
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                "<tr>"+
                "<td style=\"font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">"+
                "<a href=\"" + this.uriAtivacao + jwt + "\"><font color=\"#ffffff\">" + this.uriAtivacao + jwt + "</font></a>"+
                "</td>"+
                "</tr>"+
                "</table>"+
                "</td>"+
                "</tr>"+
                "<tr>"+
                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">"+
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"+
                "<tr>"+
                "<td style=\"padding: 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 14px; line-height: 20px;\">"+
                "Por favor, não responda este e-mail. Para maiores informações, entre em contato com a FETI."+
                "</td>"+
                "</tr>"+
                "</table>"+
                "</td>"+
                "</tr>"+
                "</table>"+
                "</td>"+
                "</tr>"+
                "</table>"+
                "</body>";

        EmailOutput emailOutput = new EmailOutput();
        emailOutput.setEmailUsuario(createdUser.getEmail());
        emailOutput.setAssunto("Ativação de conta");
        emailOutput.setMensagem(mensagem);
        return emailOutput;
    }

    EmailOutput createEmailRecuperarSenha(Optional<User> user) {
        String jwt = jwtService.gerarTokenEmail(user.get());

        String mensagem = "<div style=\"margin: 0; padding: 0;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" bgcolor=\"#eeeeee\">" +
                "<tr>" +
                "<td style=\"padding: 32px 0;\">" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse;\">" +
                "<tr>" +
                "<td align=\"center\" bgcolor=\"#12295d\" style=\"padding: 32px 32px; color: #ffffff; border-collapse: collapse; font-size: 28px; font-weight: bold; font-family: Arial, sans-serif;\">" +
                "<b>NOME DO SISTEMA</b>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                "<tr>" +
                "<td align=\"center\" style=\"color: #12295d; font-family: Arial, sans-serif; font-size: 24px;\">" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style=\"padding: 0 0 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">" +
                "<p>Prezado candidato, " + user.get().getName() + "</p>" +
                "<p>O link abaixo irá lhe direcionar para o formulário de alteração de senha.</p>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td bgcolor=\"#12295d\" style=\"padding: 32px 32px;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                "<tr>" +
                "<td style=\"font-family: Arial, sans-serif; font-size: 16px; line-height: 20px;\">" +
                "<a href=\"" + this.uriRecuperacao + jwt + "\"><font color=\"#ffffff\">" + this.uriRecuperacao + jwt + "</font></a>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "<tr>" +
                "<td bgcolor=\"#ffffff\" style=\"padding: 32px 32px;\">" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">" +
                "<tr>" +
                "<td style=\"padding: 0 0; color: #12295d; font-family: Arial, sans-serif; font-size: 14px; line-height: 20px;\">" +
                "Por favor, não responda este e-mail. Para maiores informações, entre em contato com a FETI." +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</td>" +
                "</tr>" +
                "</table>" +
                "</div>";

        EmailOutput emailOutput = new EmailOutput();
        emailOutput.setEmailUsuario(user.get().getEmail());
        emailOutput.setAssunto("Recuperação de senha");
        emailOutput.setMensagem(mensagem);
        return emailOutput;
    }
}
