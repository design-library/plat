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

import com.soramame.web.biz.dao.UserAccessCounterDao;
import com.soramame.web.biz.dto.UserAccessCounterDto;

/**
 * UserAccessCounterDaoImpl
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Repository
public class UserAccessCounterDaoImpl implements UserAccessCounterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#getUserAccessCount(java.lang.String)
	 */
	@Override
	public int getUserAccessCount(String userName) throws DataAccessException {
		
		int userAccessCount = 
				jdbcTemplate.queryForInt(
						"select count(*) from user_access_counter where user_name = ?",
						new Object[]{
								userName
								});
		
		return userAccessCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#findAllOrderByUserAccessCountAndUpdateDate()
	 */
	@Override
	public List<UserAccessCounterDto> findAllOrderByUserAccessCountAndUpdateDate() throws DataAccessException {
		
		List<UserAccessCounterDto> userAccessCounterDtoRes =
				(List<UserAccessCounterDto>) jdbcTemplate.query(
						"select * from user_access_counter order by user_access_count asc, access_date desc", 
						new BeanPropertyRowMapper<UserAccessCounterDto>(UserAccessCounterDto.class));
		
		return userAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#findByUserName(com.soramame.web.biz.dto.UserAccessCounterDto)
	 */
	@Override
	public UserAccessCounterDto findByUserName(
			UserAccessCounterDto userAccessCounterDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserAccessCounterDto userAccessCounterDtoRes =
				(UserAccessCounterDto) jdbcTemplate.queryForObject(
						"select * from user_access_counter where user_name = ?", 
						new BeanPropertyRowMapper<UserAccessCounterDto>(UserAccessCounterDto.class),
						new Object[]{
							userAccessCounterDto.getUserName()
						});
		
		return userAccessCounterDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#create(com.soramame.web.biz.dto.UserAccessCounterDto)
	 */
	@Override
	public int create(UserAccessCounterDto userAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into user_access_counter values(?, ?, CURRENT_TIMESTAMP)", 
				new Object[]{
						userAccessCounterDto.getUserName(),
						userAccessCounterDto.getUserAccessCount()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#update(com.soramame.web.biz.dto.UserAccessCounterDto)
	 */
	@Override
	public int update(UserAccessCounterDto userAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update user_access_counter set "
				+ "user_access_count = ?, "
				+ "access_date = CURRENT_TIMESTAMP "
				+ "where user_name = ?",
				new Object[]{
						userAccessCounterDto.getUserAccessCount(),
						userAccessCounterDto.getUserName()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserAccessCounterDao#delete(com.soramame.web.biz.dto.UserAccessCounterDto)
	 */
	@Override
	public int delete(UserAccessCounterDto userAccessCounterDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from user_access_counter where user_name = ?",
				new Object[]{
						userAccessCounterDto.getUserName()
				});
		
		return rowCount;
		
	}

}
