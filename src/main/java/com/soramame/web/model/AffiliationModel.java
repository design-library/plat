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
 * AffiliationModel
 * 
 * @author Plat.
 * @version 1.0
 */
public class AffiliationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** user_name. */
	private String userName;
	
	/** affiliation_name. */
	private String affiliationName;

	/** address. */
	private String address;

	/** type_industry_code. */
	private String typeIndustryCode;

	/** activities. */
	private String activities;

	/** register_name. */
	private String registerName;

	/** register_date. */
	private Date registerDate;

	/** update_name. */
	private String updateName;

	/** update_date. */
	private Date updateDate;

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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the typeIndustryCode
	 */
	public String getTypeIndustryCode() {
		return typeIndustryCode;
	}

	/**
	 * @param typeIndustryCode the typeIndustryCode to set
	 */
	public void setTypeIndustryCode(String typeIndustryCode) {
		this.typeIndustryCode = typeIndustryCode;
	}

	/**
	 * @return the activities
	 */
	public String getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(String activities) {
		this.activities = activities;
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
	
}
