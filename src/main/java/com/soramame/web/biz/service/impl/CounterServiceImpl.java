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
package com.soramame.web.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soramame.web.biz.dao.ParticipationHopeCounterDao;
import com.soramame.web.biz.dao.ProjectAccessCounterDao;
import com.soramame.web.biz.dao.ProjectDao;
import com.soramame.web.biz.dao.UserAccessCounterDao;
import com.soramame.web.biz.dao.UserScoutCounterDao;
import com.soramame.web.biz.dto.ParticipationHopeCounterDto;
import com.soramame.web.biz.dto.ProjectAccessCounterDto;
import com.soramame.web.biz.dto.ProjectDto;
import com.soramame.web.biz.dto.UserAccessCounterDto;
import com.soramame.web.biz.dto.UserScoutCounterDto;
import com.soramame.web.biz.service.CounterService;
import com.soramame.web.biz.service.ProjectService;
import com.soramame.web.exception.UpdateDataException;

/**
 * CounterServiceImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Service
public class CounterServiceImpl implements CounterService {

	@Autowired
	private UserAccessCounterDao userAccessCounterDao;
	
	@Autowired
	private UserScoutCounterDao userScoutCounterDao;
	
	@Autowired
	private ProjectAccessCounterDao projectAccessCounterDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ParticipationHopeCounterDao participationHopeCounterDao;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserAccessCounterOfAll()
	 */
	@Override
	public List<UserAccessCounterDto> getUserAccessCounterOfAll() {
		
		List<UserAccessCounterDto> userAccessCounterDtoRes = 
				userAccessCounterDao.findAllOrderByUserAccessCountAndUpdateDate();
		
		return userAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserAccessTopFive()
	 */
	@Override
	public List<UserAccessCounterDto> getUserAccessTopFive() {
		
		List<UserAccessCounterDto> userAccessCounterDtoRtn = 
				new ArrayList<UserAccessCounterDto>(5);
		
		List<UserAccessCounterDto> userAccessCounterDtoRes = 
				userAccessCounterDao.findAllOrderByUserAccessCountAndUpdateDate();
		int resSize = userAccessCounterDtoRes.size();
		
		for (int i = 0;
				i < 5 && i < resSize && 0 < resSize;
				i++) {
			
			userAccessCounterDtoRtn.add(userAccessCounterDtoRes.get(i));
			
		}
		return userAccessCounterDtoRtn;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#updateUserAccessCounter(java.util.List)
	 */
	@Override
	public int updateUserAccessCounter(
			List<UserAccessCounterDto> userAccessCounterDtoList) throws UpdateDataException {
		
		int resDel = 0;
		int resIns = 0;
		int res = 0;
		
		for (UserAccessCounterDto userAccessCounterDto : userAccessCounterDtoList) {
			int existCount = userAccessCounterDao.getUserAccessCount(userAccessCounterDto.getUserName());
			if (existCount > 0) {
				resDel = userAccessCounterDao.delete(userAccessCounterDto);
				resIns = userAccessCounterDao.create(userAccessCounterDto);
				res = resDel * resIns;
				
			} else {
				res = userAccessCounterDao.create(userAccessCounterDto);
				
			}
			if (res == 0) {
				break;
				
			}
			
		}
		return res;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserScoutCounterOfAll()
	 */
	@Override
	public List<UserScoutCounterDto> getUserScoutCounterOfAll() {
		
		List<UserScoutCounterDto> userScoutCounterDtoRes = 
				userScoutCounterDao.findAllOrderByUserScoutCountAndUpdateDate();
		
		return userScoutCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserScoutTopFive()
	 */
	@Override
	public List<UserScoutCounterDto> getUserScoutTopFive() {
		
		List<UserScoutCounterDto> userScoutCounterDtoRtn = 
				new ArrayList<UserScoutCounterDto>(5);
		
		List<UserScoutCounterDto> userScoutCounterDtoRes = 
				userScoutCounterDao.findAllOrderByUserScoutCountAndUpdateDate();
		int resSize = userScoutCounterDtoRes.size();
		
		for (int i = 0;
				i < 5 && i < resSize && 0 < resSize;
				i++) {
			
			userScoutCounterDtoRtn.add(userScoutCounterDtoRes.get(i));
			
		}
		return userScoutCounterDtoRtn;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#updateUserScoutCounter(java.util.List)
	 */
	@Override
	public int updateUserScoutCounter(
			List<UserScoutCounterDto> userScoutCounterDtoList) throws UpdateDataException {
		
		int resDel = 0;
		int resIns = 0;
		int res = 0;
		
		for (UserScoutCounterDto userScoutCounterDto : userScoutCounterDtoList) {
			
			int existCount = userScoutCounterDao.getUserScoutCount(userScoutCounterDto.getUserName());
			if (existCount > 0) {
				resDel = userScoutCounterDao.delete(userScoutCounterDto);
				resIns = userScoutCounterDao.create(userScoutCounterDto);
				res = resDel * resIns;
				
			} else {
				res = userScoutCounterDao.create(userScoutCounterDto);
				
			}
			if (res == 0) {
				break;
				
			}
			
		}
		return res;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getProjectAccessCounterOfAll()
	 */
	@Override
	public List<ProjectAccessCounterDto> getProjectAccessCounterOfAll() {
		
		List<ProjectAccessCounterDto> projectAccessCounterDtoRes = 
				projectAccessCounterDao.findAllOrderByProjectAccessCountAndUpdateDate();
		
		return projectAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getProjectAccessTopFive()
	 */
	@Override
	public List<ProjectAccessCounterDto> getProjectAccessTopFive() {
		
		List<ProjectAccessCounterDto> projectAccessCounterDtoRtn = 
				new ArrayList<ProjectAccessCounterDto>(5);
		
		List<ProjectAccessCounterDto> projectAccessCounterDtoRes = 
				projectAccessCounterDao.findAllOrderByProjectAccessCountAndUpdateDate();
		int resSize = projectAccessCounterDtoRes.size();
		
		for (int i = 0;
				i < 5 && i < resSize && 0 < resSize;
				i++) {
			ProjectAccessCounterDto projectAccessCounterDto = projectAccessCounterDtoRes.get(i);
			ProjectDto projectDtoParam = new ProjectDto();
			projectDtoParam.setProjectId(projectAccessCounterDto.getProjectId());
			ProjectDto projectDto = projectDao.findByPrimaryKey(projectDtoParam);
			projectAccessCounterDto.setTitle(projectDto.getTitle());
			projectAccessCounterDtoRtn.add(projectAccessCounterDto);
			
		}
		return projectAccessCounterDtoRtn;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#updateProjectAccessCounter(java.util.List)
	 */
	@Override
	public int updateProjectAccessCounter(
			List<ProjectAccessCounterDto> projectAccessCounterDtoList) throws UpdateDataException {
		
		int resDel = 0;
		int resIns = 0;
		int res = 0;
		
		for (ProjectAccessCounterDto projectAccessCounterDto : projectAccessCounterDtoList) {
			
			int existCount = projectAccessCounterDao.getProjectAccessCount(projectAccessCounterDto.getProjectId());
			if (existCount > 0) {
				resDel = projectAccessCounterDao.delete(projectAccessCounterDto);
				resIns = projectAccessCounterDao.create(projectAccessCounterDto);
				res = resDel * resIns;
				
			} else {
				res = projectAccessCounterDao.create(projectAccessCounterDto);
				
			}
			if (res == 0) {
				break;
				
			}
			
		}
		return res;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getParticipationHopeCounterOfAll()
	 */
	@Override
	public List<ParticipationHopeCounterDto> getParticipationHopeCounterOfAll() {
		
		List<ParticipationHopeCounterDto> participationHopeCounterDtoList = 
				participationHopeCounterDao.findAllOrderByParticipationHopeCountAndUpdateDate();
		List<ParticipationHopeCounterDto> participationHopeCounterDtoRes = new ArrayList<ParticipationHopeCounterDto>();
		for (ParticipationHopeCounterDto dto : participationHopeCounterDtoList) {
			ProjectDto param = new ProjectDto();
			param.setProjectId(dto.getProjectId());
			ProjectDto projectDto = projectDao.findByPrimaryKey(param);
			dto.setTitle(projectDto.getTitle());
			participationHopeCounterDtoRes.add(dto);
		}
		
		return participationHopeCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getParticipationHopeTopFive()
	 */
	@Override
	public List<ParticipationHopeCounterDto> getParticipationHopeTopFive() {
		
		List<ParticipationHopeCounterDto> participationHopeCounterDtoRtn = 
				new ArrayList<ParticipationHopeCounterDto>(5);
		
		List<ParticipationHopeCounterDto> participationHopeCounterDtoRes = 
				participationHopeCounterDao.findAllOrderByParticipationHopeCountAndUpdateDate();
		int resSize = participationHopeCounterDtoRes.size();
		
		for (int i = 0;
				i < 5 && i < resSize && 0 < resSize;
				i++) {
			
			ParticipationHopeCounterDto dto = participationHopeCounterDtoRes.get(i);
			ProjectDto param = new ProjectDto();
			param.setProjectId(dto.getProjectId());
			ProjectDto projectDto = projectDao.findByPrimaryKey(param);
			dto.setTitle(projectDto.getTitle());
			participationHopeCounterDtoRtn.add(dto);
			
		}
		return participationHopeCounterDtoRtn;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#updateParticipationHopeCounter(java.util.List)
	 */
	@Override
	public int updateParticipationHopeCounter(
			List<ParticipationHopeCounterDto> participationHopeCounterDtoList) throws UpdateDataException {
		
		int resDel = 0;
		int resIns = 0;
		int res = 0;
		
		for (ParticipationHopeCounterDto participationHopeCounterDto : participationHopeCounterDtoList) {
			int existCount = participationHopeCounterDao.getParticipationHopeCount(participationHopeCounterDto.getProjectId());
			if (existCount > 0) {
				resDel = participationHopeCounterDao.delete(participationHopeCounterDto);
				resIns = participationHopeCounterDao.create(participationHopeCounterDto);
				res = resDel * resIns;
				
			} else {
				res = participationHopeCounterDao.create(participationHopeCounterDto);
				
			}
			if (res == 0) {
				break;
				
			}
			
		}
		return res;
		
	}


}
