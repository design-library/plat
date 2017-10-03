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
package com.soramame.web.validator;

import java.text.ParseException;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soramame.web.model.UserModel;

/**
 * validation of User.
 * 
 * @version 1.0
 * @author Plat.
 */
@Component
public class UserValidator implements Validator {
	
	/** 0-9, a-z, A-Z, _ */
	protected static final String[] UNICODE_TABLE_AccountID = {
			"u0030","u0031","u0032","u0033","u0034","u0035","u0036","u0037","u0038","u0039",
			"u0061","u0062","u0063","u0064","u0065","u0066","u0067","u0068","u0069","u006a",
			"u006b","u006c","u006d","u006e","u006f","u0070","u0071","u0072","u0073","u0074",
			"u0075","u0076","u0077","u0078","u0079","u007a",
			"u0041","u0042","u0043","u0044","u0045","u0046","u0047","u0048","u0049","u004a",
			"u004b","u004c","u004d","u004e","u004f","u0050","u0051","u0052","u0053","u0054",
			"u0055","u0056","u0057","u0058","u0059","u005a",
			"u005f"
			};

	/** 0-9, a-z, A-Z, "#$%&'()*+,-./:;<=>?@[\]^_`{|}~! */
	protected static String[] UNICODE_TABLE_PASSWORD = {
			"u0030","u0031","u0032","u0033","u0034","u0035","u0036","u0037","u0038","u0039",
			"u0061","u0062","u0063","u0064","u0065","u0066","u0067","u0068","u0069","u006a",
			"u006b","u006c","u006d","u006e","u006f","u0070","u0071","u0072","u0073","u0074",
			"u0075","u0076","u0077","u0078","u0079","u007a",
			"u0041","u0042","u0043","u0044","u0045","u0046","u0047","u0048","u0049","u004a",
			"u004b","u004c","u004d","u004e","u004f","u0050","u0051","u0052","u0053","u0054",
			"u0055","u0056","u0057","u0058","u0059","u005a",
			"u0021","u0022","u0023","u0024","u0025","u0026","u0027","u0028","u0029","u002a",
			"u002b","u002c","u002d","u002e","u002f","u003a","u003b","u003c","u003d","u003e",
			"u003f","u0040","u005b","u005c","u005d","u005e","u005f","u0060","u007b","u007c",
			"u007d","u007e"
			};

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		UserModel userModel = (UserModel)target;
		
		if ("register".equals(userModel.getVerb())) {
			
			userNameValidate(userModel.getUserName(), errors);
			
		}
		
		passwordValidate(userModel.getPassword(), errors);
		
		birthDayValidate(userModel.getBirthDay(), errors);

		emailValidate(userModel.getEmail(), errors);
			
		liveCodeValidate(userModel.getLiveCode(), errors);
			
	}
	
	private void userNameValidate(String userName, Errors errors) {
		
		if(StringUtils.isBlank(userName)) {
			errors.rejectValue("userName", "user.message.user_name.required");
			return;
			
		}
		if(userName.length() > 32) {
			errors.rejectValue("userName", "user.message.user_name.length");
			return;
			
		}
		int len = userName.length();
		for(int i = 0;i < len;i++) {
			char c = userName.charAt(i);
			String targetCode = "u00" + Integer.toString((int)c, 16);
			// a-z, A-Z, 0-9, _
			if(Arrays.asList(UNICODE_TABLE_AccountID).contains(targetCode)) {
				;
				
			} else {
				errors.rejectValue("userName", "user.message.user_name.formal");
				return;
				
			}
			
		}
		return;
		
	}
	
	private void passwordValidate(String password, Errors errors) {
		if(StringUtils.isBlank(password)) {
			errors.rejectValue("password", "user.message.password.required");
			return;
			
		}
		if(password.length() > 24) {
			errors.rejectValue("password", "user.message.password.maxlength");
			return;
			
		}
		int len = password.length();
		for(int i = 0;i < len;i++) {
			char c = password.charAt(i);
			String targetCode = "u00" + Integer.toString((int)c, 16);
			// 0-9, a-z, A-Z, "#$%&'()*+,-./:;<=>?@[\]^_`{|}~!
			if(Arrays.asList(UNICODE_TABLE_PASSWORD).contains(targetCode)) {
				;
				
			} else {
				errors.rejectValue("password", "user.message.password.formal");
				return;
				
			}
			
		}
		return;
		
	}

	private void birthDayValidate(String birthDay, Errors errors)  {
		
		if(StringUtils.isBlank(birthDay)) {
			errors.rejectValue("birthDay", "user.message.birth_day.required");
			return;
			
		}
		
		try {
			DateUtils.parseDateStrictly(birthDay, new String[] {"yyyy/MM/dd"});
			
		} catch (ParseException e) {
			errors.rejectValue("birthDay", "user.message.birth_day.formal");
			return;
			
		}
		
	}
	
	private void emailValidate(String email, Errors errors) {
		
		if(StringUtils.isBlank(email)) {
			errors.rejectValue("email", "user.message.email.required");
			return;
			
		}
		if(email.length() > 256) {
			errors.rejectValue("email", "user.message.email.maxlength");
			return;
			
		}
		EmailValidator emailValidator = EmailValidator.getInstance();
		if(!emailValidator.isValid(email)) {
			errors.rejectValue("email", "user.message.email.formal");
			return;
			
		}
		
	}
	
	private void liveCodeValidate(String liveCode, Errors errors) {
		
		if(StringUtils.isBlank(liveCode)) {
			errors.rejectValue("email", "user.message.live_code.required");
			return;
			
		}
		
	}

}
