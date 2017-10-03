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
package com.soramame.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.AuthenticationService;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.model.LoginModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.util.MessageUtil;
import com.soramame.web.validator.LoginValidation;

/**
 * LoginController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class LoginController {
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * login.
	 * @param userName
	 * @param password
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/login", method = POST)
	public String login(
			@Valid LoginModel loginModel,
			BindingResult result,
			RedirectAttributes attr, 
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
		
		LoginValidation loginValidation = new LoginValidation();
		loginValidation.validate(loginModel, result);
		if (result.hasErrors()) {
			String[] messages = messageUtil.getErrorMessages(result, locale);
			attr.addFlashAttribute("messages_type", "W");
			attr.addFlashAttribute("messages", messages);
			attr.addFlashAttribute("loginModel", loginModel);
			
			return "redirect:/messages";
			
		}
		
		UserBaseDto user = new UserBaseDto();
		user.setUserName(loginModel.getUserName());
		user.setPassword(loginModel.getPassword());
		if (authenticationService.authenticate(user)) {
			
			HttpSession currentSession = request.getSession(true);
			currentSession.invalidate();
			
			HttpSession session = request.getSession(true);
			ServletContext context = session.getServletContext();
			Map<String, UserModel> authenticatedUserMap = 
					(Map<String, UserModel>) context.getAttribute("application.map.authenticated_user");
			
			UserBaseDto userBaseDto = userService.getUserByUserName(user);
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(userModel, userBaseDto);
			
			session.setAttribute("sessionUserModel", userModel);
			authenticatedUserMap.put(userModel.getUserName(), userModel);
			context.setAttribute("application.map.authenticated_user", authenticatedUserMap);
			
			return "redirect:/index";
			
		} else {
			String[] messages = new String[1];
			messages[0] = messageUtil.getMessage("login.message.login.fail", null, locale);
			attr.addFlashAttribute("messages_type", "W");
			attr.addFlashAttribute("messages", messages);
			attr.addFlashAttribute("loginModel", loginModel);
			
			return "redirect:/messages";
			
		}
		
	}

	/**
	 * logout.
	 * @param userName
	 * @param password
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/toLogout", method = GET)
	public String toLogout(
			RedirectAttributes attr, 
    		HttpServletRequest request,
    		Locale locale
    		) {
		
    	String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("logout.message.logout", null, locale);
		attr.addFlashAttribute("messages", messages);
		attr.addFlashAttribute("messages_type", "logout");
		
		return "redirect:/logout";
		
	}
	
	/**
	 * to logout.
	 * @return
	 */
	@RequestMapping(value = "/logout", method = GET)
	public String logout() {
		
		return "messages";
		
	}

}
