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

import com.soramame.web.biz.dto.ProjectDto;

/**
 * ProjectDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface ProjectDao {
	
	/**
	 * find by primarykey.
	 * @param projectDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public ProjectDto findByPrimaryKey(ProjectDto projectDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * find all, order by updateDate.
	 * @return
	 * @throws DataAccessException
	 */
	public List<ProjectDto> findAllOrderByUpdateDateDesc() throws DataAccessException;
	
	/**
	 * find by userName, order by updateDate.
	 * @param projectDto
	 * @return
	 * @throws DataAccessException
	 */
	public List<ProjectDto> findByUserNameOrderByUpdateDateDesc(ProjectDto projectDto) throws DataAccessException ;	
	/**
	 * create.
	 * @param projectDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(ProjectDto projectDto);
	
	/**
	 * update by primarykey.
	 * @param projectDto
	 * @return
	 * @throws DataAccessException
	 */
	public int update(ProjectDto projectDto);
	
	/**
	 * delete by primarykey.
	 * @param projectDto
	 * @return
	 * @throws DataAccessException
	 */
	public int delete(ProjectDto projectDto);

}
