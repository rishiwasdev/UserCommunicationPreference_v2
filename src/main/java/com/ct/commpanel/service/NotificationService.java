package com.ct.commpanel.service;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ct.commpanel.controller.NotificationController;
import com.ct.commpanel.model.Communication;
import com.ct.commpanel.model.User;

@Service( )
public class NotificationService {
	private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

	/** TO BE IMPLEMENTED */
	public void sendCommunication( User user, List< Communication > communications, @Valid String msg ) {
		logger.info("*************************************************");
		logger.info("SENDING MESSAGE TO USER, ACCORDING TO PREFERENCES");
		logger.info(user.toString());
		logger.info("MESSAGE:\t" + msg);
	}
}