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

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soramame.web.biz.dto.ProjectDto;
import com.soramame.web.biz.dto.UserCareerOverviewDto;
import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.ProjectService;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.ParticipationHopeCounterModel;
import com.soramame.web.model.ProjectAccessCounterModel;
import com.soramame.web.model.ProjectModel;
import com.soramame.web.model.RecruitmentAreaMstModel;
import com.soramame.web.model.TypeIndustryMstModel;
import com.soramame.web.model.UserAccessCounterModel;
import com.soramame.web.model.UserCareerOverviewModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.model.UserScoutCounterModel;
import com.soramame.web.model.YearsExperienceMstModel;
import com.soramame.web.util.ApplicationUtil;
import com.soramame.web.util.ParticipationHopeCounterComparator;
import com.soramame.web.util.ProjectAccessCounterComparator;
import com.soramame.web.util.UserAccessCounterComparator;
import com.soramame.web.util.UserScoutCounterComparator;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * IndexController
 * 
 * @author Plat.
 * @version 1.0
 */
@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProjectService projectService;

	@Autowired
	private ApplicationUtil applicationUtil;
	
	/**
	 * forward index.
	 * @return
	 */
	@RequestMapping(value = "/", method = GET)
	public String home() {
		return "forward:/index";
		
	}
	
	/**
	 * to index.
	 * @param model
	 * @param request
	 * @param locale
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws BusinessException 
	 */
	@RequestMapping(value = "/index", method = GET)
	public String index(
    		Model model,
    		HttpServletRequest request,
    		Locale locale
    		) throws IllegalAccessException, InvocationTargetException, BusinessException {
		// get context.
		HttpSession session = request.getSession(true);
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
		Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = 
				(Map<String, RecruitmentAreaMstModel>) context.getAttribute("application.map.recruitment_area");

		
		////////////////////////////////////////////////////////////////
		// player. start.
		////////////////////////////////////////////////////////////////
		// get user access top5.
		List<UserAccessCounterModel> userAccessTop5List = 
				(List<UserAccessCounterModel>) context.getAttribute("application.list.user_access_top5");
		Collections.sort(userAccessTop5List, new UserAccessCounterComparator());
		model.addAttribute("userAccessTop5List", userAccessTop5List);
		
		// get user scout top5.
		List<UserScoutCounterModel> userScoutTop5List = 
				(List<UserScoutCounterModel>) context.getAttribute("application.list.user_scout_top5");
		Collections.sort(userScoutTop5List, new UserScoutCounterComparator());
		model.addAttribute("userScoutTop5List", userScoutTop5List);
		
		// player list new5.
		UserBaseDto userBaseDto = new UserBaseDto();
		userBaseDto.setRole("10"); // player:10
		List<UserBaseDto> userBaseDtoList = userService.getUserByRole(userBaseDto);
		List<UserModel> userModelNew5List = new ArrayList<UserModel>(5);
		if (userBaseDtoList.size() > 0) {
			for (int i = 0;userModelNew5List.size() < 5 && userBaseDtoList.size() > i;i++) {
				UserBaseDto userBaseDtoRes = userBaseDtoList.get(i);
				String userName = userBaseDtoRes.getUserName();
				
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
					BeanUtils.copyProperties(userModel, userBaseDtoRes);
					userModel.setAgeGroup(applicationUtil.convToAgeGrp(
									ageGroupModelMap, userBaseDtoRes.getBirthDay()));
					userModel.setLiveName(applicationUtil.convToLiveName(
									locationModelMap, userBaseDtoRes.getLiveCode()));
					userModel.setUserCareerOverviewList(userCareerOverviewModelWrap);
					
					// make user_model_top5.
					userModelNew5List.add(userModel);
					
				}
				
			}
		}
		model.addAttribute("userModelNew5List", userModelNew5List);
		
		////////////////////////////////////////////////////////////////
		// -- player. end.
		////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////////////////////////////////////
		// project. start.
		////////////////////////////////////////////////////////////////
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
		
		// Project list new5.
		List<ProjectDto> projectDtoList = projectService.getProjectOfAll();
		List<ProjectModel> projectModelNew5List = new ArrayList<ProjectModel>(5);
		if (projectDtoList.size() > 0) {
			for (int i = 0;projectModelNew5List.size() < 5 && projectDtoList.size() > i;i++) {
				ProjectDto projectDtoRes = projectDtoList.get(i);
				ProjectModel projectModel = new ProjectModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(projectModel, projectDtoRes);
	    		projectModel.setRecruitmentAreaName(
						applicationUtil.convToRecruitmentAreaName(
								recruitmentAreaModelMap, projectModel.getRecruitmentAreaCode()));
				
				projectModelNew5List.add(projectModel);
				
			}
		}
		model.addAttribute("projectModelNew5List", projectModelNew5List);
		
		////////////////////////////////////////////////////////////////
		// -- player. end.
		////////////////////////////////////////////////////////////////
		
		model.addAttribute("userModel", new UserModel());
		
		return "index";
		
	}
	
	/**
	 * to about.
	 * @return
	 */
	@RequestMapping(value = "/about", method = GET)
	public String about() {
		return "about";
		
	}
	
	/**
	 * to agreement.
	 * @return
	 */
	@RequestMapping(value = "/agreement", method = GET)
	public String agreement() {
		return "agreement";
		
	}
	
	/**
	 * to learnmore.
	 * @return
	 */
	@RequestMapping(value = "/learnmore", method = GET)
	public String learnmore() {
		return "learnmore";
		
	}
	
	/**
	 * redirect guide.
	 * @return
	 */
	@RequestMapping(value = "/guideRd", method = GET)
	public String guideRd() {
		return "redirect:/guide";
		
	}
	
	/**
	 * to guide.
	 * @return
	 */
	@RequestMapping(value = "/guide", method = GET)
	public String guide() {
		return "guide";
		
	}
	
	/**
	 * to messages
	 * @return
	 */
	@RequestMapping(value = "/messages", method = GET)
	public String toMessages() {
		return "messages";
		
	}

}
