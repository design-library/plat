package com.soramame.web.biz.dto;

import java.io.Serializable;

/**
 * location_mst ���f���N���X.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class LocationMstDto implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** location_code. */
	private String locationCode;

	/** location_name. */
	private String locationName;

	/** sort_number. */
	private Integer sortNumber;

	/**
	 * �R���X�g���N�^.
	 */
	public LocationMstDto() {
	}

	/**
	 * location_code ��ݒ肵�܂�.
	 * 
	 * @param locationCode
	 *            location_code
	 */
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * location_code ���擾���܂�.
	 * 
	 * @return location_code
	 */
	public String getLocationCode() {
		return this.locationCode;
	}

	/**
	 * location_name ��ݒ肵�܂�.
	 * 
	 * @param locationName
	 *            location_name
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * location_name ���擾���܂�.
	 * 
	 * @return location_name
	 */
	public String getLocationName() {
		return this.locationName;
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
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((locationCode == null) ? 0 : locationCode.hashCode());
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
		LocationMstDto other = (LocationMstDto) obj;
		if (locationCode == null) {
			if (other.locationCode != null) {
				return false;
			}
		} else if (!locationCode.equals(other.locationCode)) {
			return false;
		}
		return true;
	}

}