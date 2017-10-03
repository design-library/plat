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
package com.soramame.web.biz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soramame.web.biz.dao.ProjectAccessCounterDao;
import com.soramame.web.biz.dto.ProjectAccessCounterDto;

/**
 * ProjectAccessCounterDaoImpl
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Repository
public class ProjectAccessCounterDaoImpl implements ProjectAccessCounterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#getProjectAccessCount(java.lang.String)
	 */
	@Override
	public int getProjectAccessCount(String projectId) throws DataAccessException {
		
		int projectAccessCount = 
				jdbcTemplate.queryForInt(
						"select count(*) from project_access_counter where project_id = ?",
						new Object[]{
								projectId
								});
		
		return projectAccessCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#findAllOrderByProjectAccessCountAndUpdateDate()
	 */
	@Override
	public List<ProjectAccessCounterDto> findAllOrderByProjectAccessCountAndUpdateDate() throws DataAccessException {
		
		List<ProjectAccessCounterDto> projectAccessCounterDtoRes =
				(List<ProjectAccessCounterDto>) jdbcTemplate.query(
						"select pac.project_id, prj.title, pac.project_access_count, pac.access_date from project_access_counter pac, project prj "
						+ "where pac.project_id = prj.project_id "
						+ "order by pac.project_access_count asc, pac.access_date desc;", 
						new BeanPropertyRowMapper<ProjectAccessCounterDto>(ProjectAccessCounterDto.class));
		
		return projectAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#findByProjectId(com.soramame.web.biz.dto.ProjectAccessCounterDto)
	 */
	@Override
	public ProjectAccessCounterDto findByProjectId(ProjectAccessCounterDto projectAccessCounterDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		System.out.println("projectId=" + projectAccessCounterDto.getProjectId());
		ProjectAccessCounterDto projectAccessCounterDtoRes =
				(ProjectAccessCounterDto) jdbcTemplate.queryForObject(
						"select * from project_access_counter where project_id = ?", 
						new BeanPropertyRowMapper<ProjectAccessCounterDto>(ProjectAccessCounterDto.class),
						new Object[]{
							projectAccessCounterDto.getProjectId()
							});
		
		return projectAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#create(com.soramame.web.biz.dto.ProjectAccessCounterDto)
	 */
	@Override
	public int create(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into project_access_counter values(?, ?, CURRENT_TIMESTAMP)", 
				new Object[]{
						projectAccessCounterDto.getProjectId(),
						projectAccessCounterDto.getProjectAccessCount()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#update(com.soramame.web.biz.dto.ProjectAccessCounterDto)
	 */
	@Override
	public int update(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update project_access_counter set "
				+ "project_access_count = ?, "
				+ "access_date = CURRENT_TIMESTAMP "
				+ "where project_id = ?",
				new Object[]{
						projectAccessCounterDto.getProjectAccessCount(),
						projectAccessCounterDto.getProjectId()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectAccessCounterDao#delete(com.soramame.web.biz.dto.ProjectAccessCounterDto)
	 */
	@Override
	public int delete(ProjectAccessCounterDto projectAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from project_access_counter where project_id = ?",
				new Object[]{
						projectAccessCounterDto.getProjectId()
				});
		
		return rowCount;
		
	}

}
