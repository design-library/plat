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
package com.soramame.web.biz.service;

import java.util.List;

import com.soramame.web.biz.dto.ParticipationHopeCounterDto;
import com.soramame.web.biz.dto.ProjectAccessCounterDto;
import com.soramame.web.biz.dto.UserAccessCounterDto;
import com.soramame.web.biz.dto.UserScoutCounterDto;
import com.soramame.web.exception.UpdateDataException;

/**
 * CounterService.
 * 
 * @author Plat.
 * @version 1.0
 *
 */
public interface CounterService {
	
	/**
	 * get userAccessCounter of all.
	 * @return
	 */
	public List<UserAccessCounterDto> getUserAccessCounterOfAll();
	
	/**
	 * get user access top 5.
	 * @return
	 */
	public List<UserAccessCounterDto> getUserAccessTopFive();
	
	/**
	 * update userAccessCounter
	 * @param userAccessCounterDtoList
	 * @return
	 * @throws UpdateDataException
	 */
	public int updateUserAccessCounter(List<UserAccessCounterDto> userAccessCounterDtoList) throws UpdateDataException;
	
	/**
	 * get userScoutCounter of all.
	 * @return
	 */
	public List<UserScoutCounterDto> getUserScoutCounterOfAll();
	
	/**
	 * get user scout top 5.
	 * @return
	 */
	public List<UserScoutCounterDto> getUserScoutTopFive();
	
	/**
	 * update userScoutCounter
	 * @param userScoutCounterDtoList
	 * @return
	 * @throws UpdateDataException
	 */
	public int updateUserScoutCounter(List<UserScoutCounterDto> userScoutCounterDtoList) throws UpdateDataException;

	
	/**
	 * get projectAccessCounter of all.
	 * @return
	 */
	public List<ProjectAccessCounterDto> getProjectAccessCounterOfAll();
	
	/**
	 * get project access top 5.
	 * @return
	 */
	public List<ProjectAccessCounterDto> getProjectAccessTopFive();
	
	/**
	 * update projectAccessCounter
	 * @param projectAccessCounterDtoList
	 * @return
	 * @throws UpdateDataException
	 */
	public int updateProjectAccessCounter(List<ProjectAccessCounterDto> projectAccessCounterDtoList) throws UpdateDataException;
	
	/**
	 * get participationHopeCounter of all.
	 * @return
	 */
	public List<ParticipationHopeCounterDto> getParticipationHopeCounterOfAll();
	
	/**
	 * get participation hope top 5.
	 * @return
	 */
	public List<ParticipationHopeCounterDto> getParticipationHopeTopFive();
	
	/**
	 * update participationHopeCounter
	 * @param participationHopeCounterDtoList
	 * @return
	 * @throws UpdateDataException
	 */
	public int updateParticipationHopeCounter(List<ParticipationHopeCounterDto> participationHopeCounterDtoList) throws UpdateDataException;

}
