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
package com.soramame.web.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.soramame.web.model.AgeGroupMstModel;
import com.soramame.web.model.JobCategoryMstModel;
import com.soramame.web.model.LocationMstModel;
import com.soramame.web.model.RecruitmentAreaMstModel;
import com.soramame.web.model.TypeIndustryMstModel;
import com.soramame.web.model.YearsExperienceMstModel;

/**
 * application utility.
 * 
 * @version 1.0
 * @author Plat.
 */
@Component
public class ApplicationUtil {
	
	private static String HYPHEN = "-";
	
    /**
	 * convert to years experience name
     * @param yearsExperienceModelMap
     * @param yearsExperienceCode
     * @return
     */
	public String convToYearsExperienceName(
			Map<String, YearsExperienceMstModel> yearsExperienceModelMap, String yearsExperienceCode) {
		
		YearsExperienceMstModel model = null;
		if(yearsExperienceModelMap.containsKey(yearsExperienceCode)) {
			model = (YearsExperienceMstModel) yearsExperienceModelMap.get(yearsExperienceCode);
			return model.getYearsExperienceName();
			
		} else {
			return HYPHEN;
			
		}
		
	}
	
	/**
	 * convert to job category name
	 * @param jobCategoryModelMap
	 * @param jobCategoryCode
	 * @return
	 */
	public String convToJobCategoryName(
			Map<String, JobCategoryMstModel> jobCategoryModelMap, String jobCategoryCode) {
		
		JobCategoryMstModel model = null;
		if(jobCategoryModelMap.containsKey(jobCategoryCode)) {
			model = (JobCategoryMstModel) jobCategoryModelMap.get(jobCategoryCode);
			return model.getJobCategoryName();
			
		} else {
			return HYPHEN;
			
		}
		
	}
	
	/**
	 * convert to live name
	 * @param locationModelMap
	 * @param liveCode
	 * @return
	 */
	public String convToLiveName(Map<String, LocationMstModel> locationModelMap, String liveCode) {
		
		LocationMstModel model = null;
		if(locationModelMap.containsKey(liveCode)) {
			model = (LocationMstModel) locationModelMap.get(liveCode);
			return model.getLocationName();
			
		} else {
			return HYPHEN;
			
		}
		
	}
	
	/**
	 * convert to recruitmentArea name
	 * @param recruitmentAreaModelMap
	 * @param recruitmentAreaCode
	 * @return
	 */
	public String convToRecruitmentAreaName(Map<String, RecruitmentAreaMstModel> recruitmentAreaModelMap, String recruitmentAreaCode) {
		
		RecruitmentAreaMstModel model = null;
		if(recruitmentAreaModelMap.containsKey(recruitmentAreaCode)) {
			model = (RecruitmentAreaMstModel) recruitmentAreaModelMap.get(recruitmentAreaCode);
			return model.getRecruitmentAreaName();
			
		} else {
			return HYPHEN;
			
		}
		
	}
	
	/**
	 * convert to age group
	 * @param ageGroupModelMap
	 * @param birthDay
	 * @return
	 */
	public String convToAgeGrp(Map<String, AgeGroupMstModel> ageGroupModelMap, String birthDay) {
		String[] birthDayArray =  birthDay.split("/");
		String age = getAge(birthDayArray[0], birthDayArray[1], birthDayArray[2]);
		if (Integer.parseInt(age) < 20) {
			return ageGroupModelMap.get("00").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 20 && Integer.parseInt(age) < 25) {
			return ageGroupModelMap.get("01").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 25 && Integer.parseInt(age) < 30) {
			return ageGroupModelMap.get("02").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 30 && Integer.parseInt(age) < 35) {
			return ageGroupModelMap.get("03").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 35 && Integer.parseInt(age) < 40) {
			return ageGroupModelMap.get("04").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 40 && Integer.parseInt(age) < 45) {
			return ageGroupModelMap.get("05").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 45 && Integer.parseInt(age) < 50) {
			return ageGroupModelMap.get("06").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 50 && Integer.parseInt(age) < 55) {
			return ageGroupModelMap.get("07").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 55 && Integer.parseInt(age) < 60) {
			return ageGroupModelMap.get("08").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 60 && Integer.parseInt(age) < 65) {
			return ageGroupModelMap.get("09").getAgeGroupName();
		} else if (Integer.parseInt(age) >= 65) {
			return ageGroupModelMap.get("10").getAgeGroupName();
		} else {
			return "•s–¾";
		}
	}

