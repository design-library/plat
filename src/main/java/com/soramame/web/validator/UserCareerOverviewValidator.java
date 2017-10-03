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
package com.soramame.web.validator;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soramame.web.model.UserCareerOverviewModel;

/**
 * validation of UserCareerOverview.
 * 
 * @version 1.0
 * @author Plat.
 */
@Component
public class UserCareerOverviewValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		UserCareerOverviewModel userCareerOverviewModel = (UserCareerOverviewModel)target;
		
		affiliationNameValidate(userCareerOverviewModel.getAffiliationName(), errors);
		
		jobCategoryCodeValidate(userCareerOverviewModel.getJobCategoryCode(), errors);
			
		periodFromToValidate(
				userCareerOverviewModel.getPeriodFrom(), 
				userCareerOverviewModel.getPeriodTo(), 
				errors);
		
		overViewValidate(userCareerOverviewModel.getOverView(), errors);
			
	}

	private void affiliationNameValidate(String affiliationName, Errors errors) {
		
		if(StringUtils.isBlank(affiliationName)) {
			errors.rejectValue("affiliationName", "user_career_overview.message.affiliation_name.required");
			return;
			
		}
		if(affiliationName.length() > 32) {
			errors.rejectValue("affiliationName", "user_career_overview.message.affiliation_name.length");
			return;
			
		}
		return;
		
	}
	
	private void jobCategoryCodeValidate(String jobCategoryCode, Errors errors) {
		if(StringUtils.isBlank(jobCategoryCode)) {
			errors.rejectValue("jobCategoryCode", "user_career_overview.message.job_category_code.required");
			return;
			
		}
		return;
		
	}

	private void periodFromToValidate(String periodFrom, String periodTo, Errors errors)  {
		
		if(StringUtils.isBlank(periodFrom)) {
			errors.rejectValue("periodFrom", "user_career_overview.message.period_from.required");
			return;
			
		}
		Date from = null;
		try {
			from = DateUtils.parseDateStrictly(periodFrom, new String[] {"yyyy/MM/dd"});
			
		} catch (ParseException e) {
			errors.rejectValue("periodFrom", "user_career_overview.message.period_from.formal");
			return;
			
		}
		
		if(StringUtils.isBlank(periodTo)) {
			errors.rejectValue("periodTo", "user_career_overview.message.period_to.required");
			return;
			
		}
		Date to = null;
		try {
			to = DateUtils.parseDateStrictly(periodTo, new String[] {"yyyy/MM/dd"});
			
		} catch (ParseException e) {
			errors.rejectValue("periodTo", "user_career_overview.message.period_to.formal");
			return;
			
		}
		
		if(from.compareTo(to) > 0) {
			errors.rejectValue("periodTo", "user_career_overview.message.period_to.compare");
			return;
			
		}
		
	}
	
	private void overViewValidate(String overView, Errors errors) {
		
		if(StringUtils.isBlank(overView)) {
			errors.rejectValue("overView", "user_career_overview.message.over_view.required");
			return;
			
		}
		if(overView.length() > 400) {
			errors.rejectValue("overView", "user_career_overview.message.over_view.maxlength");
			return;
			
		}
		
	}

}
