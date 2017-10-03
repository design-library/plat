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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.soramame.web.biz.dao.AgeGroupMstDao;
import com.soramame.web.biz.dao.JobCategoryMstDao;
import com.soramame.web.biz.dao.LocationMstDao;
import com.soramame.web.biz.dao.RecruitmentAreaMstDao;
import com.soramame.web.biz.dao.TypeIndustryMstDao;
import com.soramame.web.biz.dao.YearsExperienceMstDao;
import com.soramame.web.biz.dto.RecruitmentAreaMstDto;
import com.soramame.web.biz.dto.AgeGroupMstDto;
import com.soramame.web.biz.dto.JobCategoryMstDto;
import com.soramame.web.biz.dto.LocationMstDto;
import com.soramame.web.biz.dto.TypeIndustryMstDto;
import com.soramame.web.biz.dto.YearsExperienceMstDto;
import com.soramame.web.biz.service.ApplicationMasterService;

/**
 * ApplicationMasterServiceImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Service
public class ApplicationMasterServiceImpl implements ApplicationMasterService {

	@Autowired
	private JobCategoryMstDao jobCategoryMstDao;
	
	@Autowired
	private TypeIndustryMstDao typeIndustryMstDao;
	
	@Autowired
	private LocationMstDao locationMstDao;
	
	@Autowired
	private RecruitmentAreaMstDao recruitmentAreaMstDao;
	
	@Autowired
	private YearsExperienceMstDao yearsExperienceMstDao;
	
	@Autowired
	private AgeGroupMstDao ageGroupMstDao;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ApplicationMasterService#getJobCategoryMstOfAll()
	 */
	@Override
	public List<JobCategoryMstDto> getJobCategoryMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<JobCategoryMstDto> jobCategoryMstDtoRes = jobCategoryMstDao.findAllOrderBySortNumber();
		
		return jobCategoryMstDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ApplicationMasterService#getTypeIndustryMstOfAll()
	 */
	@Override
	public List<TypeIndustryMstDto> getTypeIndustryMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<TypeIndustryMstDto> typeIndustryMstDtoRes = typeIndustryMstDao.findAllOrderBySortNumber();
		
		return typeIndustryMstDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ApplicationMasterService#getLocationMstOfAll()
	 */
	@Override
	public List<LocationMstDto> getLocationMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<LocationMstDto> locationMstDtoRes = locationMstDao.findAllOrderBySortNumber();
		
		return locationMstDtoRes;
		
	}

	@Override
	public List<RecruitmentAreaMstDto> getRecruitmentAreaMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<RecruitmentAreaMstDto> recruitmentAreaMstDtoRes = recruitmentAreaMstDao.findAllOrderBySortNumber();
		
		return recruitmentAreaMstDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ApplicationMasterService#getYearsExperienceMstOfAll()
	 */
	@Override
	public List<YearsExperienceMstDto> getYearsExperienceMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<YearsExperienceMstDto> yearsExperienceMstDtoRes = yearsExperienceMstDao.findAllOrderBySortNumber();
		
		return yearsExperienceMstDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ApplicationMasterService#getAgeGroupMstOfAll()
	 */
	@Override
	public List<AgeGroupMstDto> getAgeGroupMstOfAll()
			throws EmptyResultDataAccessException {
		
		List<AgeGroupMstDto> ageGroupMstDtoRes = ageGroupMstDao.findAllOrderBySortNumber();
		
		return ageGroupMstDtoRes;
		
	}

}
