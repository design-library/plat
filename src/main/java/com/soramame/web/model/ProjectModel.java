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

import com.soramame.web.model.ParticipationHopeCounterModel;
import com.soramame.web.model.ProjectAccessCounterModel;

/**
 * ProjectModel
 * 
 * @author Plat.
 * @version 1.0
 */
public class ProjectModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** project_id. */
	private String projectId;

	/** user_name. */
	private String userName;

	/** title. */
	private String title;

	/** recruitment_area_code. */
	private String recruitmentAreaCode;

	/** recruitment_area_name. */
	private String recruitmentAreaName;

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

	/** project_access_counter. */
	private ProjectAccessCounterModel projectAccessCounter;

	/** participation_hope_counter. */
	private ParticipationHopeCounterModel participationHopeCounter;
	
	private String verb;
	
	/**
	 * constractor
	 */
	public ProjectModel() {
		
	}

	/**
	 * @return the projectId
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the recruitmentAreaCode
	 */
	public String getRecruitmentAreaCode() {
		return recruitmentAreaCode;
	}

	/**
	 * @param recruitmentAreaCode the recruitmentAreaCode to set
	 */
	public void setRecruitmentAreaCode(String recruitmentAreaCode) {
		this.recruitmentAreaCode = recruitmentAreaCode;
	}

	/**
	 * @return the recruitmentAreaName
	 */
	public String getRecruitmentAreaName() {
		return recruitmentAreaName;
	}

	/**
	 * @param recruitmentAreaName the recruitmentAreaName to set
	 */
	public void setRecruitmentAreaName(String recruitmentAreaName) {
		this.recruitmentAreaName = recruitmentAreaName;
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
	 * @return the projectAccessCounter
	 */
	public ProjectAccessCounterModel getProjectAccessCounter() {
		return projectAccessCounter;
	}

	/**
	 * @param projectAccessCounter the projectAccessCounter to set
	 */
	public void setProjectAccessCounter(ProjectAccessCounterModel projectAccessCounter) {
		this.projectAccessCounter = projectAccessCounter;
	}

	/**
	 * @return the participationHopeCounter
	 */
	public ParticipationHopeCounterModel getParticipationHopeCounter() {
		return participationHopeCounter;
	}

	/**
	 * @param participationHopeCounter the participationHopeCounter to set
	 */
	public void setParticipationHopeCounter(
			ParticipationHopeCounterModel participationHopeCounter) {
		this.participationHopeCounter = participationHopeCounter;
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
