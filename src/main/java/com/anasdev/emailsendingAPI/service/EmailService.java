package com.anasdev.emailsendingAPI.service;

import com.anasdev.emailsendingAPI.dto.EmailRequest;
import com.anasdev.emailsendingAPI.model.Email;
import com.anasdev.emailsendingAPI.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;

    public String sendEmail(EmailRequest emailRequest){
        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(emailRequest.getTo());
            simpleMailMessage.setSubject(emailRequest.getSubject());
            simpleMailMessage.setText(emailRequest.getBody());
            javaMailSender.send(simpleMailMessage); //wheere we are sending email


            //SAVE TO DATABSE
            Email emailToSave = new Email();
            emailToSave.setRecipient(emailRequest.getTo());
            emailToSave.setSubject(emailRequest.getSubject());
            emailToSave.setBody(emailRequest.getBody());
            emailRepository.save(emailToSave); //where we are saving to database
            return "Email successfully sent";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
