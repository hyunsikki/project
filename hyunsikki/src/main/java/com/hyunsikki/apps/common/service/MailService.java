package com.hyunsikki.apps.common.service;

import com.hyunsikki.apps.common.model.Mail;



public interface MailService {

	boolean sendMail(Mail mail);
}