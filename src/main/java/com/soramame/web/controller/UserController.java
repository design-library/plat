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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.AuthenticationService;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.exception.ControllerException;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.UserAccessCounterModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.model.UserScoutCounterModel;
import com.soramame.web.util.ApplicationUtil;
import com.soramame.web.util.MessageUtil;
import com.soramame.web.util.UserAccessCounterComparator;
import com.soramame.web.util.UserScoutCounterComparator;
import com.soramame.web.validator.UserValidator;

/**
 * UserController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class UserController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationUtil applicationUtil;
	
	@Autowired
	private MessageUtil messageUtil;

    /**
	 * Input of users.
     * @param model
     * @param request
     * @param locale
     * @return
     * @throws ControllerException
     */
	@RequestMapping(value = "/users/new", method = GET)
	public String toRegister(
    		Model model,
    		HttpServletRequest request,
    		Locale locale
    		) {
		
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		List<LocationMstModel> locationModelList = 
				(List<LocationMstModel>) context.getAttribute("application.list.location");
		
    	String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("user.message.register.information", null, locale);
    	model.addAttribute("messages_type", "I");
    	model.addAttribute("messages", messages);
		
		model.addAttribute("locationModelList", locationModelList);
		model.addAttribute("pageStatus", "register");
		
		return "users";
		
	}
	
	/**
	 * Input of users.
	 * @param userName
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}/edit", method = GET)
	public String toUpdate(
    		@PathVariable("userName") String userName,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
		
		HttpSession session = request.getSession(false);
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
			
		}
		
		UserBaseDto param = new UserBaseDto();
		param.setUserName(userName);
		UserBaseDto userBaseDto = userService.getUserByUserName(param);
		UserModel userModel = new UserModel();
		BeanUtils.copyProperties(userModel, userBaseDto);
		model.addAttribute("userModel", userModel);
		
		ServletContext context = session.getServletContext();
		List<LocationMstModel> locationModelList = 
				(List<LocationMstModel>) context.getAttribute("application.list.location");
		List<LocationMstModel> locationList = 
				applicationUtil.getLiveModel(locationModelList, userModel.getLiveCode());
		model.addAttribute("locationModelList", locationList);

		String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("user.message.update.information", null, locale);
    	model.addAttribute("messages_type", "I");
    	model.addAttribute("messages", messages);

		model.addAttribute("pageStatus", "update");

		return "users";
		
	}
	
	/**
	 * Registration of users.
	 * @param usersModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/users", method = POST)
	public String doRegister(
    		@Valid UserModel userModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
		
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		
		// validate.
		UserValidator userValidator = new UserValidator();
		userModel.setVerb("register");
		userValidator.validate(userModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
    		List<LocationMstModel> locationModelList = 
    				(List<LocationMstModel>) context.getAttribute("application.list.location");
    		model.addAttribute("locationModelList", locationModelList);
    		model.addAttribute("pageStatus", "register");
    		
    		return "users";
			
		}
		
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		UserBaseDto userBaseDto = new UserBaseDto();
		BeanUtils.copyProperties(userBaseDto, userModel);
		userBaseDto.setRegisterName(userModel.getUserName());
		userBaseDto.setUpdateName(userModel.getUserName());
		int count = 0;
		String[] messages = new String[1];
		try {
			count = userService.addUser(userBaseDto);
			
		} catch (BusinessException e) {
			if (e.getCause().getClass() == org.springframework.mail.MailSendException.class) {
				messages[0] = messageUtil.getMessage("user.message.sendmail.fail", null, locale);
	    		attr.addFlashAttribute("messages_type", "W");
	    		attr.addFlashAttribute("messages", messages);
	    		
	    		return "redirect:/messages";
	    		
			}
			if (e.getCause().getClass() == com.soramame.web.exception.DuplicateException.class) {
				messages[0] = messageUtil.getMessage("user.message.register.duplicate_error", null, locale);
				model.addAttribute("messages_type", "W");
	    		model.addAttribute("messages", messages);
	    		
	    		List<LocationMstModel> locationModelList = 
	    				(List<LocationMstModel>) context.getAttribute("application.list.location");
	    		model.addAttribute("locationModelList", locationModelList);
	    		model.addAttribute("pageStatus", "register");
	    		
	    		return "users";
	    		
			}
			
		}
		if (count == 0) {
			messages[0] = messageUtil.getMessage("user.message.register.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("user.message.register.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);
		
		return "redirect:/messages";
		
	}
	
	/**
	 * Update of users.
	 * @param userName
	 * @param playerModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}", params="_method=put", method = POST)
	public String doUpdate(
    		@PathVariable("userName") String userName, 
    		@Valid UserModel userModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
		
		HttpSession session = request.getSession(false);
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		// validate.
		UserValidator userValidator = new UserValidator();
		userModel.setVerb("update");
		userValidator.validate(userModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			model.addAttribute("userModel", userModel);
			
			ServletContext context = session.getServletContext();
			List<LocationMstModel> locationModelList = 
					(List<LocationMstModel>) context.getAttribute("application.list.location");
			List<LocationMstModel> locationList = 
					applicationUtil.getLiveModel(locationModelList, userModel.getLiveCode());
			model.addAttribute("locationModelList", locationList);
			
			model.addAttribute("pageStatus", "update");

			return "users";
			
		}
		
		// update user.
		UserBaseDto userBaseDto = new UserBaseDto();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(userBaseDto, userModel);
		int count = userService.updateUser(userBaseDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("user.message.update.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("user.message.update.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);

		return "redirect:/users/" + userName + "/edit";
		
	}
	
	/**
	 * Delete of users.
	 * @param userName
	 * @param playerModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}", params="_method=delete", method = POST)
	public String doDelete(
    		@PathVariable("userName") String userName, 
    		@Valid UserModel playerModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException {
		
		HttpSession session = request.getSession(false);
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		String[] messages = new String[1];
		UserBaseDto user = new UserBaseDto();
		user.setUserName(playerModel.getUserName());
		user.setPassword(playerModel.getPassword());
		ServletContext context = session.getServletContext();
		if (!authenticationService.authenticate(user)) {
			messages[0] = messageUtil.getMessage("user.message.authentication.fail", null, locale);
			model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			model.addAttribute("userModel", playerModel);
			
			List<LocationMstModel> locationModelList = 
					(List<LocationMstModel>) context.getAttribute("application.list.location");
			List<LocationMstModel> locationList = 
					applicationUtil.getLiveModel(locationModelList, playerModel.getLiveCode());
			model.addAttribute("locationModelList", locationList);
			
			model.addAttribute("pageStatus", "update");

			return "users";
    		
		} else {
		
			int count = 0;
			UserBaseDto userBaseDto = new UserBaseDto();
			userBaseDto.setUserName(userName);
			count = userService.deleteUser(userBaseDto);
			if (count < 1) {
				messages[0] = messageUtil.getMessage("user.message.delete.fail", null, locale);
	    		attr.addFlashAttribute("messages_type", "W");
	    		
			} else {
				
				// remove user_access_counter map.
				Map<String, UserAccessCounterModel> userAccessCounterMap = 
						(Map<String, UserAccessCounterModel>)context.getAttribute("application.map.user_access_counter");
				userAccessCounterMap.remove(userName);
				
				// make top5 user_access_counter list.
				List<UserAccessCounterModel> userAccessCounterList = new ArrayList<UserAccessCounterModel>();
				Set userNameKeySet = userAccessCounterMap.keySet();
				Iterator userNameIte = userNameKeySet.iterator();
				while(userNameIte.hasNext()) {
					String userNameKey = (String) userNameIte.next();
					userAccessCounterList.add(userAccessCounterMap.get(userNameKey));
				}
				Collections.sort(userAccessCounterList, new UserAccessCounterComparator());
				
				int i = 0;
				List<UserAccessCounterModel> userAccessTop5List = new ArrayList<UserAccessCounterModel>();
				for (UserAccessCounterModel userAccessCounterModel : userAccessCounterList) {
					userAccessTop5List.add(userAccessCounterModel);
					if (i == 4) {
						break;
					}
				}
				context.setAttribute("application.list.user_access_top5", userAccessTop5List);
				
				
				// remove user_scout_counter map.
				Map<String, UserScoutCounterModel> userScoutCounterMap = 
						(Map<String, UserScoutCounterModel>)context.getAttribute("application.map.user_scout_counter");
				userScoutCounterMap.remove(userName);
				
				// make top5 user_scout_counter list.
				List<UserScoutCounterModel> userScoutCounterList = new ArrayList<UserScoutCounterModel>();
				userNameKeySet = userScoutCounterMap.keySet();
				userNameIte = userNameKeySet.iterator();
				while(userNameIte.hasNext()) {
					String userNameKey = (String) userNameIte.next();
					userScoutCounterList.add(userScoutCounterMap.get(userNameKey));
				}
				Collections.sort(userScoutCounterList, new UserScoutCounterComparator());
				
				int j = 0;
				List<UserScoutCounterModel> userScoutTop5List = new ArrayList<UserScoutCounterModel>();
				for (UserScoutCounterModel userScoutCounterModel : userScoutCounterList) {
					userScoutTop5List.add(userScoutCounterModel);
					if (j == 4) {
						break;
					}
				}
				context.setAttribute("application.list.user_scout_top5", userScoutTop5List);
				
				
				messages[0] = messageUtil.getMessage("user.message.delete.complete", null, locale);
	    		attr.addFlashAttribute("messages_type", "userDelete");
	    		
			}
			attr.addFlashAttribute("messages", messages);
	
			return "redirect:/messages";
			
		}
		
	}

}
