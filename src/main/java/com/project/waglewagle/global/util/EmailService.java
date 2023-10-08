package com.project.waglewagle.global.util;


import java.util.Random;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;


    public static final String EPASSWORD = createKey();
    private MimeMessage createMessage(String to)throws Exception{
        System.out.println("수신자 : "+ to);
        System.out.println("인증 번호 : "+ EPASSWORD);

        MimeMessage message = javaMailSender.createMimeMessage();


        message.addRecipients(MimeMessage.RecipientType.TO, to); //보내는 대상
        message.setSubject("[와글와글] 비밀번호 재발급 메일 "); //제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h3> 와글와글 임시비밀번호 안내  </h3>";
        msgg+= "<br>";
        msgg+= "<p>안녕하세요 와글와글입니다.<p>";
        msgg+= "<br>";
        msgg+= "<p>아래 발급된 비밀번호를 통해 로그인해주세요 감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:sans-serif';>";
        msgg+= "<h3 style='color:blue;'> 임시 비밀번호 </h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "<strong>";
        msgg+= EPASSWORD +"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html"); // 내용
        message.setFrom(new InternetAddress("waglewagle.unius@gmail.com","wagle-wagle"));// 보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }
        return key.toString();
    }


    public String sendSimpleMessage(String to)throws Exception {
        MimeMessage message = createMessage(to);
        try{
            javaMailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return EPASSWORD;
    }

}
