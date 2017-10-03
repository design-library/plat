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

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.soramame.web.model.ProjectModel;

/**
 * validation of Project.
 * 
 * @version 1.0
 * @author Plat.
 */
@Component
public class ProjectValidator implements Validator {

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

		ProjectModel projectModel = (ProjectModel)target;
		
		titleValidate(projectModel.getTitle(), errors);
		
		recruitmentAreaCodeValidate(projectModel.getRecruitmentAreaCode(), errors);
		
		overViewValidate(projectModel.getOverView(), errors);
			
	}

	private void titleValidate(String title, Errors errors) {
		
		if(StringUtils.isBlank(title)) {
			errors.rejectValue("title", "projects.message.title.required");
			return;
			
		}
		if(title.length() > 128) {
			errors.rejectValue("title", "projects.message.title.length");
			return;
			
		}
		return;
		
	}
	
	private void recruitmentAreaCodeValidate(String recruitmentAreaCode, Errors errors) {
		if(StringUtils.isBlank(recruitmentAreaCode)) {
			errors.rejectValue("recruitmentAreaCode", "projects.message.recruitment_area_code.required");
			return;
			
		}
		return;
		
	}
	
	private void overViewValidate(String overView, Errors errors) {
		
		if(StringUtils.isBlank(overView)) {
			errors.rejectValue("overView", "projects.message.over_view.required");
			return;
			
		}
		if(overView.length() > 400) {
			errors.rejectValue("overView", "projects.message.over_view.maxlength");
			return;
			
		}
		
	}

}
