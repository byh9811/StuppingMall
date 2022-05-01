package com.nerds.stuppingmall.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderId;

    private MimeMessage createMessage(String key, String receiver) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, receiver);
        message.setSubject("Stupping Mall 이메일 인증입니다.");

        StringBuilder msg = new StringBuilder();
        msg.append("<div style='margin:100px;'>")
                .append("<h1> 안녕하세요 Stupping Mall입니다. </h1>")
                .append("<br>")
                .append("<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>")
                .append("<br>")
                .append("<p>감사합니다!<p>")
                .append("<br>")
                .append("<div align='center' style='border:1px solid black; font-family:verdana';>")
                .append("<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>")
                .append("<div style='font-size:130%'>")
                .append("CODE : <strong>")
                .append(key)
                .append("</strong><div><br/></div>");

        message.setText(msg.toString(), "utf-8", "html");
        message.setFrom(new InternetAddress(senderId, "Stupping Mall"));

        return message;
    }

    private String createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for(int i=0; i<6; i++)
            key.append(rnd.nextInt(10));

        return key.toString();
    }

    public String sendMessage(String receiver) throws Exception {
        String key = createKey();;
        MimeMessage message = createMessage(key, receiver);

        javaMailSender.send(message);

        return key;
    }
}
