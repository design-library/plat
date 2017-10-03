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

import java.util.Date;
import java.util.List;
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

import com.soramame.web.biz.dao.NumberingDao;
import com.soramame.web.biz.dao.ProjectDao;
import com.soramame.web.biz.dao.TransmissionHistoryDao;
import com.soramame.web.biz.dto.NumberingDto;
import com.soramame.web.biz.dto.ProjectDto;
import com.soramame.web.biz.dto.SimpleMailDto;
import com.soramame.web.biz.dto.TransmissionHistoryDto;
import com.soramame.web.biz.service.BusinessException;
import com.soramame.web.biz.service.ProjectService;
import com.soramame.web.exception.NumberingException;
import com.soramame.web.util.SimpleMailer;

/**
 * ProjectServiceImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private NumberingDao numberingDao;

	@Autowired
	private SimpleMailer simpleMailer;
	
	@Autowired
	private TransmissionHistoryDao transmissionHistoryDao;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getProjectByProjectId(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public ProjectDto getProjectByProjectId(ProjectDto projectDto) throws BusinessException {
		
		ProjectDto projectDtoRes = null;
		try {
			projectDtoRes = projectDao.findByPrimaryKey(projectDto);
			
		} catch (EmptyResultDataAccessException e) {
			;

		} catch (IncorrectResultSizeDataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getProjectByUserName(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public List<ProjectDto> getProjectByUserName(ProjectDto projectDto) throws BusinessException {
		
		List<ProjectDto> projectDtoRes = null;
		try {
			projectDtoRes = projectDao.findByUserNameOrderByUpdateDateDesc(projectDto);
			
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#getProjectOfAll()
	 */
	@Override
	public List<ProjectDto> getProjectOfAll() throws BusinessException {
		
		List<ProjectDto> projectDtoRes = null;
		try {
			projectDtoRes = projectDao.findAllOrderByUpdateDateDesc();
		
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#createProject(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int addProject(ProjectDto projectDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			NumberingDto numberingDto = new NumberingDto();
			numberingDto.setNumberingCode("02");
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
			projectDto.setProjectId(newId);
			
			rowCount = projectDao.create(projectDto);
			
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", 
					new NumberingException("PRJ_BIZ_ERR", e));
			
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#updateProject(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int updateProject(ProjectDto projectDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			rowCount = projectDao.update(projectDto);
			
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		return rowCount;

	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#deleteProject(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int deleteProject(ProjectDto projectDto) throws BusinessException {
		
		int rowCount = 0;
		try {
			rowCount = projectDao.delete(projectDto);
			
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}
		return rowCount;
		
	}
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.ProjectService#sendMail(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, 
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
			transmissionHistoryDto.setTransmissionType(type); // project:02, player:01.
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
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		} catch(ParseErrorException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		} catch(MethodInvocationException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		} catch(MailException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		} catch (DataAccessException e) {
			throw new BusinessException("PRJ_BIZ_ERR", e);
			
		}

	}
}
