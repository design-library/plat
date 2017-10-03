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
import java.util.List;

/**
 * UserModel
 * 
 * @author Plat.
 * @version 1.0
 */
public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** user_name. */
	private String userName;

	/** password. */
	private String password;
	
	/** role. */
	private String role;

	/** age_group. */
	private String ageGroup;

	/** birth_day. */
	private String birthDay;

	/** email. */
	private String email;

	/** live_name. */
	private String liveName;

	/** live_code. */
	private String liveCode;

	/** register_name. */
	private String registerName;

	/** register_date. */
	private Date registerDate;

	/** update_name. */
	private String updateName;

	/** update_date. */
	private Date updateDate;

	/** user_career_overview ˆê——. */
	private List<UserCareerOverviewModel> userCareerOverviewList;

	/** user_scout_counter. */
	private UserScoutCounterModel userScoutCounter;

	/** user_access_counter. */
	private UserAccessCounterModel userAccessCounter;
	
	/** verb. */
	private String verb;

	/**
	 * constractor.
	 */
	public UserModel() {
		
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the ageGroup
	 */
	public String getAgeGroup() {
		return ageGroup;
	}

	/**
	 * @param ageGroup the ageGroup to set
	 */
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the liveName
	 */
	public String getLiveName() {
		return liveName;
	}

	/**
	 * @param liveName the liveName to set
	 */
	public void setLiveName(String liveName) {
		this.liveName = liveName;
	}

	/**
	 * @return the liveCode
	 */
	public String getLiveCode() {
		return liveCode;
	}

	/**
	 * @param liveCode the liveCode to set
	 */
	public void setLiveCode(String liveCode) {
		this.liveCode = liveCode;
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
	 * @return the userCareerOverviewList
	 */
	public List<UserCareerOverviewModel> getUserCareerOverviewList() {
		return userCareerOverviewList;
	}

	/**
	 * @param userCareerOverviewList the userCareerOverviewList to set
	 */
	public void setUserCareerOverviewList(
			List<UserCareerOverviewModel> userCareerOverviewList) {
		this.userCareerOverviewList = userCareerOverviewList;
	}

	/**
	 * @return the userScoutCounter
	 */
	public UserScoutCounterModel getUserScoutCounter() {
		return userScoutCounter;
	}

	/**
	 * @param userScoutCounter the userScoutCounter to set
	 */
	public void setUserScoutCounter(UserScoutCounterModel userScoutCounter) {
		this.userScoutCounter = userScoutCounter;
	}

	/**
	 * @return the userAccessCounter
	 */
	public UserAccessCounterModel getUserAccessCounter() {
		return userAccessCounter;
	}

	/**
	 * @param userAccessCounter the userAccessCounter to set
	 */
	public void setUserAccessCounter(UserAccessCounterModel userAccessCounter) {
		this.userAccessCounter = userAccessCounter;
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
