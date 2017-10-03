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

import com.soramame.web.biz.dao.ProjectDao;
import com.soramame.web.biz.dto.ProjectDto;

/**
 * ProjectDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#findByPrimaryKey(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public ProjectDto findByPrimaryKey(ProjectDto projectDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		ProjectDto projectDtoRes = 
				jdbcTemplate.queryForObject(
						"select * from project where project_id = ?", 
						new BeanPropertyRowMapper<ProjectDto>(ProjectDto.class),
						new Object[]{
							projectDto.getProjectId()
							});
		
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#findAllOrderByUpdateDateDesc()
	 */
	@Override
	public List<ProjectDto> findAllOrderByUpdateDateDesc() throws DataAccessException {
		
		List<ProjectDto> projectDtoRes = 
				jdbcTemplate.query(
						"select * from project order by update_date desc", 
						new BeanPropertyRowMapper<ProjectDto>(ProjectDto.class)
						);
		
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#findByUserNameOrderByUpdateDate(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public List<ProjectDto> findByUserNameOrderByUpdateDateDesc(
			ProjectDto projectDto) throws DataAccessException {
		
		List<ProjectDto> projectDtoRes = 
				jdbcTemplate.query(
						"select * from project where user_name = ? order by update_date desc", 
						new BeanPropertyRowMapper<ProjectDto>(ProjectDto.class),
						new Object[]{projectDto.getUserName()
							});
		
		return projectDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#create(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int create(ProjectDto projectDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into project values(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)",
				new Object[]{
						projectDto.getProjectId(),
						projectDto.getUserName(),
						projectDto.getTitle(),
						projectDto.getRecruitmentAreaCode(),
						projectDto.getOverView(),
						projectDto.getRegisterName(),
						projectDto.getUpdateName()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#update(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int update(ProjectDto projectDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update project set "
				+ "title = ?, "
				+ "recruitment_area_code = ?, "
				+ "over_view = ?, "
				+ "update_name = ?, "
				+ "update_date = CURRENT_TIMESTAMP "
				+ "where project_id = ?", 
				new Object[]{
						projectDto.getTitle(),
						projectDto.getRecruitmentAreaCode(),
						projectDto.getOverView(),
						projectDto.getUpdateName(),
						projectDto.getProjectId()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ProjectDao#delete(com.soramame.web.biz.dto.ProjectDto)
	 */
	@Override
	public int delete(ProjectDto projectDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from project where project_id = ?", 
				new Object[]{projectDto.getProjectId()
				});
		
		return rowCount;
		
	}

}
