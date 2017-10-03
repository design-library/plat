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
package com.soramame.web.biz.service;

import java.util.List;
import java.util.Map;

import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.dto.UserCareerOverviewDto;

/**
 * UserService
 * 
 * @author Plat.
 * @version 1.0
 */
public interface UserService {
	
	/**
	 * get user by userName.
	 * @param userBaseDto
	 * @return
	 * @throws BusinessException 
	 */
	public UserBaseDto getUserByUserName(UserBaseDto userBaseDto) throws BusinessException;
	
	/**
	 * get user by role.
	 * @param userBaseDto
	 * @return
	 * @throws BusinessException 
	 */
	public List<UserBaseDto> getUserByRole(UserBaseDto userBaseDto) throws BusinessException;
	
	/**
	 * create user.
	 * @param userBaseDto
	 * @throws BusinessException
	 * @return
	 */
	public int addUser(UserBaseDto userBaseDto) throws BusinessException;
	
	/**
	 * update user.
	 * @param userBaseDto
	 * @return
	 * @throws BusinessException 
	 */
	public int updateUser(UserBaseDto userBaseDto) throws BusinessException;
	
	/**
	 * delete userBaseDto.
	 * @param userBaseDto
	 * @return
	 * @throws BusinessException 
	 */
	public int deleteUser(UserBaseDto userBaseDto) throws BusinessException;
	
	/**
	 * 
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public UserCareerOverviewDto getUserCareerOverviewByUserNameAndPeriodFromMin(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * get userCareerOverview by userName.
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public List<UserCareerOverviewDto> getUserCareerOverviewByUserName(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * get userCareerOverview by careerId.
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public UserCareerOverviewDto getUserCareerOverviewByCareerId(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * create userCareerOverview.
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public int addUserCareerOverview(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * update userCareerOverview.
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public int updateUserCareerOverview(UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * delete userCareerOverview.
	 * @param userCareerOverviewDto
	 * @return
	 * @throws BusinessException 
	 */
	public int deleteUserCareerOverview(UserCareerOverviewDto userCareerOverviewDto) throws BusinessException;
	
	/**
	 * send mail.
	 * @param type
	 * @param sendersName
	 * @param recipientName
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @param bccAddress
	 * @param subject
	 * @param template
	 * @param tmplMap
	 * @throws BusinessException
	 */
	public void sendMail(String type, String sendersName, String recipientName, String fromAddress, String[] toAddress,
			String[] ccAddress, String[] bccAddress, String subject, String template,
			Map<String, String> tmplMap) throws BusinessException ;

}
