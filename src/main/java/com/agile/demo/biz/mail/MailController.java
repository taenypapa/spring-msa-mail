package com.agile.demo.biz.mail;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/api/mails")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class MailController {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @GetMapping
    public ResponseEntity<?> get(){

        MailEntity entity = MailEntity.builder().build();
        return ResponseEntity.ok().body(entity);
    }

    @PostMapping
    public ResponseEntity<?> send() throws MessagingException {
        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(m,"UTF-8");
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo("to mail");//받는 사람 메일 주소
        mimeMessageHelper.setSubject("테스트메일");
        mimeMessageHelper.setText("메일테스트");
        mailSender.send(m);

        return ResponseEntity.accepted().build();
    }
}
