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
package com.soramame.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * UserCareerOverviewModel
 * 
 * @author Plat.
 * @version 1.0
 */
public class UserCareerOverviewModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** career_id. */
	private String careerId;

	/** user_name. */
	private String userName;

	/** affiliation_name. */
	private String affiliationName;

	/** job_category_name. */
	private String jobCategoryName;
	
	/** job_category_code. */
	private String jobCategoryCode;

	/** period_from. */
	private String periodFrom;

	/** period_to. */
	private String periodTo;

	/** over_view. */
	private String overView;

	/** register_name. */
	private String registerName;

	/** register_date. */
	private Date registerDate;

	/** update_name. */
	private String updateName;

	/** update_date. */
	private Date updateDate;
	
	/** verb. */
	private String verb;
	
	/**
	 * constractor.
	 */
	public UserCareerOverviewModel() {
		
	}

	/**
	 * @return the careerId
	 */
	public String getCareerId() {
		return careerId;
	}

	/**
	 * @param careerId the careerId to set
	 */
	public void setCareerId(String careerId) {
		this.careerId = careerId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the affiliationName
	 */
	public String getAffiliationName() {
		return affiliationName;
	}

	/**
	 * @param affiliationName the affiliationName to set
	 */
	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}

	/**
	 * @return the jobCategoryName
	 */
	public String getJobCategoryName() {
		return jobCategoryName;
	}

	/**
	 * @param jobCategoryName the jobCategoryName to set
	 */
	public void setJobCategoryName(String jobCategoryName) {
		this.jobCategoryName = jobCategoryName;
	}

	/**
	 * @return the jobCategoryCode
	 */
	public String getJobCategoryCode() {
		return jobCategoryCode;
	}

	/**
	 * @param jobCategoryCode the jobCategoryCode to set
	 */
	public void setJobCategoryCode(String jobCategoryCode) {
		this.jobCategoryCode = jobCategoryCode;
	}

	/**
	 * @return the periodFrom
	 */
	public String getPeriodFrom() {
		return periodFrom;
	}

	/**
	 * @param periodFrom the periodFrom to set
	 */
	public void setPeriodFrom(String periodFrom) {
		this.periodFrom = periodFrom;
	}

	/**
	 * @return the periodTo
	 */
	public String getPeriodTo() {
		return periodTo;
	}

	/**
	 * @param periodTo the periodTo to set
	 */
	public void setPeriodTo(String periodTo) {
		this.periodTo = periodTo;
	}

	/**
	 * @return the overView
	 */
	public String getOverView() {
		return overView;
	}

	/**
	 * @param overView the overView to set
	 */
	public void setOverView(String overView) {
		this.overView = overView;
	}

	/**
	 * @return the registerName
	 */
	public String getRegisterName() {
		return registerName;
	}

	/**
	 * @param registerName the registerName to set
	 */
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	/**
	 * @return the registerDate
	 */
	public Date getRegisterDate() {
		return registerDate;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * @return the updateName
	 */
	public String getUpdateName() {
		return updateName;
	}

	/**
	 * @param updateName the updateName to set
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the verb
	 */
	public String getVerb() {
		return verb;
	}

	/**
	 * @param verb the verb to set
	 */
	public void setVerb(String verb) {
		this.verb = verb;
	}

	
}
