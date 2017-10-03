package com.soramame.web.model;

import java.io.Serializable;

/**
 * years_experience_mst モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class YearsExperienceMstModel implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** years_experience_code. */
	private String yearsExperienceCode;

	/** years_experience_name. */
	private String yearsExperienceName;

	/** sort_number. */
	private Integer sortNumber;

	/** select. */
	private String select;
	
	/**
	 * コンストラクタ.
	 */
	public YearsExperienceMstModel(String yearsExperienceCode, String yearsExperienceName, String select) {
		this.yearsExperienceCode = yearsExperienceCode;
		this.yearsExperienceName = yearsExperienceName;
		
		if (select.equals(yearsExperienceCode)) {
			this.select = "selected";
			
		} else {
			this.select = "";
			
		}
		
	}

	/**
	 * years_experience_code を設定します.
	 * 
	 * @param yearsExperienceCode
	 *            years_experience_code
	 */
	public void setYearsExperienceCode(String yearsExperienceCode) {
		this.yearsExperienceCode = yearsExperienceCode;
	}

	/**
	 * years_experience_code を取得します.
	 * 
	 * @return years_experience_code
	 */
	public String getYearsExperienceCode() {
		return this.yearsExperienceCode;
	}

	/**
	 * years_experience_name を設定します.
	 * 
	 * @param yearsExperienceName
	 *            years_experience_name
	 */
	public void setYearsExperienceName(String yearsExperienceName) {
		this.yearsExperienceName = yearsExperienceName;
	}

	/**
	 * years_experience_name を取得します.
	 * 
	 * @return years_experience_name
	 */
	public String getYearsExperienceName() {
		return this.yearsExperienceName;
	}

	/**
	 * sort_number を設定します.
	 * 
	 * @param sortNumber
	 *            sort_number
	 */
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * sort_number を取得します.
	 * 
	 * @return sort_number
	 */
	public Integer getSortNumber() {
		return this.sortNumber;
	}

	/**
	 * @return the select
	 */
	public String getSelect() {
		return select;
	}

	/**
	 * @param select the select to set
	 */
	public void setSelect(String select) {
		this.select = select;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((yearsExperienceCode == null) ? 0 : yearsExperienceCode.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		YearsExperienceMstModel other = (YearsExperienceMstModel) obj;
		if (yearsExperienceCode == null) {
			if (other.yearsExperienceCode != null) {
				return false;
			}
		} else if (!yearsExperienceCode.equals(other.yearsExperienceCode)) {
			return false;
		}
		return true;
	}

}
