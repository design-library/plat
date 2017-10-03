/**
 * Copyright 2015 Plat.
 * 
 * This file is part of Pj Platform.
 *
 *  Pj Platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pj Platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Pj Platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soramame.web.util;

import java.io.StringWriter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.soramame.web.biz.dto.SimpleMailDto;

/**
 * mail utility.
 * 
 * @version 1.0
 * @author Plat.
 * @refer http://www.technicalkeeda.com/spring/spring-email-velocity-template-example
 */
@Component
public class SimpleMailer {
	
	private static final Logger logger = Logger.getLogger(SimpleMailer.class);
	
	private MailSender mailSender;
	
	private VelocityEngine velocityEngine;
	
	/**
	 * 
	 * @param mailSender
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	/**
	 * 
	 * @param velocityEngine
	 */
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	/**
	 * send mail.
	 * @param mail
	 * @throws ResourceNotFoundException
	 * @throws ParseErrorException
	 * @throws MethodInvocationException
	 * @throws MailException
	 */
	public void sendMail(SimpleMailDto mail) throws ResourceNotFoundException, ParseErrorException, MethodInvocationException, MailException {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(mail.getFromAddress());
		message.setTo(mail.getToAddressArray());
		message.setCc(mail.getCcAddressArray());
		message.setBcc(mail.getBccAddressArray());
		message.setSubject(mail.getSubject());
		message.setSentDate(new Date(System.currentTimeMillis()));
		
		Template template = velocityEngine.getTemplate("./templates/" + mail.getTemplateName(), "MS932");
		VelocityContext velocityContext = new VelocityContext(mail.getMailMap());
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		
		message.setText(stringWriter.toString());
		mailSender.send(message);
		
	}
	
}
