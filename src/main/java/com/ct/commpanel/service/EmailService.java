package com.ct.commpanel.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ct.commpanel.controller.EmailController;
import com.ct.commpanel.model.Email;
import com.ct.commpanel.model.User;
import com.ct.commpanel.repository.CommunicationRepository;
import com.ct.commpanel.repository.EmailRepository;

@Service
public class EmailService {
	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	@Autowired
	private EmailRepository emailRepository;
	@Autowired
	private CommunicationRepository commRepository;

	public List< Email > fetchUnsentEmails( User user ) {
		List< Email > emails = emailRepository.findByUserId(user.getId());
		Iterator< Email > itr = emails.iterator();
		while ( itr.hasNext() ) {
			Email email = itr.next();
			if( email.getSent() )
				itr.remove();
		}
		return emails;
	}

	@Transactional
	public void setSentStatus( List< Email > emails ) {
		for( Email email : emails )
			email.setSent(true);
		emailRepository.saveAll(emails);
	}

	/** TO BE IMPLEMENTED */
	public boolean sendEmails( User user, List< Email > emails ) {
		for( int i = 0; i < emails.size(); i++ )
			logger.info( (i + 1) + ".\t" + emails.get(i).getBody());
		setSentStatus(emails);
		return true;
	}
}