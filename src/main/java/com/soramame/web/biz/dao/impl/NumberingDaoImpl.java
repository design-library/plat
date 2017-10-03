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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soramame.web.biz.dao.NumberingDao;
import com.soramame.web.biz.dto.NumberingDto;

/**
 * Implementation of UserAccessCounterDao.
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Repository
public class NumberingDaoImpl implements NumberingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.NumberingDao#findNumberByPrimaryKey(com.soramame.web.biz.dto.NumberingDto)
	 */
	@Override
	public NumberingDto findNumberByPrimaryKey(NumberingDto numberingDto) 
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		NumberingDto numberingDtoRes = jdbcTemplate.queryForObject(
				"select * from numbering where numbering_code = ?", 
				new BeanPropertyRowMapper<NumberingDto>(NumberingDto.class),
				new Object[]{
					numberingDto.getNumberingCode()
				});
		
		return numberingDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.NumberingDao#update(com.soramame.web.biz.dto.NumberingDto)
	 */
	@Override
	public int update(NumberingDto numberingDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update numbering set "
				+ "id = ? "
				+ "where numbering_code = ?", 
				new Object[]{
						numberingDto.getId(), 
						numberingDto.getNumberingCode()
				});
		
		return rowCount;
		
	}

}
