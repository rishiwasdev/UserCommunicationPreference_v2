package com.ct.commpanel.service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ct.commpanel.controller.EmailController;
import com.ct.commpanel.exception.ResourceNotFoundException;
import com.ct.commpanel.model.Communication;
import com.ct.commpanel.model.Email;
import com.ct.commpanel.model.SMS;
import com.ct.commpanel.model.User;
import com.ct.commpanel.repository.CommunicationRepository;

@Component
public class SchedulerService {
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
	@Autowired
	private EmailService emailService;
	@Autowired
	private SmsService smsService;
	@Autowired
	private CommunicationRepository commRepository;

	@Scheduled( fixedRate = 10000 )
	public void execute() {
		List< Communication > communications = commRepository.findAll();
		// .parallelStream().filter(comm -> comm.getTypeOfComm() == CommunicationType.EMAIL).iterator();
		for( Communication comm : communications ) {
			User user = comm.getUser();
			switch( comm.getTypeOfComm() ) {
				case EMAIL:
					List< Email > emails = emailService.fetchUnsentEmails(user);
					if( emails.size() > 0 ) {
						logger.info("*************************************************");
						logger.info("SENDING 'EMAILS' TO:" + user.getEmail());
						emailService.sendEmails(user, emails);
					}
					break;
				case SMS:
					List< SMS > messages = smsService.fetchUnsentSms(user);
					if( messages.size() > 0 ) {
						logger.info("*************************************************");
						logger.info("SENDING 'SMSs' TO:" + user.getContact());
						smsService.sendSms(user, messages);
					}
					break;
				default:
					try {
						throw new ResourceNotFoundException("UNSUPPORTED  OPERATION FOR: " + comm.getTypeOfComm());
					} catch( ResourceNotFoundException ex ) {
						ex.printStackTrace();
					}
			}
		}
	}
}