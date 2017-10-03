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

import com.soramame.web.biz.dao.ParticipationHopeCounterDao;
import com.soramame.web.biz.dto.ParticipationHopeCounterDto;

/**
 * ParticipationHopeCounterDaoImpl
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Repository
public class ParticipationHopeCounterDaoImpl implements ParticipationHopeCounterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#getParticipationHopeCount(java.lang.String)
	 */
	@Override
	public int getParticipationHopeCount(String projectId) throws DataAccessException {
		
		int participationHopeCount = 
				jdbcTemplate.queryForInt(
						"select count(*) from participation_hope_counter where project_id = ?",
						new Object[]{
								projectId
								});
		
		return participationHopeCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#findAllOrderByParticipationHopeCountAndUpdateDate()
	 */
	@Override
	public List<ParticipationHopeCounterDto> findAllOrderByParticipationHopeCountAndUpdateDate() throws DataAccessException {
		
		List<ParticipationHopeCounterDto> participationHopeCounterDtoRes =
				(List<ParticipationHopeCounterDto>) jdbcTemplate.query(
						"select * from participation_hope_counter order by participation_hope_count asc, participation_hope_date desc", 
						new BeanPropertyRowMapper<ParticipationHopeCounterDto>(ParticipationHopeCounterDto.class));
		
		return participationHopeCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#findByProjectId(com.soramame.web.biz.dto.ParticipationHopeCounterDto)
	 */
	@Override
	public ParticipationHopeCounterDto findByProjectId(ParticipationHopeCounterDto participationHopeCounterDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		ParticipationHopeCounterDto participationHopeCounterDtoRes =
				(ParticipationHopeCounterDto) jdbcTemplate.queryForObject(
						"select * from participation_hope_counter where project_id = ?", 
						new BeanPropertyRowMapper<ParticipationHopeCounterDto>(ParticipationHopeCounterDto.class),
						new Object[]{
							participationHopeCounterDto.getProjectId()
							}
						);
		
		return participationHopeCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#create(com.soramame.web.biz.dto.ParticipationHopeCounterDto)
	 */
	@Override
	public int create(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into participation_hope_counter values(?, ?, CURRENT_TIMESTAMP)", 
				new Object[]{
						participationHopeCounterDto.getProjectId(),
						participationHopeCounterDto.getParticipationHopeCount()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#update(com.soramame.web.biz.dto.ParticipationHopeCounterDto)
	 */
	@Override
	public int update(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update participation_hope_counter set "
				+ "participation_hope_count = ?, "
				+ "participation_hope_date = CURRENT_TIMESTAMP "
				+ "where project_id = ?",
				new Object[]{
						participationHopeCounterDto.getParticipationHopeCount(),
						participationHopeCounterDto.getProjectId()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.ParticipationHopeCounterDao#delete(com.soramame.web.biz.dto.ParticipationHopeCounterDto)
	 */
	@Override
	public int delete(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from participation_hope_counter where project_id = ?",
				new Object[]{
						participationHopeCounterDto.getProjectId()
				});
		
		return rowCount;
		
	}

}
