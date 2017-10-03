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

import org.springframework.dao.EmptyResultDataAccessException;

import com.soramame.web.biz.dto.AgeGroupMstDto;
import com.soramame.web.biz.dto.JobCategoryMstDto;
import com.soramame.web.biz.dto.LocationMstDto;
import com.soramame.web.biz.dto.RecruitmentAreaMstDto;
import com.soramame.web.biz.dto.TypeIndustryMstDto;
import com.soramame.web.biz.dto.YearsExperienceMstDto;

/**
 * ApplicationMasterService
 * 
 * @author Plat.
 * @version 1.0
 */
public interface ApplicationMasterService {
	
	/**
	 * get jobCategoryMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<JobCategoryMstDto> getJobCategoryMstOfAll() 
			throws EmptyResultDataAccessException;
	
	/**
	 * get typeIndustryMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<TypeIndustryMstDto> getTypeIndustryMstOfAll()
			throws EmptyResultDataAccessException;
	
	/**
	 * get recruitmentAreaMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<RecruitmentAreaMstDto> getRecruitmentAreaMstOfAll()
			throws EmptyResultDataAccessException;
	
	/**
	 * get locationMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<LocationMstDto> getLocationMstOfAll()
			throws EmptyResultDataAccessException;
	
	/**
	 * get yearsExperienceMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<YearsExperienceMstDto> getYearsExperienceMstOfAll()
			throws EmptyResultDataAccessException;
	
	/**
	 * get ageGroupMst of all.
	 * @return
	 * @throws EmptyResultDataAccessException
	 */
	public List<AgeGroupMstDto> getAgeGroupMstOfAll()
			throws EmptyResultDataAccessException;

}
