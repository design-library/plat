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

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.ProjectModel;
import com.soramame.web.model.RecruitmentAreaMstModel;
import com.soramame.web.model.TypeIndustryMstModel;
import com.soramame.web.model.UserAccessCounterModel;
import com.soramame.web.model.UserCareerOverviewModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.model.UserScoutCounterModel;
import com.soramame.web.model.YearsExperienceMstModel;
import com.soramame.web.biz.dto.ProjectDto;
import com.soramame.web.biz.dto.TransmissionHistoryDto;
import com.soramame.web.biz.dto.UserCareerOverviewDto;
import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.HistoryService;
import com.soramame.web.biz.service.ProjectService;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.util.ApplicationUtil;
import com.soramame.web.util.MessageUtil;
import com.soramame.web.util.UserAccessCounterComparator;
import com.soramame.web.util.UserScoutCounterComparator;

/**
 * PlayerController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class PlayerController {
	
	@Autowired
	private ApplicationUtil applicationUtil;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private ProjectService projectService;

	private static String DEFAULT_PAGE_NUMBER = "1";
	
	private static String DEFAULT_PAGE_SIZE = "10";
	
	private static String SCOUT_TYPE = "01";
	
	// 3days interval.
	private static long SCOUT_INTERVAL = 3L * 12 * 60 * 60 * 1000;
	
	/**
	 * get players list.
	 * @param pageNumber
	 * @param model
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
    @RequestMapping(value = "/players", method = GET)
    public String getAllList(
    		@RequestParam("pageNumber") String pageNumber,
    		Model model,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
    	
		HttpSession session = request.getSession(false);
		ServletContext context = session.getServletContext();
		
		// user model.
		Map<String, JobCategoryMstModel> jobCategoryModelMap = 
				(Map<String, JobCategoryMstModel>) context.getAttribute("application.map.job_category");
		Map<String, LocationMstModel> locationModelMap = 
				(Map<String, LocationMstModel>) context.getAttribute("application.map.location");
		Map<String, AgeGroupMstModel> ageGroupModelMap = 
				(Map<String, AgeGroupMstModel>) context.getAttribute("application.map.age_group");
		Map<String, YearsExperienceMstModel> yearsExperienceModelMap = 
				(Map<String, YearsExperienceMstModel>) context.getAttribute("application.map.years_experience");
		Map<String, TypeIndustryMstModel> typeIndustryModelMap = 
				(Map<String, TypeIndustryMstModel>) context.getAttribute("application.map.type_industry");

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
    	UserBaseDto param = new UserBaseDto();
    	param.setRole("10");
    	List<UserBaseDto> userBaseDtoList = userService.getUserByRole(param);
    	
    	List<UserModel> userModelReNewList = new ArrayList<UserModel>();
    	for (UserBaseDto userBaseDto : userBaseDtoList) {
    		String userName = userBaseDto.getUserName();
    		
			// make user_career_overview.
			UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
			UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
			List<UserCareerOverviewModel> userCareerOverviewModelWrap = new ArrayList<UserCareerOverviewModel>(1);

			userCareerOverviewDto.setUserName(userName);
			userCareerOverviewDto = userService.getUserCareerOverviewByUserNameAndPeriodFromMin(userCareerOverviewDto);
			if (userCareerOverviewDto != null && 
					userCareerOverviewDto.getOverView() != null && 
					!("".equals(userCareerOverviewDto.getOverView()) &&
							userCareerOverviewDto.getJobCategoryCode() != null &&
							!("".equals(userCareerOverviewDto.getJobCategoryCode())))) {
				
				ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
				BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
				userCareerOverviewModel.setJobCategoryName(applicationUtil.convToJobCategoryName(
								jobCategoryModelMap, userCareerOverviewDto.getJobCategoryCode()));
				
				userCareerOverviewModelWrap.add(userCareerOverviewModel);
				
				// make user_model.
				UserModel userModel = new UserModel();
				BeanUtils.copyProperties(userModel, userBaseDto);
				userModel.setAgeGroup(applicationUtil.convToAgeGrp(
								ageGroupModelMap, userBaseDto.getBirthDay()));
				userModel.setLiveName(applicationUtil.convToLiveName(
								locationModelMap, userBaseDto.getLiveCode()));
				userModel.setUserCareerOverviewList(userCareerOverviewModelWrap);
				
				// make user_model.
				userModelReNewList.add(userModel);
				
			}
			
		}
    	
    	
    	
		/////////////////////////////////////////////////////
		// page scroll.  start
		/////////////////////////////////////////////////////
    	int accountSize = userModelReNewList.size();
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
		List<UserModel> playerModelList = new ArrayList<UserModel>();
		for (int i = stPos;i <= edPos;i++) {
			
			UserModel userModel = userModelReNewList.get(i);
			playerModelList.add(userModel);
			
		}

		// get user access top5.
		List<UserAccessCounterModel> userAccessTop5List = 
				(List<UserAccessCounterModel>) context.getAttribute("application.list.user_access_top5");
		Collections.sort(userAccessTop5List, new UserAccessCounterComparator());
		
		// get user scout top5.
		List<UserScoutCounterModel> userScoutTop5List = 
				(List<UserScoutCounterModel>) context.getAttribute("application.list.user_scout_top5");
		Collections.sort(userScoutTop5List, new UserScoutCounterComparator());
		
		// set model.
		model.addAttribute("playerModelList", playerModelList);
		model.addAttribute("amountPageNumber", page + 1);
		model.addAttribute("currentPageNumber", pageNumberI);
		model.addAttribute("userAccessTop5List", userAccessTop5List);
		model.addAttribute("userScoutTop5List", userScoutTop5List);
		/////////////////////////////////////////////////////
		// -- make model.  end
		/////////////////////////////////////////////////////
    	
    	return "players_list";
    	
    }
    
    /**
     * get players information.
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
    @RequestMapping(value = "/players/{userName}", method = GET)
    public String getInfo(
    		@PathVariable("userName") String userName, 
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
    	
		
		/////////////////////////////////////////////////////
		// make player model.  start
		/////////////////////////////////////////////////////
		ServletContext context = session.getServletContext();
		
		Map<String, JobCategoryMstModel> jobCategoryModelMap = 
				(Map<String, JobCategoryMstModel>) context.getAttribute("application.map.job_category");
		Map<String, LocationMstModel> locationModelMap = 
				(Map<String, LocationMstModel>) context.getAttribute("application.map.location");
		Map<String, AgeGroupMstModel> ageGroupModelMap = 
				(Map<String, AgeGroupMstModel>) context.getAttribute("application.map.age_group");
		Map<String, YearsExperienceMstModel> yearsExperienceModelMap = 
				(Map<String, YearsExperienceMstModel>) context.getAttribute("application.map.years_experience");
		Map<String, TypeIndustryMstModel> typeIndustryModelMap = 
				(Map<String, TypeIndustryMstModel>) context.getAttribute("application.map.type_industry");

		// get user.
		UserBaseDto userBaseDtoParam = new UserBaseDto();
		userBaseDtoParam.setUserName(userName);
		UserBaseDto userBaseDto = userService.getUserByUserName(userBaseDtoParam);
		if (userBaseDto == null) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("user.message.get", null, locale);
    		attr.addFlashAttribute("messages", messages);
    		attr.addAttribute("messages_type", "W");
    		return "redirect:/messages";
    		
		}
    	UserModel playerModel = new UserModel();
    	ConvertUtils.register(new DateConverter(null), java.util.Date.class);
    	BeanUtils.copyProperties(playerModel, userBaseDto);
    	
    	// make model.
    	playerModel.setAgeGroup(
    			applicationUtil.convToAgeGrp(ageGroupModelMap, playerModel.getBirthDay()));
    	playerModel.setLiveName(
    			applicationUtil.convToLiveName(locationModelMap, playerModel.getLiveCode()));
		/////////////////////////////////////////////////////
		// make player model.  end
		/////////////////////////////////////////////////////    	
    	
		/////////////////////////////////////////////////////
		// make UserCareerOverview model.  start
		/////////////////////////////////////////////////////    	
    	UserCareerOverviewDto userCareerOverviewDtoParam = new UserCareerOverviewDto();
    	userCareerOverviewDtoParam.setUserName(userName);
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
    	playerModel.setUserCareerOverviewList(userCareerOverviewModelList);
		/////////////////////////////////////////////////////
		// make UserCareerOverview model.  end
		/////////////////////////////////////////////////////
    	
    	
		/////////////////////////////////////////////////////
		// no login. start.
		/////////////////////////////////////////////////////
		if (!loginSessionState) {
	    	String[] messages = new String[1];
	    	messages[0] = messageUtil.getMessage("player.message.pubinfo", null, locale);
	    	model.addAttribute("messages", messages);
	    	model.addAttribute("messages_type", "I");
			
			model.addAttribute("playerModel", playerModel);
			model.addAttribute("loginSessionState", loginSessionState);

			return "players_info";

		}
		/////////////////////////////////////////////////////
		// no login. end.
		/////////////////////////////////////////////////////
		
    	
		/////////////////////////////////////////////////////
		// make manager model.  start
		/////////////////////////////////////////////////////
    	UserModel managerModel = (UserModel) session.getAttribute("sessionUserModel");
    	String managerUserName = managerModel.getUserName();
		// make user_career_overview.
		UserCareerOverviewDto userCareerOverviewDto = new UserCareerOverviewDto();
		UserCareerOverviewModel userCareerOverviewModel = new UserCareerOverviewModel();
		List<UserCareerOverviewModel> userCareerOverviewModelWrap = new ArrayList<UserCareerOverviewModel>(1);
		userCareerOverviewDto.setUserName(managerUserName);
		userCareerOverviewDto = userService.getUserCareerOverviewByUserNameAndPeriodFromMin(userCareerOverviewDto);
		if (userCareerOverviewDto != null) {
			ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
			BeanUtils.copyProperties(userCareerOverviewModel, userCareerOverviewDto);
			userCareerOverviewModel.setJobCategoryName(applicationUtil.convToJobCategoryName(
							jobCategoryModelMap, userCareerOverviewDto.getJobCategoryCode()));
		}
		userCareerOverviewModelWrap.add(userCareerOverviewModel);
		managerModel.setUserCareerOverviewList(userCareerOverviewModelWrap);
    	managerModel.setAgeGroup(
    			applicationUtil.convToAgeGrp(ageGroupModelMap, managerModel.getBirthDay()));
    	managerModel.setLiveName(
    			applicationUtil.convToLiveName(locationModelMap, managerModel.getLiveCode()));
    	model.addAttribute("managerModel", managerModel);
		/////////////////////////////////////////////////////
		// make manager model.  end
		/////////////////////////////////////////////////////
    	
    	
		/////////////////////////////////////////////////////
		// make manager model.  start
		/////////////////////////////////////////////////////
    	ProjectDto projectDtoParam = new ProjectDto();
    	projectDtoParam.setUserName(managerUserName);
    	List<ProjectDto> projectDtoList = projectService.getProjectByUserName(projectDtoParam);
    	List<ProjectModel> projectModelList = new ArrayList<ProjectModel>();
    	for (ProjectDto projectDto : projectDtoList) {
    		ProjectModel projectModel = new ProjectModel();
			ConvertUtils.register(new DateConverter(null),  java.util.Date.class);
			BeanUtils.copyProperties(projectModel, projectDto);
			projectModelList.add(projectModel);
    		
    	}
    	model.addAttribute("projectModelList", projectModelList);
		/////////////////////////////////////////////////////
		// make manager model.  end
		/////////////////////////////////////////////////////

    	
		/////////////////////////////////////////////////////
		// access count. startd
		// preserve count to application.
		/////////////////////////////////////////////////////
		Map<String, UserAccessCounterModel> userAccessCounterMap = 
				(Map<String, UserAccessCounterModel>) context.getAttribute("application.map.user_access_counter");
		UserAccessCounterModel userAccessCounterModel = 
				(UserAccessCounterModel) userAccessCounterMap.get(userName);
		Map<String, String> sessionCounter = 
				(Map<String, String>) session.getAttribute("session.map.user_access_counter");
		if (sessionCounter.containsKey(userName)) {
			// no count.
			;
			
		} else {
			// count.
			sessionCounter.put(userName, "1");
			session.setAttribute("session.map.user_access_counter", sessionCounter);
			
			// accessCounter
			if (userAccessCounterModel == null) {
				userAccessCounterModel = new UserAccessCounterModel();
				
			}
			userAccessCounterModel.setUserName(userName);
			userAccessCounterModel.setUserAccessCount(userAccessCounterModel.getUserAccessCount() + 1);
			userAccessCounterModel.setAccessDate(new Date(System.currentTimeMillis()));
			
		}
		// preserve count to application.
		userAccessCounterMap.put(userName, userAccessCounterModel);
		context.setAttribute("application.map.user_access_counter", userAccessCounterMap);
		
		// add or remove top five list
		int cnt = 0;
		List<UserAccessCounterModel> userAccessTop5ModelList = 
				(List<UserAccessCounterModel>) context.getAttribute("application.list.user_access_top5");
		for (UserAccessCounterModel mModel : userAccessTop5ModelList) {
			if (userName.equals(mModel.getUserName())) {
				userAccessTop5ModelList.remove(cnt);
				break;
				
			}
			cnt++;
			
		}
		userAccessTop5ModelList.add(userAccessCounterModel);
		context.setAttribute("application.list.user_access_top5", userAccessTop5ModelList);

		playerModel.setUserAccessCounter(userAccessCounterModel);
		/////////////////////////////////////////////////////
		// -- access count. end
		/////////////////////////////////////////////////////
		
		
		/////////////////////////////////////////////////////
		// -- get scout count. start
		/////////////////////////////////////////////////////
		Map<String, UserScoutCounterModel> userScoutCounterMap = 
				(Map<String, UserScoutCounterModel>) context.getAttribute("application.map.user_scout_counter");
		UserScoutCounterModel userScoutCounterModel = new UserScoutCounterModel();
		if (userScoutCounterMap.containsKey(userName)) {
			userScoutCounterModel = userScoutCounterMap.get(userName);
			
		} else {
			userScoutCounterModel.setUserScoutCount(0);
			
		}
		
		playerModel.setUserScoutCounter(userScoutCounterModel);
		/////////////////////////////////////////////////////
		// -- get scout count. end
		/////////////////////////////////////////////////////
		
		
    	String[] messages = new String[1];
    	messages[0] = messageUtil.getMessage("player.message.info", null, locale);
    	model.addAttribute("messages", messages);
    	model.addAttribute("messages_type", "I");
    	
    	model.addAttribute("playerModel", playerModel);
		model.addAttribute("loginSessionState", loginSessionState);
		
    	return "players_info";
    	
    }
	
    
    
	/**
	 * scout players.
	 * @param userName
	 * @param projectId
	 * @param model
	 * @param attr
	 * @param request
	 * @param locale
	 * @return
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/players/{pUserName}", method = POST)
	public String scout(
    		@PathVariable("pUserName") String userName, 
    		ProjectModel projectModel,
    		UserModel managerModel,
    		Model model,
    		RedirectAttributes attr,
    		HttpServletRequest request, 
    		Locale locale
    		) throws BusinessException {
		
		// history check.
		TransmissionHistoryDto transmissionHistoryDto = new TransmissionHistoryDto();
		transmissionHistoryDto.setTransmissionType(SCOUT_TYPE);
		transmissionHistoryDto.setSendersName(managerModel.getUserName());
		transmissionHistoryDto.setRecipientName(userName);
		long diff = historyService.getDiff(transmissionHistoryDto);
		if (diff > 0 && SCOUT_INTERVAL > diff) {
        	String[] messages = new String[1];
        	messages[0] = messageUtil.getMessage("player.message.scout.interval", null, locale);
    		attr.addFlashAttribute("messages", messages);
    		attr.addAttribute("messages_type", "W");
    		return "redirect:/messages";

		}

		HttpSession session = request.getSession(false);
		
		ServletContext context = session.getServletContext();
		
		Map<String, JobCategoryMstModel> jobCategoryModelMap = 
				(Map<String, JobCategoryMstModel>) context.getAttribute("application.map.job_category");
		Map<String, LocationMstModel> locationModelMap = 
				(Map<String, LocationMstModel>) context.getAttribute("application.map.location");
		Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = 
				(Map<String, RecruitmentAreaMstModel>) context.getAttribute("application.map.recruitment_area");
		Map<String, AgeGroupMstModel> ageGroupModelMap = 
				(Map<String, AgeGroupMstModel>) context.getAttribute("application.map.age_group");
		Map<String, YearsExperienceMstModel> yearsExperienceModelMap = 
				(Map<String, YearsExperienceMstModel>) context.getAttribute("application.map.years_experience");
		Map<String, TypeIndustryMstModel> typeIndustryModelMap = 
				(Map<String, TypeIndustryMstModel>) context.getAttribute("application.map.type_industry");

		// send mail.
    	String server = messageUtil.getMessage("common.server", null, locale);
    	String protcol = messageUtil.getMessage("common.protcol", null, locale);
    	String fromAddress = messageUtil.getMessage("common.mail.from.address", null, locale);
    	String[] toAddress = new String[1];
    	UserBaseDto userDtoParam = new UserBaseDto();
    	userDtoParam.setUserName(userName);
    	UserBaseDto userDto = userService.getUserByUserName(userDtoParam);
    	toAddress[0] = userDto.getEmail();
    	String[] ccAddress = null;
    	String[] bccAddress= new String[1];
    	bccAddress[0] = messageUtil.getMessage("common.mail.bcc.address", null, locale);
    	String subject = messageUtil.getMessage("player.mail.scout.subject", null, locale);
    	String template = "player_scout_email_template.vm";
		
		ProjectDto projectDtoParam = new ProjectDto();
		projectDtoParam.setProjectId(projectModel.getProjectId());
		ProjectDto projectDto = projectService.getProjectByProjectId(projectDtoParam);
		
		String recruitmentAreaName = applicationUtil.convToRecruitmentAreaName(
				recruitmentAreaModelMap, projectDto.getRecruitmentAreaCode());
		
    	Map<String, String> tmplMap = new HashMap<String, String>();
    	tmplMap.put("userName", userName);
    	tmplMap.put("pjTitle", projectDto.getTitle());
    	tmplMap.put("pjRecruitmentAreaName", recruitmentAreaName);
    	tmplMap.put("pjOverView", projectDto.getOverView());
    	tmplMap.put("mgrUserName", managerModel.getUserName());
    	tmplMap.put("mgrEmail", managerModel.getEmail());
    	tmplMap.put("mgrAgeGroup", managerModel.getAgeGroup());
    	tmplMap.put("mgrLiveName", managerModel.getLiveName());
    	tmplMap.put("mgrAffiliationName", managerModel.getUserCareerOverviewList().get(0).getAffiliationName());
    	tmplMap.put("mgrJobCategoryName", managerModel.getUserCareerOverviewList().get(0).getJobCategoryName());
    	tmplMap.put("mgrOverView", managerModel.getUserCareerOverviewList().get(0).getOverView());

    	tmplMap.put("projectInfoUrl", protcol + server + request.getContextPath() + "/projects/" + projectModel.getProjectId());

    	projectService.sendMail(SCOUT_TYPE, managerModel.getUserName(), userName, fromAddress, toAddress, ccAddress, bccAddress, subject, template, tmplMap);
    	String[] messages = new String[1];
		messages[0] = messageUtil.getMessage("player.message.mail.complete", null, locale);
		
		attr.addFlashAttribute("messages_type", "I");
		attr.addFlashAttribute("messages", messages);

		
		/////////////////////////////////////////////////////
		// scout count. startd
		// preserve count to application.
		/////////////////////////////////////////////////////
		Map<String, UserScoutCounterModel> userScoutCounterMap = 
				(Map<String, UserScoutCounterModel>) context.getAttribute("application.map.user_scout_counter");
		UserScoutCounterModel userScoutCounterModel = 
				(UserScoutCounterModel) userScoutCounterMap.get(userName);
		Map<String, String> sessionCounter = 
				(Map<String, String>) session.getAttribute("session.map.user_scout_counter");
		if (sessionCounter.containsKey(userName)) {
			// no count.
			;
			
		} else {
			// count.
			sessionCounter.put(userName, "1");
			session.setAttribute("session.map.user_scout_counter", sessionCounter);
			
			// scoutCounter
			if (userScoutCounterModel == null) {
				userScoutCounterModel = new UserScoutCounterModel();
				
			}
			userScoutCounterModel.setUserName(userName);
			userScoutCounterModel.setUserScoutCount(userScoutCounterModel.getUserScoutCount() + 1);
			userScoutCounterModel.setScoutDate(new Date(System.currentTimeMillis()));
			
		}
		// preserve count to application.
		userScoutCounterMap.put(userName, userScoutCounterModel);
		context.setAttribute("application.map.user_scout_counter", userScoutCounterMap);
		
		// add or remove top five list
		int cnt = 0;
		List<UserScoutCounterModel> userScoutTop5ModelList = 
				(List<UserScoutCounterModel>) context.getAttribute("application.list.user_scout_top5");
		for (UserScoutCounterModel mModel : userScoutTop5ModelList) {
			if (userName.equals(mModel.getUserName())) {
				userScoutTop5ModelList.remove(cnt);
				break;
				
			}
			cnt++;
			
		}
		userScoutTop5ModelList.add(userScoutCounterModel);
		context.setAttribute("application.list.user_scout_top5", userScoutTop5ModelList);

		/////////////////////////////////////////////////////
		// -- access count. end
		/////////////////////////////////////////////////////

		return "redirect:/messages";
		
	}

}
