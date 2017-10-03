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
package com.soramame.web.biz.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.soramame.web.util.MessageUtil;
import com.soramame.web.util.PasswordHash;
import com.soramame.web.biz.dto.SimpleMailDto;
import com.soramame.web.util.SimpleMailer;
import com.soramame.web.biz.dao.NumberingDao;
import com.soramame.web.biz.dao.TransmissionHistoryDao;
import com.soramame.web.biz.dao.UserCareerOverviewDao;
import com.soramame.web.biz.dao.UserBaseDao;
import com.soramame.web.biz.dto.NumberingDto;
import com.soramame.web.biz.dto.TransmissionHistoryDto;
import com.soramame.web.biz.dto.UserCareerOverviewDto;
import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.UserService;
import com.soramame.web.exception.DuplicateException;
import com.soramame.web.exception.NumberingException;

/**
 * UserServiceImpl.
 * 
 * @author Plat.
 * @version 1.0
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private NumberingDao numberingDao;
	
	@Autowired
	private UserBaseDao userDao;

	@Autowired
	private UserCareerOverviewDao userCareerOverviewDao;

	@Autowired 
	private PasswordHash passwordHash;

	@Autowired
	private SimpleMailer simpleMailer;

	@Autowired
	private TransmissionHistoryDao transmissionHistoryDao;

	@Autowired
	private MessageUtil messageUtil;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserByUserName(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public UserBaseDto getUserByUserName(UserBaseDto userBaseDto) throws BusinessException {
		
		UserBaseDto userBaseDtoRes = null;
		try {
			userBaseDtoRes = userDao.findByPrimaryKey(userBaseDto);
			userBaseDtoRes.setPassword("");
			
		} catch (EmptyResultDataAccessException e) {
			;
			
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return userBaseDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserByRole(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public List<UserBaseDto> getUserByRole(UserBaseDto userBaseDto) throws BusinessException {
		
		List<UserBaseDto> dtoList = null;
		try {
			List<UserBaseDto> userBaseDtoRes = userDao.findByRoleOrderByUpdateDateDesc(userBaseDto);
			dtoList = new ArrayList<UserBaseDto>(userBaseDtoRes.size());
			for (UserBaseDto dto : userBaseDtoRes) {
				dto.setPassword("");
				dtoList.add(dto);
				
			}
			
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return dtoList;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#createUser(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int addUser(UserBaseDto userBaseDto) throws BusinessException {
		
		int rowCount = 0;
    	try {
			
			rowCount = userDao.getCount(userBaseDto);
			if (rowCount == 0) {
				String password = passwordHash.getStretchedPassword(
						userBaseDto.getPassword(), userBaseDto.getUserName());
				userBaseDto.setPassword(password);
				userBaseDto.setRegisterName(userBaseDto.getUserName());
				userBaseDto.setUpdateName(userBaseDto.getUserName());
				
				rowCount = userDao.create(userBaseDto);
				
				if (rowCount > 0) {
					UserBaseDto userBaseDtoRes = userDao.findByPrimaryKey(userBaseDto);
					Locale locale = Locale.getDefault();
		        	String fromAddress = messageUtil.getMessage("common.mail.from.address", null, locale);
		        	String[] toAddress = new String[1];
		        	toAddress[0] = userBaseDtoRes.getEmail();
		        	String[] bccAddress = new String[1];
		        	bccAddress[0] = messageUtil.getMessage("common.mail.bcc.address", null, locale);
		        	String[] ccAddress = null;
		        	String subject = messageUtil.getMessage("user.mail.subject.register", null, locale);
		        	String template = "user_register_email_template.vm";
		        	
		        	Map<String, String> tmplMap = new HashMap<String, String>();
		        	tmplMap.put("userName", userBaseDtoRes.getUserName());
		        	
		        	this.sendMail("00", "system", userBaseDtoRes.getUserName(), fromAddress, toAddress, ccAddress, bccAddress, subject, template, tmplMap);
	
				}
				
			} else {
				throw new BusinessException(new DuplicateException());
				
			}
			
    	} catch (DataAccessException e) {
    		throw new BusinessException("UZR_BIZ_ERR", e);
    		
    	} catch (NoSuchAlgorithmException e) {
    		throw new BusinessException("UZR_BIZ_ERR", e);
    		
		}
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#updateUser(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int updateUser(UserBaseDto userBaseDto) throws BusinessException {
		
		String password;
		int rowCount = 0;
		try {
			password = passwordHash.getStretchedPassword(
					userBaseDto.getPassword(), userBaseDto.getUserName());
			userBaseDto.setPassword(password);
			userBaseDto.setUpdateName(userBaseDto.getUserName());
			rowCount = userDao.update(userBaseDto);
		
    	} catch (DataAccessException e) {
    		throw new BusinessException("UZR_BIZ_ERR", e);
    		
		} catch (NoSuchAlgorithmException e) {
    		throw new BusinessException("UZR_BIZ_ERR", e);
    		
		}
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#deleteUser(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int deleteUser(UserBaseDto userBaseDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			rowCount = userDao.delete(userBaseDto);
			
		} catch (DataAccessException e) {
    		throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return rowCount;
		
	}
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserCareerOverviewByUserNameAndPeriodFromMin(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public UserCareerOverviewDto getUserCareerOverviewByUserNameAndPeriodFromMin(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		UserCareerOverviewDto userCareerOverviewDtoRes = null;
		try {
			userCareerOverviewDtoRes = 
					userCareerOverviewDao.findByUserNameAndPeriodFromMin(userCareerOverviewDto);
			
		} catch (EmptyResultDataAccessException e) {
			;
			
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserCareerOverviewByUserName(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public List<UserCareerOverviewDto> getUserCareerOverviewByUserName(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		List<UserCareerOverviewDto> userCareerOverviewDtoRes = null;
		try {
			userCareerOverviewDtoRes = 
					userCareerOverviewDao.findByUserNameOrderByPeriodFrom(userCareerOverviewDto);
			
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#getUserCareerOverviewByCareerId(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public UserCareerOverviewDto getUserCareerOverviewByCareerId(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		UserCareerOverviewDto userCareerOverviewDtoRes = null;
		try {
			userCareerOverviewDtoRes = 
					userCareerOverviewDao.findByCareerId(userCareerOverviewDto);
		
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#createUserCareerOverview(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int addUserCareerOverview(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			NumberingDto numberingDto = new NumberingDto();
			numberingDto.setNumberingCode("01");
			NumberingDto numberingDtoRes = numberingDao.findNumberByPrimaryKey(numberingDto);
			
			String id = numberingDtoRes.getId();
			Long idL = Long.valueOf(id) + 1;
			String newId = idL.toString();
			int lng = newId.length();
			for (int i = 0;i < 12-lng;i++) {
				newId = "0" + newId;
			}
			numberingDtoRes.setId(newId);
			numberingDao.update(numberingDtoRes);
			userCareerOverviewDto.setCareerId(newId);
			
			rowCount = userCareerOverviewDao.create(userCareerOverviewDto);
			
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", 
					new NumberingException("UZR_BIZ_ERR", e));
			
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#updateUserCareerOverview(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int updateUserCareerOverview(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			rowCount = userCareerOverviewDao.update(userCareerOverviewDto);
			
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.UserService#deleteUserCareerOverview(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int deleteUserCareerOverview(
			UserCareerOverviewDto userCareerOverviewDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			rowCount = userCareerOverviewDao.delete(userCareerOverviewDto);
		
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}
		return rowCount;
		
	}
	
	/* (non-Javadoc)
	 * @see com.edamame.web.biz.service.AccountService#sendMail(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, 
	 * java.lang.String[], java.lang.String[], java.lang.String, java.util.Map)
	 */
	@Override
	public void sendMail(String type, String sendersName, String recipientName, String fromAddress, String[] toAddress,
			String[] ccAddress, String[] bccAddress, String subject,
			String template, Map<String, String> tmplMap) throws BusinessException {
		
		try {
			NumberingDto numberingDto = new NumberingDto();
			numberingDto.setNumberingCode("03");
			NumberingDto numberingDtoRes = numberingDao.findNumberByPrimaryKey(numberingDto);
			
			String id = numberingDtoRes.getId();
			Long idL = Long.valueOf(id) + 1;
			String newId = idL.toString();
			int lng = newId.length();
			for (int i = 0;i < 12-lng;i++) {
				newId = "0" + newId;
			}
			numberingDtoRes.setId(newId);
			numberingDao.update(numberingDtoRes);
			
			TransmissionHistoryDto transmissionHistoryDto = new TransmissionHistoryDto();
			transmissionHistoryDto.setHistoryId(numberingDtoRes.getId());
			transmissionHistoryDto.setSendersName(sendersName);
			transmissionHistoryDto.setRecipientName(recipientName);
			transmissionHistoryDto.setRegisterDate(new Date(System.currentTimeMillis()));
			transmissionHistoryDto.setTransmissionType(type); // 00:user_base
			transmissionHistoryDao.create(transmissionHistoryDto);
			
			SimpleMailDto smd = new SimpleMailDto();
			smd.setFromAddress(fromAddress);
			smd.setToAddressArray(toAddress);
			smd.setCcAddressArray(ccAddress);
			smd.setBccAddressArray(bccAddress);
			smd.setSubject(subject);
			smd.setTemplateName(template);
			smd.setMailMap(tmplMap);
			simpleMailer.sendMail(smd);
			
		} catch (ResourceNotFoundException e) {
			throw new BusinessException("UZR_TMP_ERR", e);
			
		} catch(ParseErrorException e) {
			throw new BusinessException("UZR_TMP_ERR", e);
			
		} catch(MethodInvocationException e) {
			throw new BusinessException("UZR_TMP_ERR", e);
			
		} catch(MailException e) {
			throw new BusinessException("UZR_MAL_ERR", e);
			
		} catch (DataAccessException e) {
			throw new BusinessException("UZR_BIZ_ERR", e);
			
		}

	}

}
