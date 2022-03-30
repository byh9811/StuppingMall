package com.nerds.stuppingmall.service.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailSendService {
    private final JavaMailSender javaMailSender;

    @Value("spring.mail.username")
    private String senderId;

    private MimeMessage createMessage(String key, String receiver) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, receiver);
        message.setSubject("Stupping Mall 이메일 인증입니다.");

        String msg = "";
        msg+= "<div style='margin:100px;'>";
        msg+= "<h1> 안녕하세요 Stupping Mall입니다. </h1>";
        msg+= "<br>";
        msg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        msg+= "<br>";
        msg+= "<p>감사합니다!<p>";
        msg+= "<br>";
        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msg+= "<div style='font-size:130%'>";
        msg+= "CODE : <strong>";
        msg+= key + "</strong><div><br/> ";
        msg+= "</div>";

        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress(senderId+"@naver.com", "Stupping Mall"));

        return message;
    }

    private String createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for(int i=0; i<6; i++)
            key.append(Integer.toString(rnd.nextInt()));

        return key.toString();
    }

    public String sendMessage(String receiver) throws Exception {
        String key = createKey();
        MimeMessage message = createMessage(key, receiver);
        javaMailSender.send(message);

        return key;
    }
}
