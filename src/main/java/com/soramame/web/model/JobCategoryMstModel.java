package com.soramame.web.model;

import java.io.Serializable;

/**
 * job_category_mst ���f���N���X.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class JobCategoryMstModel implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** job_category_code. */
	private String jobCategoryCode;

	/** job_category_name. */
	private String jobCategoryName;

	/** sort_number. */
	private Integer sortNumber;
	
	/** select. */
	private String select;

	/**
	 * Constructor
	 * @param jobCategoryCode
	 * @param jobCategoryName
	 * @param select
	 */
	public JobCategoryMstModel(String jobCategoryCode, String jobCategoryName, String select) {
		this.jobCategoryCode = jobCategoryCode;
		this.jobCategoryName = jobCategoryName;
		
		if(select.equals(jobCategoryCode)) {
			this.select = "selected";
			
		} else {
			this.select = "";
			
		}
		
	}

	/**
	 * job_category_code ��ݒ肵�܂�.
	 * 
	 * @param jobCategoryCode
	 *            job_category_code
	 */
	public void setJobCategoryCode(String jobCategoryCode) {
		this.jobCategoryCode = jobCategoryCode;
	}

	/**
	 * job_category_code ���擾���܂�.
	 * 
	 * @return job_category_code
	 */
	public String getJobCategoryCode() {
		return this.jobCategoryCode;
	}

	/**
	 * job_category_name ��ݒ肵�܂�.
	 * 
	 * @param jobCategoryName
	 *            job_category_name
	 */
	public void setJobCategoryName(String jobCategoryName) {
		this.jobCategoryName = jobCategoryName;
	}

	/**
	 * job_category_name ���擾���܂�.
	 * 
	 * @return job_category_name
	 */
	public String getJobCategoryName() {
		return this.jobCategoryName;
	}

	/**
	 * sort_number ��ݒ肵�܂�.
	 * 
	 * @param sortNumber
	 *            sort_number
	 */
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	/**
	 * sort_number ���擾���܂�.
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
		result = prime * result + ((jobCategoryCode == null) ? 0 : jobCategoryCode.hashCode());
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
		JobCategoryMstModel other = (JobCategoryMstModel) obj;
		if (jobCategoryCode == null) {
			if (other.jobCategoryCode != null) {
				return false;
			}
		} else if (!jobCategoryCode.equals(other.jobCategoryCode)) {
			return false;
		}
		return true;
	}

}