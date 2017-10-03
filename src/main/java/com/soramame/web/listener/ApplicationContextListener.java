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
package com.soramame.web.listener;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soramame.web.biz.dto.RecruitmentAreaMstDto;
import com.soramame.web.biz.dto.AgeGroupMstDto;
import com.soramame.web.biz.dto.JobCategoryMstDto;
import com.soramame.web.biz.dto.LocationMstDto;
import com.soramame.web.biz.dto.ParticipationHopeCounterDto;
import com.soramame.web.biz.dto.ProjectAccessCounterDto;
import com.soramame.web.biz.dto.TypeIndustryMstDto;
import com.soramame.web.biz.dto.UserAccessCounterDto;
import com.soramame.web.biz.dto.UserScoutCounterDto;
import com.soramame.web.biz.service.ApplicationMasterService;
import com.soramame.web.biz.service.CounterService;
import com.soramame.web.exception.UpdateDataException;
import com.soramame.web.model.RecruitmentAreaMstModel;
import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.ParticipationHopeCounterModel;
import com.soramame.web.model.ProjectAccessCounterModel;
import com.soramame.web.model.TypeIndustryMstModel;
import com.soramame.web.model.UserAccessCounterModel;
import com.soramame.web.model.UserModel;
import com.soramame.web.model.UserScoutCounterModel;
import com.soramame.web.model.YearsExperienceMstModel;

/**
 * ApplicationContextListener.
 * 
 * @version 1.0
 * @author Plat.
 */
public class ApplicationContextListener implements ServletContextListener {
	
	@Autowired
	private CounterService counterService;
	
	@Autowired
	private ApplicationMasterService applicationMasterService;
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/spring/beans-biz.xml");
		
		ServletContext svltContext = sce.getServletContext();
		
		counterService = appContext.getBean(CounterService.class);
		
