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
package com.soramame.web.biz.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.soramame.web.biz.dto.ProjectAccessCounterDto;


/**
 * ProjectAccessCounterDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface ProjectAccessCounterDao {
	
	/**
	 * get ProjectAccessCount.
	 * @param projectId
	 * @return
	 * @throws DataAccessException
	 */
	public int getProjectAccessCount(String projectId) throws DataAccessException;
	
	/**
	 * find All order by ProjectAccessCount, updateDate.
	 * @return
	 * @throws DataAccessException
	 */
	public List<ProjectAccessCounterDto> findAllOrderByProjectAccessCountAndUpdateDate() throws DataAccessException;
	
	/**
	 * find by projectId.
	 * @param projectAccessCounterDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public ProjectAccessCounterDto findByProjectId(ProjectAccessCounterDto projectAccessCounterDto) 
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * create.
	 * @param projectAccessCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException;
	
	/**
	 * update by primarykey.
	 * @param projectAccessCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int update(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException;
	
	/**
	 * delete by primarykey.
	 * @param projectAccessCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int delete(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException;

}
