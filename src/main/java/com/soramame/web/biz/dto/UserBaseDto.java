package com.soramame.web.biz.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * player_base ���f���N���X.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class UserBaseDto implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** user_name. */
	private String userName;

	/** password. */
	private String password;

	/** role. */
	private String role;

	/** birth_day. */
	private String birthDay;

	/** email. */
	private String email;

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

	/** player_career_overview �ꗗ. */
	private List<UserCareerOverviewDto> playerCareerOverviewList;

	/** player_scout_counter. */
	private UserScoutCounterDto playerScoutCounter;

	/** player_access_counter. */
	private UserAccessCounterDto playerAccessCounter;

	/**
	 * �R���X�g���N�^.
	 */
	public UserBaseDto() {
		this.playerCareerOverviewList = new ArrayList<UserCareerOverviewDto>();
	}

	/**
	 * user_name ��ݒ肵�܂�.
	 * 
	 * @param userName
	 *            user_name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * user_name ���擾���܂�.
	 * 
	 * @return user_name
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * password ��ݒ肵�܂�.
	 * 
	 * @param password
	 *            password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * password ���擾���܂�.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return this.password;
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
	 * birth_day ��ݒ肵�܂�.
	 * 
	 * @param birthDay
	 *            birth_day
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * birth_day ���擾���܂�.
	 * 
	 * @return birth_day
	 */
	public String getBirthDay() {
		return this.birthDay;
	}

	/**
	 * email ��ݒ肵�܂�.
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * email ���擾���܂�.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * live_code ��ݒ肵�܂�.
	 * 
	 * @param liveCode
	 *            live_code
	 */
	public void setLiveCode(String liveCode) {
		this.liveCode = liveCode;
	}

	/**
	 * live_code ���擾���܂�.
	 * 
	 * @return live_code
	 */
	public String getLiveCode() {
		return this.liveCode;
	}

	/**
	 * register_name ��ݒ肵�܂�.
	 * 
	 * @param registerName
	 *            register_name
	 */
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	/**
	 * register_name ���擾���܂�.
	 * 
	 * @return register_name
	 */
	public String getRegisterName() {
		return this.registerName;
	}

	/**
	 * register_date ��ݒ肵�܂�.
	 * 
	 * @param registerDate
	 *            register_date
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * register_date ���擾���܂�.
	 * 
	 * @return register_date
	 */
	public Date getRegisterDate() {
		return this.registerDate;
	}

	/**
	 * update_name ��ݒ肵�܂�.
	 * 
	 * @param updateName
	 *            update_name
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}

	/**
	 * update_name ���擾���܂�.
	 * 
	 * @return update_name
	 */
	public String getUpdateName() {
		return this.updateName;
	}

	/**
	 * update_date ��ݒ肵�܂�.
	 * 
	 * @param updateDate
	 *            update_date
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * update_date ���擾���܂�.
	 * 
	 * @return update_date
	 */
	public Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * player_career_overview �ꗗ��ݒ肵�܂�.
	 * 
	 * @param playerCareerOverviewList
	 *            player_career_overview �ꗗ
	 */
	public void setPlayerCareerOverviewList(List<UserCareerOverviewDto> playerCareerOverviewList) {
		this.playerCareerOverviewList = playerCareerOverviewList;
	}

	/**
	 * player_career_overview ��ǉ����܂�.
	 * 
	 * @param playerCareerOverview
	 *            player_career_overview
	 */
	public void addPlayerCareerOverview(UserCareerOverviewDto playerCareerOverview) {
		this.playerCareerOverviewList.add(playerCareerOverview);
	}

	/**
	 * player_career_overview �ꗗ���擾���܂�.
	 * 
	 * @return player_career_overview �ꗗ
	 */
	public List<UserCareerOverviewDto> getPlayerCareerOverviewList() {
		return this.playerCareerOverviewList;
	}

	/**
	 * player_scout_counter ��ݒ肵�܂�.
	 * 
	 * @param playerScoutCounter
	 *            player_scout_counter
	 */
	public void setPlayerScoutCounter(UserScoutCounterDto playerScoutCounter) {
		this.playerScoutCounter = playerScoutCounter;
	}

	/**
	 * player_scout_counter ���擾���܂�.
	 * 
	 * @return player_scout_counter
	 */
	public UserScoutCounterDto getPlayerScoutCounter() {
		return this.playerScoutCounter;
	}

	/**
	 * player_access_counter ��ݒ肵�܂�.
	 * 
	 * @param playerAccessCounter
	 *            player_access_counter
	 */
	public void setPlayerAccessCounter(UserAccessCounterDto playerAccessCounter) {
		this.playerAccessCounter = playerAccessCounter;
	}

	/**
	 * player_access_counter ���擾���܂�.
	 * 
	 * @return player_access_counter
	 */
	public UserAccessCounterDto getPlayerAccessCounter() {
		return this.playerAccessCounter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
		UserBaseDto other = (UserBaseDto) obj;
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

}