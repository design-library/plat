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

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soramame.web.model.LoginModel;

/**
 * validation of account.
 * 
 * @version 1.0
 * @author Plat.
 */
@Component
public class LoginValidation implements Validator {

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
	public boolean supports(Class<?> arg0) {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		
		LoginModel loginModel = (LoginModel)target;
		
		userNameValidate(loginModel.getUserName(), errors);
		
		passwordValidate(loginModel.getPassword(), errors);
		
	}
	
	private void userNameValidate(String userName, Errors errors) {
		
		if(StringUtils.isBlank(userName)) {
			errors.rejectValue("userName", "login.message.userName.required");
			return;
			
		}
		return;

	}
	
	private void passwordValidate(String password, Errors errors) {
		if(StringUtils.isBlank(password)) {
			errors.rejectValue("password", "login.message.password.required");
			return;
			
		}
		
	}
	
}
