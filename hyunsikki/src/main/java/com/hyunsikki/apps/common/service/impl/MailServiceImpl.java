package com.hyunsikki.apps.common.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hyunsikki.apps.common.model.Mail;
import com.hyunsikki.apps.common.service.MailService;


@Service
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
    private JavaMailSender mailSender;

	public boolean sendMail(Mail mail) {
		
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
            messageHelper.setSubject(mail.getSubject());
            
            // 수신인 다수
            String[] toArrayResult = new String[mail.getToUser().size()];
            
            for(int i=0; i<mail.getToUser().size(); i++){
            	toArrayResult[i] =  mail.getToUser().get(i);
            	logger.debug("Send User : {}", mail.getToUser().get(i));
            }
            messageHelper.setTo(toArrayResult);
            messageHelper.setFrom(mail.getFromUser(), "Amoeba Mail Sending System");
            messageHelper.setText(mail.getText(), true);
            
            // 여러개의 파일첨부시
            if(mail.getFile()!=null){
            	for(int i=0; i< mail.getFile().size(); i++){
            		if( mail.getFile().get(i).isFile()){
            			messageHelper.addAttachment(mail.getAttcheFileName().get(i), mail.getFile().get(i));
            			logger.debug("AttcheFileName : {}", mail.getAttcheFileName().get(i));
            		}
            	}
            }
            logger.debug("Send Mail Subject : {}", mail.getSubject());
            mailSender.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.debug(" MessagingException : {}", e.getMessage());
            return false;
        } catch (Exception ex) {
        	ex.printStackTrace();
        	logger.debug(" Exception : {}", ex.getMessage());
        	 return false;
        }
		
	}
}