	/**
	 * @param y
	 * @param m
	 * @param d
	 */
	private String getAge(String y, String m, String d) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int mon = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		return "" + (year 
				- Integer.parseInt(y) 
				- ((Integer.parseInt(m) > mon 
						|| (Integer.parseInt(m) == mon 
						&& Integer.parseInt(d) > day)) ? 1 : 0));
	}

	/**
	 * get live model.
	 * @param locationModellList
	 * @param liveCode
	 * @return
	 */
	public List<LocationMstModel> getLiveModel(
    		List<LocationMstModel> locationModellList, String liveCode) {
    	
    	if (liveCode == null) liveCode = "";
    	List<LocationMstModel> liveList = new ArrayList<LocationMstModel>();
    	for (LocationMstModel model : locationModellList) {
    		liveList.add(
    				new LocationMstModel(
    						model.getLocationCode(), model.getLocationName(), liveCode));
    		
    	}
    	return liveList;
    	
    }

	/**
	 * get recruitmentArea model.
	 * @param recruitmentAreaModelList
	 * @param recruitmentAreaCode
	 * @return
	 */
	public List<RecruitmentAreaMstModel> getRecruitmentAreaModel(
    		List<RecruitmentAreaMstModel> recruitmentAreaModelList, String recruitmentAreaCode) {
    	
    	if (recruitmentAreaCode == null) recruitmentAreaCode = "";
    	List<RecruitmentAreaMstModel> recruitmentAreaList = new ArrayList<RecruitmentAreaMstModel>();
    	for (RecruitmentAreaMstModel model : recruitmentAreaModelList) {
    		recruitmentAreaList.add(
    				new RecruitmentAreaMstModel(
    						model.getRecruitmentAreaCode(), model.getRecruitmentAreaName(), recruitmentAreaCode));
    		
    	}
    	return recruitmentAreaList;
    	
    }
    
	/**
	 * get job category model.
	 * @param jobCategoryModellList
	 * @param jobCategoryCode
	 * @return
	 */
	public List<JobCategoryMstModel> getJobCategoryModel(
    		List<JobCategoryMstModel> jobCategoryModellList, String jobCategoryCode) {
    	
    	if (jobCategoryCode == null) jobCategoryCode = "";
    	List<JobCategoryMstModel> jobCategory = new ArrayList<JobCategoryMstModel>();
    	for (JobCategoryMstModel model : jobCategoryModellList) {
    		jobCategory.add(new JobCategoryMstModel(model.getJobCategoryCode(), model.getJobCategoryName(), jobCategoryCode));
    	}
    	return jobCategory;
    	
    }
    
	/**
	 * get years experience model.
	 * @param yearsExperienceModelList
	 * @param yearsExperienceCode
	 * @return
	 */
	public List<YearsExperienceMstModel> getYearsExperienceModel(
    		List<YearsExperienceMstModel> yearsExperienceModelList, String yearsExperienceCode) {
    	
    	if (yearsExperienceCode == null) yearsExperienceCode = "";
    	List<YearsExperienceMstModel> yearsExperience = new ArrayList<YearsExperienceMstModel>();
    	for (YearsExperienceMstModel model : yearsExperienceModelList) {
    		yearsExperience.add(new YearsExperienceMstModel(model.getYearsExperienceCode(), model.getYearsExperienceName(), yearsExperienceCode));
    	}
    	return yearsExperience;
    	
    }
	
	/**
	 * convert to type industry name.
	 * @param typeIndustryModelMap
	 * @param typeIndustryCode
	 * @return
	 */
	public String convToTypeIndustryName(
			Map<String, TypeIndustryMstModel> typeIndustryModelMap, String typeIndustryCode) {
		
		TypeIndustryMstModel model = null;
		if(typeIndustryModelMap.containsKey(typeIndustryCode)) {
			model = (TypeIndustryMstModel) typeIndustryModelMap.get(typeIndustryCode);
			return model.getTypeIndustryName();
			
		} else {
			return HYPHEN;
			
		}
		
	}
    
	/**
	 * get type industry model
	 * @param typeIndustryModellList
	 * @param typeIndustryCode
	 * @return
	 */
    public List<TypeIndustryMstModel> getTypeIndustryModel(
    		List<TypeIndustryMstModel> typeIndustryModellList, String typeIndustryCode) {
    	
    	if (typeIndustryCode == null) typeIndustryCode = "";
    	List<TypeIndustryMstModel> typeIndustry = new ArrayList<TypeIndustryMstModel>();
    	for (TypeIndustryMstModel model : typeIndustryModellList) {
    		typeIndustry.add(new TypeIndustryMstModel(model.getTypeIndustryCode(), model.getTypeIndustryName(), typeIndustryCode));
    	}
    	return typeIndustry;
    	
    }
	
}