		try {
			// insert into user access counter.
			Map<String, UserAccessCounterModel> userAccessCounterMap = 
					(Map<String, UserAccessCounterModel>)svltContext.getAttribute("application.map.user_access_counter");
			Iterator<String> userAccessCounterIte = userAccessCounterMap.keySet().iterator();
			List<UserAccessCounterDto> userAccessCounterDtoList = new ArrayList<UserAccessCounterDto>();
			while (userAccessCounterIte.hasNext()) {
				String userName = userAccessCounterIte.next();
				UserAccessCounterModel model = userAccessCounterMap.get(userName);
				UserAccessCounterDto dto = new UserAccessCounterDto();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(dto, model);
				userAccessCounterDtoList.add(dto);
				
			}
			counterService.updateUserAccessCounter(userAccessCounterDtoList);
			
		} catch (IllegalAccessException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (InvocationTargetException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (UpdateDataException e) {
			sce.getServletContext().log("Cannot update...", e);
			throw new RuntimeException();
			
		}
		
		try {
		
			// insert into user scout counter.
			Map<String, UserScoutCounterModel> userScoutCounterMap = 
					(Map<String, UserScoutCounterModel>)svltContext.getAttribute("application.map.user_scout_counter");
			Iterator<String> userScoutCounterIte = userScoutCounterMap.keySet().iterator();
			List<UserScoutCounterDto> userScoutCounterDtoList = new ArrayList<UserScoutCounterDto>();
			while (userScoutCounterIte.hasNext()) {
				String userName = userScoutCounterIte.next();
				UserScoutCounterModel model = userScoutCounterMap.get(userName);
				UserScoutCounterDto dto = new UserScoutCounterDto();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(dto, model);
				userScoutCounterDtoList.add(dto);
				
			}
			counterService.updateUserScoutCounter(userScoutCounterDtoList);
			
		} catch (IllegalAccessException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (InvocationTargetException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (UpdateDataException e) {
			sce.getServletContext().log("Cannot update...", e);
			throw new RuntimeException();
			
		}
			
		try {
			// insert into project access counter.
			Map<String, ProjectAccessCounterModel> projectAccessCounterMap = 
					(Map<String, ProjectAccessCounterModel>)svltContext.getAttribute("application.map.project_access_counter");
			Iterator<String> projectAccessCounterIte = projectAccessCounterMap.keySet().iterator();
			List<ProjectAccessCounterDto> projectAccessCounterDtoList = new ArrayList<ProjectAccessCounterDto>();
			while (projectAccessCounterIte.hasNext()) {
				String projectId = projectAccessCounterIte.next();
				ProjectAccessCounterModel model = projectAccessCounterMap.get(projectId);
				ProjectAccessCounterDto dto = new ProjectAccessCounterDto();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(dto, model);
				projectAccessCounterDtoList.add(dto);
				
			}
			counterService.updateProjectAccessCounter(projectAccessCounterDtoList);
			
		} catch (IllegalAccessException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (InvocationTargetException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (UpdateDataException e) {
			sce.getServletContext().log("Cannot update...", e);
			throw new RuntimeException();
			
		}
		
		try {
			// insert into participation hope counter.
			Map<String, ParticipationHopeCounterModel> participationHopeCounterMap = 
					(Map<String, ParticipationHopeCounterModel>)svltContext.getAttribute("application.map.participation_hope_counter");
			Iterator<String> participationHopeCounterIte = participationHopeCounterMap.keySet().iterator();
			List<ParticipationHopeCounterDto> participationHopeCounterDtoList = new ArrayList<ParticipationHopeCounterDto>();
			while (participationHopeCounterIte.hasNext()) {
				String projectId = participationHopeCounterIte.next();
				ParticipationHopeCounterModel model = participationHopeCounterMap.get(projectId);
				ParticipationHopeCounterDto dto = new ParticipationHopeCounterDto();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(dto, model);
				participationHopeCounterDtoList.add(dto);
				
			}
			counterService.updateParticipationHopeCounter(participationHopeCounterDtoList);

		} catch (IllegalAccessException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (InvocationTargetException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (UpdateDataException e) {
			sce.getServletContext().log("Cannot update...", e);
			throw new RuntimeException();
			
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/spring/beans-biz.xml");
		
		ServletContext svltContext = sce.getServletContext();
		
		counterService = appContext.getBean(CounterService.class);
		
		// set authenticated user map.
		Map<String, UserModel> authenticatedUserMap = new HashMap<String, UserModel>();
		svltContext.setAttribute("application.map.authenticated_user", authenticatedUserMap);
		
		try {
			////////////////////////////////////////////////////////////////
			// set counter. start.
			////////////////////////////////////////////////////////////////
			// set user access counter.
			Map<String, UserAccessCounterModel> userAccessCounterMap = new HashMap<String, UserAccessCounterModel>();
			List<UserAccessCounterDto> userAccessCounterDtoList = counterService.getUserAccessCounterOfAll();
			for (UserAccessCounterDto dto : userAccessCounterDtoList) {
				UserAccessCounterModel model = new UserAccessCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				userAccessCounterMap.put(model.getUserName(), model);
				
			}
			svltContext.setAttribute("application.map.user_access_counter", userAccessCounterMap);
			
			// set user scout counter.
			Map<String, UserScoutCounterModel> userScoutCounterMap = new HashMap<String, UserScoutCounterModel>();
			List<UserScoutCounterDto> userScoutCounterDtoList = counterService.getUserScoutCounterOfAll();
			for (UserScoutCounterDto dto : userScoutCounterDtoList) {
				UserScoutCounterModel model = new UserScoutCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				userScoutCounterMap.put(model.getUserName(), model);
				
			}
			svltContext.setAttribute("application.map.user_scout_counter", userScoutCounterMap);
			
			// set project access counter.
			Map<String, ProjectAccessCounterModel> projectAccessCounterMap = new HashMap<String, ProjectAccessCounterModel>();
			List<ProjectAccessCounterDto> projectAccessCounterDtoList = counterService.getProjectAccessCounterOfAll();
			for (ProjectAccessCounterDto dto : projectAccessCounterDtoList) {
				ProjectAccessCounterModel model = new ProjectAccessCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				projectAccessCounterMap.put(model.getProjectId(), model);
				
			}
			svltContext.setAttribute("application.map.project_access_counter", projectAccessCounterMap);
			
			// set participation hope counter.
			Map<String, ParticipationHopeCounterModel> participationHopeCounterMap = new HashMap<String, ParticipationHopeCounterModel>();
			List<ParticipationHopeCounterDto> participationHopeCounterDtoList = counterService.getParticipationHopeCounterOfAll();
			for (ParticipationHopeCounterDto dto : participationHopeCounterDtoList) {
				ParticipationHopeCounterModel model = new ParticipationHopeCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				participationHopeCounterMap.put(model.getProjectId(), model);
				
			}
			svltContext.setAttribute("application.map.participation_hope_counter", participationHopeCounterMap);
			////////////////////////////////////////////////////////////////
			// -- set counter. end.
			////////////////////////////////////////////////////////////////

			
			////////////////////////////////////////////////////////////////
			// set top five. start.
			////////////////////////////////////////////////////////////////
			// set user access top5.
			List<UserAccessCounterModel> userAccessTop5ModelList = new ArrayList<UserAccessCounterModel>();
			List<UserAccessCounterDto> userAccessTop5DtoList = counterService.getUserAccessTopFive();
			for (UserAccessCounterDto dto : userAccessTop5DtoList) {
				UserAccessCounterModel model = new UserAccessCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				userAccessTop5ModelList.add(model);
				
			}
			svltContext.setAttribute("application.list.user_access_top5", userAccessTop5ModelList);
			
			// set user scout top5.
			List<UserScoutCounterModel> userScoutTop5ModelList = new ArrayList<UserScoutCounterModel>();
			List<UserScoutCounterDto> userScoutTop5DtoList = counterService.getUserScoutTopFive();
			for (UserScoutCounterDto dto : userScoutTop5DtoList) {
				UserScoutCounterModel model = new UserScoutCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				userScoutTop5ModelList.add(model);
				
			}
			svltContext.setAttribute("application.list.user_scout_top5", userScoutTop5ModelList);
			
			// set project access top5.
			List<ProjectAccessCounterModel> projectAccessTop5ModelList = new ArrayList<ProjectAccessCounterModel>();
			List<ProjectAccessCounterDto> projectAccessTop5DtoList = counterService.getProjectAccessTopFive();
			for (ProjectAccessCounterDto dto : projectAccessTop5DtoList) {
				ProjectAccessCounterModel model = new ProjectAccessCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				
				projectAccessTop5ModelList.add(model);
				
			}
			svltContext.setAttribute("application.list.project_access_top5", projectAccessTop5ModelList);
			
			// set participation hope top5.
			List<ParticipationHopeCounterModel> participationHopeTop5ModelList = new ArrayList<ParticipationHopeCounterModel>();
			List<ParticipationHopeCounterDto> participationHopeTop5DtoList = counterService.getParticipationHopeTopFive();
			for (ParticipationHopeCounterDto dto : participationHopeTop5DtoList) {
				ParticipationHopeCounterModel model = new ParticipationHopeCounterModel();
				ConvertUtils.register(new DateConverter(null), java.util.Date.class);
				BeanUtils.copyProperties(model, dto);
				participationHopeTop5ModelList.add(model);
				
			}
			svltContext.setAttribute("application.list.participation_hope_top5", participationHopeTop5ModelList);
			////////////////////////////////////////////////////////////////
			// -- set top five. end.
			////////////////////////////////////////////////////////////////
			
			
			////////////////////////////////////////////////////////////////
			// set applications map & list. start.
			////////////////////////////////////////////////////////////////
			
			applicationMasterService = appContext.getBean(ApplicationMasterService.class);
			
			// set job category.
			List<JobCategoryMstModel> jobCategoryModelList = new ArrayList<JobCategoryMstModel>();
			Map<String, JobCategoryMstModel> jobCategoryModelMap = new HashMap<String, JobCategoryMstModel>();
			List<JobCategoryMstDto> jobCategoryDtoList = applicationMasterService.getJobCategoryMstOfAll();
			for (JobCategoryMstDto dto : jobCategoryDtoList) {
				JobCategoryMstModel model = new JobCategoryMstModel(
						dto.getJobCategoryCode(),
						dto.getJobCategoryName(),
						"");
				jobCategoryModelMap.put(dto.getJobCategoryCode(), model);
				jobCategoryModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.job_category", jobCategoryModelMap);
			svltContext.setAttribute("application.list.job_category", jobCategoryModelList);

			// set location.
			List<LocationMstModel> locationModelList = new ArrayList<LocationMstModel>();
			Map<String, LocationMstModel> locationModelMap = new HashMap<String, LocationMstModel>();
			List<LocationMstDto> locationDtoList = applicationMasterService.getLocationMstOfAll();
			for (LocationMstDto dto : locationDtoList) {
				LocationMstModel model = new LocationMstModel(
						dto.getLocationCode(),
						dto.getLocationName(),
						"");
				locationModelMap.put(dto.getLocationCode(), model);
				locationModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.location", locationModelMap);
			svltContext.setAttribute("application.list.location", locationModelList);

			// set recruitment area.
			List<RecruitmentAreaMstModel> recruitmentAreaModelList = new ArrayList<RecruitmentAreaMstModel>();
			Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap = new HashMap<String, RecruitmentAreaMstModel>();
			List<RecruitmentAreaMstDto> recruitmentAreaDtoList = applicationMasterService.getRecruitmentAreaMstOfAll();
			for (RecruitmentAreaMstDto dto : recruitmentAreaDtoList) {
				RecruitmentAreaMstModel model = new RecruitmentAreaMstModel(
						dto.getRecruitmentAreaCode(),
						dto.getRecruitmentAreaName(),
						"");
				recruitmentAreaModelMap.put(dto.getRecruitmentAreaCode(), model);
				recruitmentAreaModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.recruitment_area", recruitmentAreaModelMap);
			svltContext.setAttribute("application.list.recruitment_area", recruitmentAreaModelList);
			
			// set age group.
			List<AgeGroupMstModel> ageGroupModelList = new ArrayList<AgeGroupMstModel>();
			Map<String, AgeGroupMstModel> ageGroupModelMap = new HashMap<String, AgeGroupMstModel>();
			List<AgeGroupMstDto> ageGroupDtoList = applicationMasterService.getAgeGroupMstOfAll();
			for (AgeGroupMstDto dto : ageGroupDtoList) {
				AgeGroupMstModel model = new AgeGroupMstModel(
						dto.getAgeGroupCode(),
						dto.getAgeGroupName(),
						"");
				ageGroupModelMap.put(dto.getAgeGroupCode(), model);
				ageGroupModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.age_group", ageGroupModelMap);
			svltContext.setAttribute("application.list.age_group", ageGroupModelList);
			
			// set type industry.
			List<TypeIndustryMstModel> typeIndustryModelList = new ArrayList<TypeIndustryMstModel>();
			Map<String, TypeIndustryMstModel> typeIndustryModelMap = new HashMap<String, TypeIndustryMstModel>();
			List<TypeIndustryMstDto> typeIndustryDtoList = applicationMasterService.getTypeIndustryMstOfAll();
			for (TypeIndustryMstDto dto : typeIndustryDtoList) {
				TypeIndustryMstModel model = new TypeIndustryMstModel(
						dto.getTypeIndustryCode(),
						dto.getTypeIndustryName(),
						"");
				typeIndustryModelMap.put(dto.getTypeIndustryCode(), model);
				typeIndustryModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.type_industry", typeIndustryModelMap);
			svltContext.setAttribute("application.list.type_industry", typeIndustryModelList);
			
			// set years experience.
			List<YearsExperienceMstModel> yearsExperienceModelList = new ArrayList<YearsExperienceMstModel>();
			Map<String, YearsExperienceMstModel> yearsExperienceModelMap = new HashMap<String, YearsExperienceMstModel>();
			List<TypeIndustryMstDto> yearsExperienceDtoList = applicationMasterService.getTypeIndustryMstOfAll();
			for (TypeIndustryMstDto dto : yearsExperienceDtoList) {
				YearsExperienceMstModel model = new YearsExperienceMstModel(
						dto.getTypeIndustryCode(),
						dto.getTypeIndustryName(),
						"");
				yearsExperienceModelMap.put(dto.getTypeIndustryCode(), model);
				yearsExperienceModelList.add(model);
				
			}
			svltContext.setAttribute("application.map.years_experience", yearsExperienceModelMap);
			svltContext.setAttribute("application.list.years_experience", yearsExperienceModelList);
			
			////////////////////////////////////////////////////////////////
			// -- set applications. end.
			////////////////////////////////////////////////////////////////
			
		} catch (IllegalAccessException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		} catch (InvocationTargetException e) {
			sce.getServletContext().log("Cannot copy properties...", e);
			throw new RuntimeException();
			
		}
		
	}

}
