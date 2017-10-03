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
import java.util.Date;
import java.util.HashMap;
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
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soramame.web.biz.dto.ProjectDto;
import com.soramame.web.biz.dto.TransmissionHistoryDto;
import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.dto.UserCareerOverviewDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.HistoryService;
import com.soramame.web.biz.service.ProjectService;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.ParticipationHopeCounterModel;
import com.soramame.web.model.ProjectAccessCounterModel;
import com.soramame.web.model.ProjectModel;
import com.soramame.web.model.RecruitmentAreaMstModel;
import com.soramame.web.model.UserAccessCounterModel;
import com.soramame.web.model.UserCareerOverviewModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.model.UserScoutCounterModel;
import com.soramame.web.util.ApplicationUtil;
import com.soramame.web.util.MessageUtil;
import com.soramame.web.util.ParticipationHopeCounterComparator;
import com.soramame.web.util.ProjectAccessCounterComparator;
import com.soramame.web.util.UserAccessCounterComparator;
import com.soramame.web.util.UserScoutCounterComparator;
import com.soramame.web.validator.ProjectValidator;

/**
 * ProjectController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class ProjectController {
	
	@Autowired
	private ApplicationUtil applicationUtil;
	
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private HistoryService historyService;
	
	private static String DEFAULT_PAGE_NUMBER = "1";
	
	private static String DEFAULT_PAGE_SIZE = "10";

	private static String PARTICIPATE_TYPE = "02";
	
	// 3days interval.
	private static long PARTICIPATE_INTERVAL = 3L * 12 * 60 * 60 * 1000;
	
	/**
	 * get projects list.
	 * @param pageNumber
	 * @param model
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
    @RequestMapping(value = "/projects", method = GET)
    public String getAllList(
    		@RequestParam("pageNumber") String pageNumber,
    		Model model,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException, IllegalAccessException, InvocationTargetException {
    	
		String[] messages = new String[1];
		int pageNumberI = 0;
    	try {
    		Integer.parseInt(pageNumber);
        	pageNumberI = NumberUtils.toInt(pageNumber, 1);
    		
    	} catch (NumberFormatException e) {
    		// invalid
    		messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
    		model.addAttribute("messages", messages);
			model.addAttribute("messages_type", "W");
    		pageNumberI = NumberUtils.toInt(DEFAULT_PAGE_NUMBER, 1);
    		
    	}
    	int pageSizeI = NumberUtils.toInt(DEFAULT_PAGE_SIZE, 10);
    	
    	// request param check.
    	if ( pageNumberI * pageSizeI < 1) {
    		// invalid
    		messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
    		model.addAttribute("messages", messages);
			model.addAttribute("messages_type", "W");
    		pageNumberI = NumberUtils.toInt(DEFAULT_PAGE_NUMBER, 1);
    		
    	}
    	
    	List<ProjectDto> projectDtoList = projectService.getProjectOfAll();
		/////////////////////////////////////////////////////
		// page scroll.  start
		/////////////////////////////////////////////////////
    	int accountSize = projectDtoList.size();
    	int page = 0;
    	if (accountSize != pageSizeI) {
        	page = accountSize / pageSizeI;
        	
    	}
    	
    	// request param check.
    	if(((page + 1) < pageNumberI)) {
    		// invalid
    		messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
    		model.addAttribute("messages", messages);
			model.addAttribute("messages_type", "W");
    		pageNumberI = NumberUtils.toInt(DEFAULT_PAGE_NUMBER, 1);
    		
    	}
    	
    	// post
    	int stPos = 0;
    	int edPos = 0;
    	if (accountSize < pageSizeI) {
    		// 1page
    		edPos = accountSize - 1;
    		
    	} else {
    		// 2page later
    		if(page < pageNumberI) {
    			// last page
    			stPos = page * pageSizeI;
        		edPos = accountSize - 1;
    			
    		} else {
    			stPos = (pageNumberI - 1) * pageSizeI;
        		edPos = stPos + (pageSizeI - 1);
    			
    		}
    		
    	}
		/////////////////////////////////////////////////////
		// -- page scroll.  end
		/////////////////////////////////////////////////////

    	
		/////////////////////////////////////////////////////
		// make model.  start
		/////////////////////////////////////////////////////
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		
		// user model.
		Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = 
				(Map<String, RecruitmentAreaMstModel>) context.getAttribute("application.map.recruitment_area");
    	
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		for (int i = stPos;i <= edPos;i++) {
			ProjectDto projectDto = projectDtoList.get(i);
			ProjectModel projectModel = new ProjectModel();
			BeanUtils.copyProperties(projectModel, projectDto);
    		projectModel.setRecruitmentAreaName(
					applicationUtil.convToRecruitmentAreaName(
							recruitmentAreaModelMap, projectModel.getRecruitmentAreaCode()));

			projectModelList.add(projectModel);
			
		}

		// get project access top5.
		List<ProjectAccessCounterModel> projectAccessTop5List = 
				(List<ProjectAccessCounterModel>) context.getAttribute("application.list.project_access_top5");
		Collections.sort(projectAccessTop5List, new ProjectAccessCounterComparator());
		model.addAttribute("projectAccessTop5List", projectAccessTop5List);
		
		// get participation hope top5.
		List<ParticipationHopeCounterModel> participationHopeTop5List = 
				(List<ParticipationHopeCounterModel>) context.getAttribute("application.list.participation_hope_top5");
		Collections.sort(participationHopeTop5List, new ParticipationHopeCounterComparator());
		model.addAttribute("participationHopeTop5List", participationHopeTop5List);
		
		// set model.
		model.addAttribute("projectModelList", projectModelList);
		model.addAttribute("amountPageNumber", page + 1);
		model.addAttribute("currentPageNumber", pageNumberI);
		/////////////////////////////////////////////////////
		// -- make model.  end
		/////////////////////////////////////////////////////

    	return "projects_list";
    	
    }
    
    /**
     * get projects list.
     * @param userName
     * @param model
     * @param attr
     * @param request
     * @param locale
     * @return
     * @throws BusinessException
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @RequestMapping(value = "/users/{userName}/projects", method = GET)
    public String getList(
    		@PathVariable("userName") String userName, 
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException, IllegalAccessException, InvocationTargetException {

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
		Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = 
				(Map<String, RecruitmentAreaMstModel>) context.getAttribute("application.map.recruitment_area");

		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setUserName(sessionUserName);
		List<ProjectDto> projectDtoList = projectService.getProjectByUserName(projectDtoParam);
		List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
		for (ProjectDto projectDto : projectDtoList) {
			ProjectModel projectModel = new ProjectModel();
    		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    		BeanUtils.copyProperties(projectModel, projectDto);

    		projectModel.setRecruitmentAreaName(
					applicationUtil.convToRecruitmentAreaName(
							recruitmentAreaModelMap, projectModel.getRecruitmentAreaCode()));
    		
    		projectModelList.add(projectModel);
    		
		}
		model.addAttribute("projectModelList", projectModelList);
		
    	return "projects_my_list";
    	
    }
    
    /**
     * get projects information.
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
    @RequestMapping(value = "/projects/{projectId}", method = GET)
    public String getInfo(
    		@PathVariable("projectId") String projectId, 
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
    	
    	boolean loginSessionState = true;
    	
		/////////////////////////////////////////////////////
		// no login. start.
		/////////////////////////////////////////////////////
		HttpSession session = request.getSession(false);
		if (session == null) {
			session = request.getSession(true);
			loginSessionState = false;
			
		} else {
			UserModel userModel = (UserModel) session.getAttribute("sessionUserModel");
			if (userModel == null) {
				loginSessionState = false;
				
			}
			
		}
		/////////////////////////////////////////////////////
		// no login. end.
		/////////////////////////////////////////////////////

		ServletContext context = session.getServletContext();
		Map<String, JobCategoryMstModel> jobCategoryModelMap = 
				(Map<String, JobCategoryMstModel>) context.getAttribute("application.map.job_category");
		Map<String, LocationMstModel> locationModelMap = 
				(Map<String, LocationMstModel>) context.getAttribute("application.map.location");
		Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = 
				(Map<String, RecruitmentAreaMstModel>) context.getAttribute("application.map.recruitment_area");
		Map<String, AgeGroupMstModel> ageGroupModelMap = 
				(Map<String, AgeGroupMstModel>) context.getAttribute("application.map.age_group");
    	
		/////////////////////////////////////////////////////
		// make project model.  start
		/////////////////////////////////////////////////////
		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setProjectId(projectId);
		ProjectDto projectDto = projectService.getProjectByProjectId(projectDtoParam);
		if (projectDto == null) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("projects.message.get", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		ProjectModel projectModel = new ProjectModel();
    	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    	BeanUtils.copyProperties(projectModel, projectDto);
		projectModel.setRecruitmentAreaName(
    			applicationUtil.convToRecruitmentAreaName(recruitmentAreaModelMap, projectModel.getRecruitmentAreaCode()));
		/////////////////////////////////////////////////////
		// make project model.  end
		/////////////////////////////////////////////////////
		
		if (!loginSessionState) {
	    	String[] messages = new String[1];
	    	messages[0] = messageUtil.getMessage("projects.message.pubinfo", null, locale);
	    	model.addAttribute("messages", messages);
	    	model.addAttribute("messages_type", "I");
			
			model.addAttribute("projectModel", projectModel);
			model.addAttribute("loginSessionState", loginSessionState);

			return "projects_info";

		}
		
		/////////////////////////////////////////////////////
		// make manager model.  start
		/////////////////////////////////////////////////////
		// get user.
		UserBaseDto userBaseDtoParam = new UserBaseDto();
		userBaseDtoParam.setUserName(projectDto.getUserName());
		UserBaseDto userBaseDto = userService.getUserByUserName(userBaseDtoParam);
		if (userBaseDto == null) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("user.message.get", null, locale);
    		attr.addFlashAttribute("messages", messages);
    		attr.addFlashAttribute("messages_type", "W");
    		return "redirect:/messages";
    		
		}
    	UserModel managerModel = new UserModel();
    	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    	BeanUtils.copyProperties(managerModel, userBaseDto);
    	managerModel.setAgeGroup(
    			applicationUtil.convToAgeGrp(ageGroupModelMap, managerModel.getBirthDay()));
    	managerModel.setLiveName(
    			applicationUtil.convToLiveName(locationModelMap, managerModel.getLiveCode()));
		/////////////////////////////////////////////////////
		// make manager model.  end
		/////////////////////////////////////////////////////    	
    	
    	
		/////////////////////////////////////////////////////
		// make UserCareerOverview model.  start
		/////////////////////////////////////////////////////    	
    	UserCareerOverviewDto userCareerOverviewDtoParam = new UserCareerOverviewDto();
    	userCareerOverviewDtoParam.setUserName(projectDto.getUserName());
    	List<UserCareerOverviewDto> userCareerOverviewDtoList =
    			userService.getUserCareerOverviewByUserName(userCareerOverviewDtoParam);
    	List<UserCareerOverviewModel> userCareerOverviewModelList = new ArrayList<UserCareerOverviewModel>();
    	for (UserCareerOverviewDto userCareerOverviewDto : userCareerOverviewDtoList) {
    		UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
    		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    		BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
    		userCareerOverviewModel.setJobCategoryName(applicationUtil.convToJobCategoryName(
    				jobCategoryModelMap, userCareerOverviewModel.getJobCategoryCode()));
    		userCareerOverviewModelList.add(userCareerOverviewModel);
    		
    	}
    	managerModel.setUserCareerOverviewList(userCareerOverviewModelList);
		/////////////////////////////////////////////////////
		// make UserCareerOverview model.  end
		/////////////////////////////////////////////////////
    	
    	
		/////////////////////////////////////////////////////
		// make player model.  start
		/////////////////////////////////////////////////////
    	UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String userName = sessionUserModel.getUserName();
		
		// make user_career_overview.
		UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
		UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
		List<UserCareerOverviewModel> userCareerOverviewModelWrap = new ArrayList<UserCareerOverviewModel>(1);
		userCareerOverviewDto.setUserName(userName);
		userCareerOverviewDto = userService.getUserCareerOverviewByUserNameAndPeriodFromMin(userCareerOverviewDto);
		if (userCareerOverviewDto != null) {
			ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
			BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
			userCareerOverviewModel.setJobCategoryName(applicationUtil.convToJobCategoryName(
							jobCategoryModelMap, userCareerOverviewDto.getJobCategoryCode()));
		}
		userCareerOverviewModelWrap.add(userCareerOverviewModel);
		// make user_model.
		UserModel playerModel = new UserModel();
		BeanUtils.copyProperties(playerModel, sessionUserModel);
		playerModel.setAgeGroup(applicationUtil.convToAgeGrp(
						ageGroupModelMap, sessionUserModel.getBirthDay()));
		playerModel.setLiveName(applicationUtil.convToLiveName(
				locationModelMap, sessionUserModel.getLiveCode()));
		playerModel.setUserCareerOverviewList(userCareerOverviewModelWrap);
		
		/////////////////////////////////////////////////////
		// make player model.  end
		/////////////////////////////////////////////////////
		
		/////////////////////////////////////////////////////
		// access count. startd
		// preserve count to application.
		/////////////////////////////////////////////////////
		Map<String, ProjectAccessCounterModel> projectAccessCounterMap = 
				(Map<String, ProjectAccessCounterModel>) context.getAttribute("application.map.project_access_counter");
		ProjectAccessCounterModel projectAccessCounterModel = 
				(ProjectAccessCounterModel) projectAccessCounterMap.get(projectId);
		Map<String, String> sessionCounter = 
				(Map<String, String>) session.getAttribute("session.map.project_access_counter");
		if (sessionCounter.containsKey(projectId)) {
			// no count.
			;
			
		} else {
			// count.
			sessionCounter.put(projectId, "1");
			session.setAttribute("session.map.project_access_counter", sessionCounter);
			
			// accessCounter
			if (projectAccessCounterModel == null) {
				projectAccessCounterModel = new ProjectAccessCounterModel();
				
			}
			projectAccessCounterModel.setProjectId(projectId);
			projectAccessCounterModel.setTitle(projectDto.getTitle());
			projectAccessCounterModel.setProjectAccessCount(projectAccessCounterModel.getProjectAccessCount() + 1);
			projectAccessCounterModel.setAccessDate(new Date(System.currentTimeMillis()));
			
		}
		// preserve count to application.
		projectAccessCounterMap.put(projectId, projectAccessCounterModel);
		context.setAttribute("application.map.project_access_counter", projectAccessCounterMap);
		
		// add or remove top five list
		int cnt = 0;
		List<ProjectAccessCounterModel> projectAccessTop5ModelList = 
				(List<ProjectAccessCounterModel>) context.getAttribute("application.list.project_access_top5");
		for (ProjectAccessCounterModel mModel : projectAccessTop5ModelList) {
			if (projectId.equals(mModel.getProjectId())) {
				projectAccessTop5ModelList.remove(cnt);
				break;
				
			}
			cnt++;
			
		}
		projectAccessTop5ModelList.add(projectAccessCounterModel);
		context.setAttribute("application.list.project_access_top5", projectAccessTop5ModelList);

		projectModel.setProjectAccessCounter(projectAccessCounterModel);
		/////////////////////////////////////////////////////
		// -- access count. end
		/////////////////////////////////////////////////////

		
		/////////////////////////////////////////////////////
		// participate count. start
		/////////////////////////////////////////////////////
		Map<String, ParticipationHopeCounterModel> participationHopeCounterMap = 
				(Map<String, ParticipationHopeCounterModel>) context.getAttribute("application.map.participation_hope_counter");
		ParticipationHopeCounterModel participationHopeCounterModel = new ParticipationHopeCounterModel();
		if (participationHopeCounterMap.containsKey(projectId)) {
			participationHopeCounterModel = participationHopeCounterMap.get(projectId);
			
		} else {
			participationHopeCounterModel.setParticipationHopeCount(0);
			
		}
		projectModel.setParticipationHopeCounter(participationHopeCounterModel);

		/////////////////////////////////////////////////////
		// -- access count. end
		/////////////////////////////////////////////////////
		
    	String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("projects.message.info", null, locale);
    	model.addAttribute("messages", messages);
    	model.addAttribute("messages_type", "I");
		
		model.addAttribute("projectModel", projectModel);
    	model.addAttribute("managerModel", managerModel);
    	model.addAttribute("playerModel", playerModel);
		model.addAttribute("loginSessionState", loginSessionState);

		return "projects_info";
    	
    }

    /**
	 * Input of projects.
     * @param model
     * @param request
     * @param locale
     * @return
     * @throws BusinessException
     */
	@RequestMapping(value = "/users/{userName}/projects/new", method = GET)
	public String toRegister(
    		@PathVariable("userName") String userName, 
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

		ServletContext context = session.getServletContext();
		List<RecruitmentAreaMstModel> recruitmentAreaModelList = 
				(List<RecruitmentAreaMstModel>) context.getAttribute("application.list.recruitment_area");
		
		model.addAttribute("recruitmentAreaModelList", recruitmentAreaModelList);
		model.addAttribute("pageStatus", "register");

		return "projects";
		
	}
	
	/**
	 * Input of projects.
	 * @param projectId
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/users/{userName}/projects/{projectId}/edit", method = GET)
	public String toUpdate(
    		@PathVariable("userName") String userName,
    		@PathVariable("projectId") String projectId,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException, IllegalAccessException, InvocationTargetException {
		
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
		
		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setProjectId(projectId);
		ProjectDto projectDto = projectService.getProjectByProjectId(projectDtoParam);
		if (projectDto == null) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("projects.message.get", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		ProjectModel projectModel = new ProjectModel();
    	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    	BeanUtils.copyProperties(projectModel, projectDto);

		ServletContext context = session.getServletContext();
		List<RecruitmentAreaMstModel> recruitmentAreaModelList = 
				(List<RecruitmentAreaMstModel>) context.getAttribute("application.list.recruitment_area");
		List<RecruitmentAreaMstModel> recruitmentAreaList = 
				applicationUtil.getRecruitmentAreaModel(recruitmentAreaModelList, projectModel.getRecruitmentAreaCode());
		
		model.addAttribute("recruitmentAreaModelList", recruitmentAreaList);
		model.addAttribute("projectModel", projectModel);
		model.addAttribute("pageStatus", "update");

		return "projects";
		
	}
			
	/**
	 * Registration of projects.
	 * @param projectModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/users/{userName}/projects/new", method = POST)
	public String doRegister(
    		@PathVariable("userName") String userName,
    		@Valid ProjectModel projectModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException, IllegalAccessException, InvocationTargetException {
		
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		//validate.
		ProjectValidator projectValidator = new ProjectValidator();
		projectModel.setVerb("regisgter");
		projectValidator.validate(projectModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			List<RecruitmentAreaMstModel> recruitmentAreaModelList = 
					(List<RecruitmentAreaMstModel>) context.getAttribute("application.list.recruitment_area");
    		model.addAttribute("recruitmentAreaModelList", recruitmentAreaModelList);
    		model.addAttribute("pageStatus", "register");
    		
    		return "projects";

		}
		
		ConvertUtils.register(new DateConverter(null), java.util.Date.class);
		ProjectDto projectDto = new ProjectDto();
		BeanUtils.copyProperties(projectDto, projectModel);
		projectDto.setUserName(sessionUserModel.getUserName());
		projectDto.setRegisterName(sessionUserModel.getUserName());
		projectDto.setUpdateName(sessionUserModel.getUserName());
		int count = projectService.addProject(projectDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("projects.message.register.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("projects.message.register.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
			
		}

		attr.addFlashAttribute("messages", messages);
		
		return "redirect:/messages";
		
	}
	
	/**
	 * Update of projects.
	 * @param userName
	 * @param projectModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/users/{userName}/projects/{projectId}", params="_method=put", method = POST)
	public String doUpdate(
    		@PathVariable("userName") String userName,
    		@PathVariable("projectId") String projectId, 
    		@Valid ProjectModel projectModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException, IllegalAccessException, InvocationTargetException {
		
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		//validate.
		ProjectValidator projectValidator = new ProjectValidator();
		projectModel.setVerb("update");
		projectValidator.validate(projectModel, result);
		if (result.hasErrors()) {
        	String[] messages = messageUtil.getErrorMessages(result, locale);
        	model.addAttribute("messages_type", "W");
			model.addAttribute("messages", messages);
			List<RecruitmentAreaMstModel> recruitmentAreaModelList = 
					(List<RecruitmentAreaMstModel>) context.getAttribute("application.list.recruitment_area");
    		model.addAttribute("recruitmentAreaModelList", recruitmentAreaModelList);
    		model.addAttribute("pageStatus", "update");
    		
    		return "projects";

		}
		
		ProjectDto projectDto = new ProjectDto();
		ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
		BeanUtils.copyProperties(projectDto, projectModel);
		projectDto.setUserName(userName);
		projectDto.setUpdateName(userName);
		int count = projectService.updateProject(projectDto);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("projects.message.update.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			messages[0] = messageUtil.getMessage("projects.message.update.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);
		
		return "redirect:/users/" + userName + "/projects/" + projectId + "/edit";
		
	}
	
	/**
	 * Delete of projects.
	 * @param projectId
	 * @param projectModel
	 * @param result
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/users/{userName}/projects/{projectId}", params="_method=delete", method = POST)
	public String doDelete(
    		@PathVariable("userName") String userName,
    		@PathVariable("projectId") String projectId, 
    		@Valid ProjectModel projectModel,
    		BindingResult result,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request,
    		Locale locale
    		) throws BusinessException {
		
		HttpSession session = request.getSession(false);
		UserModel sessionUserModel = (UserModel) session.getAttribute("sessionUserModel");
		String sessionUserName = sessionUserModel.getUserName();
		
		ServletContext context = session.getServletContext();
		if (!userName.equals(sessionUserName)) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("common.message.requestparam.invalid", null, locale);
			attr.addFlashAttribute("messages_type", "W");
    		attr.addFlashAttribute("messages", messages);
			return "redirect:/messages";
		}
		
		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setProjectId(projectId);
		int count = projectService.deleteProject(projectDtoParam);
		String[] messages = new String[1];
		if (count < 1) {
			messages[0] = messageUtil.getMessage("projects.message.delete.fail", null, locale);
    		attr.addFlashAttribute("messages_type", "W");
    		
		} else {
			// remove project_access_counter map.
			Map<String, ProjectAccessCounterModel> projectAccessCounterMap = 
					(Map<String, ProjectAccessCounterModel>)context.getAttribute("application.map.project_access_counter");
			projectAccessCounterMap.remove(projectId);
			
			// make top5 project_access_counter list.
			List<ProjectAccessCounterModel> projectAccessCounterList = new ArrayList<ProjectAccessCounterModel>();
			Set projectIdKeySet = projectAccessCounterMap.keySet();
			Iterator projectIdIte = projectIdKeySet.iterator();
			while(projectIdIte.hasNext()) {
				String projectIdKey = (String) projectIdIte.next();
				projectAccessCounterList.add(projectAccessCounterMap.get(projectIdKey));
			}
			Collections.sort(projectAccessCounterList, new ProjectAccessCounterComparator());
			
			int i = 0;
			List<ProjectAccessCounterModel> projectAccessTop5List = new ArrayList<ProjectAccessCounterModel>();
			for (ProjectAccessCounterModel projectAccessCounterModel : projectAccessCounterList) {
				projectAccessTop5List.add(projectAccessCounterModel);
				if (i == 4) {
					break;
				}
			}
			context.setAttribute("application.list.project_access_top5", projectAccessTop5List);

			
			// remove participation_hope_counter map.
			Map<String, ParticipationHopeCounterModel> participationHopeCounterMap = 
					(Map<String, ParticipationHopeCounterModel>)context.getAttribute("application.map.participation_hope_counter");
			participationHopeCounterMap.remove(projectId);
			
			// make top5 user_scout_counter list.
			List<ParticipationHopeCounterModel> participationHopeCounterList = new ArrayList<ParticipationHopeCounterModel>();
			projectIdKeySet = participationHopeCounterMap.keySet();
			projectIdIte = projectIdKeySet.iterator();
			while(projectIdIte.hasNext()) {
				String projectIdKey = (String) projectIdIte.next();
				participationHopeCounterList.add(participationHopeCounterMap.get(projectIdKey));
			}
			Collections.sort(participationHopeCounterList, new ParticipationHopeCounterComparator());
			
			int j = 0;
			List<ParticipationHopeCounterModel> participationHopeTop5List = new ArrayList<ParticipationHopeCounterModel>();
			for (ParticipationHopeCounterModel participationHopeCounterModel : participationHopeCounterList) {
				participationHopeTop5List.add(participationHopeCounterModel);
				if (j == 4) {
					break;
				}
			}
			context.setAttribute("application.list.participation_hope_top5", participationHopeTop5List);
			
			messages[0] = messageUtil.getMessage("projects.message.delete.complete", null, locale);
    		attr.addFlashAttribute("messages_type", "I");
    		
		}
		attr.addFlashAttribute("messages", messages);
		
		return "redirect:/messages";
		
	}
	
	/**
	 * scout projects.
	 * @param projectId
	 * @param userId
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/projects/{projectId}", method = POST)
	public String participate(
    		@PathVariable("projectId") String projectId, 
    		UserModel plyaerModel,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request, 
    		Locale locale
    		) throws BusinessException {
		
		// player check.
		List<UserCareerOverviewModel> userCareerOverviewModel = plyaerModel.getUserCareerOverviewList();
		if (userCareerOverviewModel == null || userCareerOverviewModel.size() < 1) {
	        	String[] messages = new String[1];
	        	messages[0] = messageUtil.getMessage("projects.message.participate.shortofinfo", null, locale);
	    		attr.addFlashAttribute("messages", messages);
	    		attr.addAttribute("messages_type", "W");
	    		return "redirect:/messages";
    		
		} else {
			
			if (userCareerOverviewModel.get(0).getOverView() == null || 
					"".equals(userCareerOverviewModel.get(0).getOverView()) || 
					userCareerOverviewModel.get(0).getPeriodFrom() == null || 
							"".equals(userCareerOverviewModel.get(0).getPeriodFrom()) ||
							userCareerOverviewModel.get(0).getAffiliationName() == null || 
									"".equals(userCareerOverviewModel.get(0).getAffiliationName())) {
				
	        	String[] messages = new String[1];
	        	messages[0] = messageUtil.getMessage("projects.message.participate.shortofinfo", null, locale);
	    		attr.addFlashAttribute("messages", messages);
	    		attr.addAttribute("messages_type", "W");
	    		return "redirect:/messages";
	    		
			}
			
		}
		
		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setProjectId(projectId);
		ProjectDto projectDto = projectService.getProjectByProjectId(projectDtoParam);
		String userName = projectDto.getUserName();
		
		// history check.
		TransmissionHistoryDto transmissionHistoryDto = new TransmissionHistoryDto();
		transmissionHistoryDto.setTransmissionType(PARTICIPATE_TYPE);
		transmissionHistoryDto.setSendersName(plyaerModel.getUserName());
		transmissionHistoryDto.setRecipientName(userName);
		long diff = historyService.getDiff(transmissionHistoryDto);
		if (diff > 0 && PARTICIPATE_INTERVAL > diff) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("projects.message.participate.interval", null, locale);
    		attr.addFlashAttribute("messages", messages);
    		attr.addAttribute("messages_type", "W");
    		return "redirect:/messages";

		}
		
		UserBaseDto userBaseDtoParam = new UserBaseDto();
		userBaseDtoParam.setUserName(userName);
		UserBaseDto managerDto = userService.getUserByUserName(userBaseDtoParam);
		
    	String server = messageUtil.getMessage("common.server", null, locale);
    	String protcol = messageUtil.getMessage("common.protcol", null, locale);
    	String fromAddress = messageUtil.getMessage("common.mail.from.address", null, locale);
    	String[] toAddress = new String[1];
    	toAddress[0] = managerDto.getEmail();
    	String[] ccAddress = null;
    	String[] bccAddress= new String[1];
    	bccAddress[0] = messageUtil.getMessage("common.mail.bcc.address", null, locale);
    	String subject = messageUtil.getMessage("projects.mail.participate.subject", null, locale);
    	String template = "project_participate_email_template.vm";
    	
    	Map<String, String> tmplMap = new HashMap<String, String>();
    	tmplMap.put("pjName", projectDto.getTitle());
    	tmplMap.put("managerName", managerDto.getUserName());
    	tmplMap.put("memberName", plyaerModel.getUserName());
    	tmplMap.put("email", plyaerModel.getEmail());
    	tmplMap.put("ageGroup", plyaerModel.getAgeGroup());
    	tmplMap.put("liveName", plyaerModel.getLiveName());
    	tmplMap.put("jobCategoryName", 
    			plyaerModel.getUserCareerOverviewList().get(0).getJobCategoryName());
    	tmplMap.put("overView", plyaerModel.getUserCareerOverviewList().get(0).getOverView());
    	tmplMap.put("userInfoUrl", protcol + server + request.getContextPath() + "/players/" + plyaerModel.getUserName());
    	
    	projectService.sendMail("02", plyaerModel.getUserName(), managerDto.getUserName(), fromAddress, toAddress, ccAddress, bccAddress, subject, template, tmplMap);
    	String[] messages = new String[1];
    	
		messages[0] = messageUtil.getMessage("projects.message.mail.complete", null, locale);
		attr.addFlashAttribute("messages_type", "I");
		attr.addFlashAttribute("messages", messages);

		
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		Map<String, ParticipationHopeCounterModel> participationHopeCounterMap = 
				(Map<String, ParticipationHopeCounterModel>) context.getAttribute("application.map.participation_hope_counter");
		ParticipationHopeCounterModel participationHopeCounterModel = 
				(ParticipationHopeCounterModel) participationHopeCounterMap.get(projectId);
		Map<String, String> sessionCounter = (Map<String, String>) session.getAttribute("session.map.participation_hope_counter");
		if (sessionCounter.containsKey(projectId)) {
			// no count.
			;
			
		} else {
			// count.
			sessionCounter.put(projectId, "1");
			session.setAttribute("session.map.participation_hope_counter", sessionCounter);
			
			// accessCounter
			if (participationHopeCounterModel == null) {
				participationHopeCounterModel = new ParticipationHopeCounterModel();
				
			}
			participationHopeCounterModel.setProjectId(projectId);
			participationHopeCounterModel.setTitle(projectDto.getTitle());
			participationHopeCounterModel.setParticipationHopeCount(
					participationHopeCounterModel.getParticipationHopeCount() + 1);
			participationHopeCounterModel.setParticipationHopeDate(
					new Date(System.currentTimeMillis()));
			
		}
		// preserve count to application.
		participationHopeCounterMap.put(projectId, participationHopeCounterModel);
		context.setAttribute("application.map.participation_hope_counter", participationHopeCounterMap);
		
		// add or remove top five list
		int cnt = 0;
		List<ParticipationHopeCounterModel> participationHopeTop5ModelList = 
				(List<ParticipationHopeCounterModel>) context.getAttribute("application.list.participation_hope_top5");
		for (ParticipationHopeCounterModel phcModel : participationHopeTop5ModelList) {
			if (projectId.equals(phcModel.getProjectId())) {
				participationHopeTop5ModelList.remove(cnt);
				break;
				
			}
			cnt++;
			
		}
		participationHopeTop5ModelList.add(participationHopeCounterModel);
		context.setAttribute("application.list.participation_hope_top5", participationHopeTop5ModelList);
		/////////////////////////////////////////////////////
		// -- participation hope count. end
		/////////////////////////////////////////////////////		
		
		return "redirect:/messages";
		
	}

}
