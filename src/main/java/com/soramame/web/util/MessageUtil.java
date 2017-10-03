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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * MessageUtil.
 * 
 * @author Plat.
 * @version 1.0
 */
@Component
public class MessageUtil {

	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code, Object[] args, String defaultMessage,
			Locale locale) {
		
		return messageSource.getMessage(code, args, defaultMessage, locale);
		
	}

	public String getMessage(String code, Object[] args, Locale locale)
			throws NoSuchMessageException {
		
		return messageSource.getMessage(code, args, locale);
		
	}

	public String getMessage(MessageSourceResolvable resolvable, Locale locale)
			throws NoSuchMessageException {
		
		return messageSource.getMessage(resolvable, locale);
		
	}
	
	/**
	 * 
	 * @param result
	 * @param locale
	 * @return
	 */
	public String[] getErrorMessages(BindingResult result, Locale locale) {
    	
    	List<FieldError> fieldErrors = result.getFieldErrors();
		List<String> messageList = new ArrayList<String>(fieldErrors.size());
    	for(FieldError fieldError : fieldErrors) {
    		messageList.add(messageSource.getMessage(fieldError.getCode(), null, locale));
    	}
    	return messageList.toArray(new String[0]);
    	
    }


}
