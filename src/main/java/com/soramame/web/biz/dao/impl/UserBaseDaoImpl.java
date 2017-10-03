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

import com.soramame.web.biz.dao.UserBaseDao;
import com.soramame.web.biz.dto.UserBaseDto;

/**
 * UserDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class UserBaseDaoImpl implements UserBaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#getCount(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int getCount(UserBaseDto userBaseDto) throws DataAccessException {
		
		int result = jdbcTemplate.queryForInt(
				"select count(*) from user_base where user_name = ?",
				new Object[]{userBaseDto.getUserName()});
		
		return result;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#findByPrimaryKey(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public UserBaseDto findByPrimaryKey(UserBaseDto userBaseDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserBaseDto userBaseDtoRes = 
				(UserBaseDto) jdbcTemplate.queryForObject(
						"select * from user_base where user_name = ?", 
						new BeanPropertyRowMapper<UserBaseDto>(UserBaseDto.class),
						new Object[]{
							userBaseDto.getUserName()
							});
		
		return userBaseDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#findByUserNameAndPassword(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public UserBaseDto findByUserNameAndPassword(UserBaseDto userBaseDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserBaseDto userBaseDtoRes = 
				(UserBaseDto) jdbcTemplate.queryForObject(
				"select * from user_base where user_name = ? and password = ?", 
				new BeanPropertyRowMapper<UserBaseDto>(UserBaseDto.class),
				new Object[]{
					userBaseDto.getUserName(),
					userBaseDto.getPassword()
				});
		
		return userBaseDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#findByRoleOrderByUpdateDateDesc(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public List<UserBaseDto> findByRoleOrderByUpdateDateDesc(UserBaseDto userBaseDto) throws DataAccessException {
		
		List<UserBaseDto> userBaseDtoRes = 
				jdbcTemplate.query(
						"select * from user_base where role = ? order by update_date desc", 
						new BeanPropertyRowMapper<UserBaseDto>(UserBaseDto.class),
						new Object[] {
							userBaseDto.getRole()
							});
		
		return userBaseDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#create(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int create(UserBaseDto userBaseDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into user_base values(?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)",
				new Object[]{
						userBaseDto.getUserName(),
						userBaseDto.getPassword(),
						userBaseDto.getRole(),
						userBaseDto.getBirthDay(),
						userBaseDto.getEmail(),
						userBaseDto.getLiveCode(),
						userBaseDto.getRegisterName(),
						userBaseDto.getUpdateName()
				});

		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#update(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int update(UserBaseDto userBaseDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update user_base set "
				+ "password = ?, "
				+ "role = ?, "
				+ "birth_day = ?, "
				+ "email = ?, "
				+ "live_code = ?, "
				+ "update_name = ?, "
				+ "update_date = CURRENT_TIMESTAMP "
				+ "where user_name = ?",
				new Object[]{
						userBaseDto.getPassword(),
						userBaseDto.getRole(),
						userBaseDto.getBirthDay(),
						userBaseDto.getEmail(),
						userBaseDto.getLiveCode(),
						userBaseDto.getUpdateName(),
						userBaseDto.getUserName()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserBaseDao#delete(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public int delete(UserBaseDto userBaseDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from user_base where user_name = ?", 
				new Object[]{
						userBaseDto.getUserName()
						});
		
		return rowCount;
		
	}

}
