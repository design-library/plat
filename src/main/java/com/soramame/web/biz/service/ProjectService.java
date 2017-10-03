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

import com.soramame.web.biz.dto.ProjectDto;

/**
 * ProjectService
 * 
 * @author Plat.
 * @version 1.0
 */
public interface ProjectService {
	
	/**
	 * get project by projectId.
	 * @param projectDto
	 * @return
	 * @throws BusinessException 
	 */
	public ProjectDto getProjectByProjectId(ProjectDto projectDto) throws BusinessException;
	
	/**
	 * get project by userName.
	 * @param projectDto
	 * @return
	 * @throws BusinessException 
	 */
	public List<ProjectDto> getProjectByUserName(ProjectDto projectDto) throws BusinessException;
	
	/**
	 * get project of all.
	 * @return
	 * @throws BusinessException 
	 */
	public List<ProjectDto> getProjectOfAll() throws BusinessException;
	
	/**
	 * create project.
	 * @param projectDto
	 * @return
	 * @throws BusinessException 
	 */
	public int addProject(ProjectDto projectDto)  throws BusinessException;
	
	/**
	 * update project.
	 * @param projectDto
	 * @return
	 * @throws BusinessException 
	 */
	public int updateProject(ProjectDto projectDto) throws BusinessException;
	
	/**
	 * delete project.
	 * @param projectDto
	 * @return
	 * @throws BusinessException 
	 */
	public int deleteProject(ProjectDto projectDto) throws BusinessException;
	
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
			Map<String, String> tmplMap) throws BusinessException;

}
