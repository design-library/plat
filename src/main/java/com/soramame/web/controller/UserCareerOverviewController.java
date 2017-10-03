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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.soramame.web.biz.dto.UserCareerOverviewDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.exception.ControllerException;
import com.soramame.web.exception.NumberingException;
import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.UserCareerOverviewModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.util.ApplicationUtil;
import com.soramame.web.util.MessageUtil;
import com.soramame.web.validator.UserCareerOverviewValidator;

/**
 * UserCareerOverviewController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class UserCareerOverviewController {
	
	@Autowired
	private ApplicationUtil applicationUtil;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private UserService userService;

	/**
	 * get careers of players list.
	 * @param userName
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws ControllerException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
    @RequestMapping(value = "/users/{userName}/careers", method = GET)
    public String getList(
    		@PathVariable("userName") String userName, 
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException, IllegalAccessException, InvocationTargetException, BusinessException {
		
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

		UserBaseDto userBaseDtoParam = new UserBaseDto();
		userBaseDtoParam.setUserName(userName);
		UserBaseDto userBaseDto = userService.getUserByUserName(userBaseDtoParam);
		UserModel userBaseModel = new UserModel();
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		BeanUtils.copyProperties(userBaseModel, userBaseDto);
		
		ServletContext context = session.getServletContext();
		Map<String, LocationMstModel> locationModelMap = 
				(Map<String, LocationMstModel>) context.getAttribute("application.map.location");
		Map<String, AgeGroupMstModel> ageGroupModelMap = 
				(Map<String, AgeGroupMstModel>) context.getAttribute("application.map.age_group");

		userBaseModel.setAgeGroup(applicationUtil.convToAgeGrp(
				ageGroupModelMap, userBaseDto.getBirthDay()));
		userBaseModel.setLiveName(applicationUtil.convToLiveName(
				locationModelMap, userBaseDto.getLiveCode()));

		
		UserCareerOverviewDto userCareerOverviewDtoParam = new UserCareerOverviewDto();
		userCareerOverviewDtoParam.setUserName(sessionUserName);
		List<UserCareerOverviewDto> userCareerOverviewDtoList = 
				userService.getUserCareerOverviewByUserName(userCareerOverviewDtoParam);
		
		Map<String, JobCategoryMstModel> jobCategoryModelMap = 
				(Map<String, JobCategoryMstModel>) context.getAttribute("application.map.job_category");
    	List<UserCareerOverviewModel> userCareerOverviewModelList = new ArrayList<UserCareerOverviewModel>();
    	for (UserCareerOverviewDto userCareerOverviewDto : userCareerOverviewDtoList) {
    		UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
    		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    		BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
    		userCareerOverviewModel.setJobCategoryName(applicationUtil.convToJobCategoryName(
    				jobCategoryModelMap, userCareerOverviewModel.getJobCategoryCode()));
    		userCareerOverviewModelList.add(userCareerOverviewModel);
    		
    	}
    	
    	model.addAttribute("playerModel", userBaseModel);
		model.addAttribute("userCareerOverviewModelList", userCareerOverviewModelList);

		String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("user_career_overview_my_list.message.information", null, locale);
    	model.addAttribute("messages_type", "I");
    	model.addAttribute("messages", messages);
		
    	return "users_careers_my_list";
    	
    }

    /**
	 * Input of players careers.
	 * @param userName
     * @param model
     * @param request
     * @param locale
     * @return
     * @throws ControllerException
     */
	@RequestMapping(value = "/users/{userName}/careers/new", method = GET)
	public String toRegister(
    		@PathVariable("userName") String userName, 
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException {
		
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
		
		ServletContext context = session.getServletContext();
		List<JobCategoryMstModel> jobCategoryModelList = 
				(List<JobCategoryMstModel>) context.getAttribute("application.list.job_category");
		
		model.addAttribute("jobCategoryModelList", jobCategoryModelList);

		String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("user_career_overview.message.register.information", null, locale);
    	model.addAttribute("messages_type", "I");
    	model.addAttribute("messages", messages);

		model.addAttribute("pageStatus", "register");

		return "users_careers";
		
	}
	
	/**
	 * Input of players careers.
	 * @param userName
	 * @param careerId
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws ControllerException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}/careers/{careerId}/edit", method = GET)
	public String toUpdate(
    		@PathVariable("userName") String userName,
    		@PathVariable("careerId") String careerId,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException, IllegalAccessException, InvocationTargetException, BusinessException {
		
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
		
		UserCareerOverviewDto userCareerOverviewParam = new UserCareerOverviewDto();
		userCareerOverviewParam.setCareerId(careerId);
		UserCareerOverviewDto userCareerOverviewDto = userService.getUserCareerOverviewByCareerId(userCareerOverviewParam);
		UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
		ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
		BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
		model.addAttribute("userCareerOverviewModel", userCareerOverviewModel);
		
		ServletContext context = session.getServletContext();
		List<JobCategoryMstModel> jobCategoryModelList = 
				(List<JobCategoryMstModel>) context.getAttribute("application.list.job_category");
		List<JobCategoryMstModel> jobCategoryList = 
				applicationUtil.getJobCategoryModel(jobCategoryModelList, userCareerOverviewModel.getJobCategoryCode());
		model.addAttribute("jobCategoryModelList", jobCategoryList);
		
		String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("user_career_overview.message.update.information", null, locale);
    	model.addAttribute("messages_type", "I");
    	model.addAttribute("messages", messages);

		model.addAttribute("pageStatus", "update");

		return "users_careers";
	}
	
	/**
	 * Registration of players careers.
	 * @param userCareerOverviewModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws ControllerException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NumberingException 
	 * @throws ParseException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}/careers", method = POST)
	public String doRegister(
    		@PathVariable("userName") String userName,
    		@Valid UserCareerOverviewModel userCareerOverviewModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException, IllegalAccessException, InvocationTargetException, ParseException, BusinessException {

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
		
		
		UserCareerOverviewValidator userCareerOverviewValidator = new UserCareerOverviewValidator();
		userCareerOverviewValidator.validate(userCareerOverviewModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			model.addAttribute("userCareerOverviewModel", userCareerOverviewModel);
			
			ServletContext context = session.getServletContext();
			List<JobCategoryMstModel> jobCategoryModelList = 
					(List<JobCategoryMstModel>) context.getAttribute("application.list.job_category");
			List<JobCategoryMstModel> jobCategoryList = 
					applicationUtil.getJobCategoryModel(jobCategoryModelList, userCareerOverviewModel.getJobCategoryCode());
			model.addAttribute("jobCategoryModelList", jobCategoryList);
			
			model.addAttribute("pageStatus", "register");

			return "users_careers";
			
		}
		
		
		UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
		ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
		BeanUtils.copyProperties(userCareerOverviewDto, userCareerOverviewModel);
		userCareerOverviewDto.setRegisterName(sessionUserName);
		userCareerOverviewDto.setUpdateName(sessionUserName);
		int count = userService.addUserCareerOverview(userCareerOverviewDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("user_career_overview.message.register.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("user_career_overview.message.register.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);

		return "redirect:/messages";
		
	}
	
	/**
	 * Update of players careers.
	 * @param userName
	 * @param playerCareerOverviewModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws ControllerException
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}/careers/{careerId}", params="_method=put", method = POST)
	public String doUpdate(
    		@PathVariable("userName") String userName, 
    		@PathVariable("careerId") String careerId,
    		@Valid UserCareerOverviewModel userCareerOverviewModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException, IllegalAccessException, InvocationTargetException, BusinessException {
		
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
		
		
		UserCareerOverviewValidator userCareerOverviewValidator = new UserCareerOverviewValidator();
		userCareerOverviewValidator.validate(userCareerOverviewModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			model.addAttribute("userCareerOverviewModel", userCareerOverviewModel);
			
			ServletContext context = session.getServletContext();
			List<JobCategoryMstModel> jobCategoryModelList = 
					(List<JobCategoryMstModel>) context.getAttribute("application.list.job_category");
			List<JobCategoryMstModel> jobCategoryList = 
					applicationUtil.getJobCategoryModel(jobCategoryModelList, userCareerOverviewModel.getJobCategoryCode());
			model.addAttribute("jobCategoryList", jobCategoryList);
			
			model.addAttribute("pageStatus", "update");

			return "users_careers";
			
		}

		UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
		ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
		BeanUtils.copyProperties(userCareerOverviewDto, userCareerOverviewModel);
		userCareerOverviewDto.setUpdateName(userName);
		userCareerOverviewDto.setUpdateDate(new Date());
		int count = userService.updateUserCareerOverview(userCareerOverviewDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("user_career_overview.message.update.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("user_career_overview.message.update.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);

		return "redirect:/users/" + userName + "/careers/" + careerId + "/edit";
		
	}
	
	/**
	 * Delete of players careers.
	 * @param userName
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws ControllerException
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/users/{userName}/careers/{careerId}", params="_method=delete", method = POST)
	public String doDelete(
    		@PathVariable("userName") String userName, 
    		@PathVariable("careerId") String careerId,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws ControllerException, BusinessException {
		
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
		
		UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
		userCareerOverviewDto.setCareerId(careerId);
		int count = userService.deleteUserCareerOverview(userCareerOverviewDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("user_career_overview.message.delete.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("user_career_overview.message.delete.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);

		return "redirect:/messages";
		
	}
	
}
