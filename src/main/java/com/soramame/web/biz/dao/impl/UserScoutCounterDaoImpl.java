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

import com.soramame.web.biz.dao.UserScoutCounterDao;
import com.soramame.web.biz.dto.UserScoutCounterDto;

/**
 * UserScoutCounterDaoImpl
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Repository
public class UserScoutCounterDaoImpl implements UserScoutCounterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#getUserScoutCount(java.lang.String)
	 */
	@Override
	public int getUserScoutCount(String userName) throws DataAccessException {
		
		int UserScoutCount = 
				jdbcTemplate.queryForInt(
						"select count(*) from user_scout_counter where user_name = ?",
						new Object[]{
								userName
								});
		
		return UserScoutCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#findAllOrderByUserScoutCountAndUpdateDate()
	 */
	@Override
	public List<UserScoutCounterDto> findAllOrderByUserScoutCountAndUpdateDate() throws DataAccessException {
		
		List<UserScoutCounterDto> userScoutCounterDtoRes =
				(List<UserScoutCounterDto>) jdbcTemplate.query(
						"select * from user_scout_counter order by User_scout_count asc, scout_date desc", 
						new BeanPropertyRowMapper<UserScoutCounterDto>(UserScoutCounterDto.class));
		
		return userScoutCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#findByUserName(com.soramame.web.biz.dto.UserScoutCounterDto)
	 */
	@Override
	public UserScoutCounterDto findByUserName(
			UserScoutCounterDto userScoutCounterDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserScoutCounterDto userScoutCounterDtoRes =
				(UserScoutCounterDto) jdbcTemplate.queryForObject(
						"select * from user_scout_counter where user_name = ?", 
						new BeanPropertyRowMapper<UserScoutCounterDto>(UserScoutCounterDto.class),
						new Object[]{
							userScoutCounterDto.getUserName()
						});
		
		return userScoutCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#create(com.soramame.web.biz.dto.UserScoutCounterDto)
	 */
	@Override
	public int create(UserScoutCounterDto userScoutCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into user_scout_counter values(?, ?, CURRENT_TIMESTAMP)", 
				new Object[]{
						userScoutCounterDto.getUserName(),
						userScoutCounterDto.getUserScoutCount()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#update(com.soramame.web.biz.dto.UserScoutCounterDto)
	 */
	@Override
	public int update(UserScoutCounterDto userScoutCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update user_scout_counter set "
				+ "User_scout_count = ?, "
				+ "scout_date = CURRENT_TIMESTAMP "
				+ "where user_name = ?",
				new Object[]{
						userScoutCounterDto.getUserScoutCount(),
						userScoutCounterDto.getUserName()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserScoutCounterDao#delete(com.soramame.web.biz.dto.UserScoutCounterDto)
	 */
	@Override
	public int delete(UserScoutCounterDto userScoutCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from user_scout_counter where user_name = ?",
				new Object[]{
						userScoutCounterDto.getUserName()
				});
		
		return rowCount;
		
	}

}
