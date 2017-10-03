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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soramame.web.biz.dao.AffiliationDao;
import com.soramame.web.biz.dto.AffiliationDto;

/**
 * AffiliationDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class AffiliationDaoImpl implements AffiliationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.AffiliationDao#findByPrimariyKey(com.soramame.web.biz.dto.AffiliationDto)
	 */
	@Override
	public AffiliationDto findByPrimariyKey(AffiliationDto affiliationDto) {
		
		AffiliationDto affiliationDtoRes = 
				(AffiliationDto) jdbcTemplate.queryForObject(
						"select * from affiliation where user_name = ?", 
						new BeanPropertyRowMapper<AffiliationDto>(AffiliationDto.class),
						new Object[]{
							affiliationDto.getUserName()
							});
		
		return affiliationDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.AffiliationDao#create(com.soramame.web.biz.dto.AffiliationDto)
	 */
	@Override
	public int create(AffiliationDto affiliationDto) {
		
		int rowCount = jdbcTemplate.update(
				"insert into affiliation values(?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)",
				new Object[]{
						affiliationDto.getUserName(),
						affiliationDto.getAffiliationName(),
						affiliationDto.getAddress(),
						affiliationDto.getTypeIndustryCode(),
						affiliationDto.getActivities(),
						affiliationDto.getRegisterName(),
						affiliationDto.getUpdateName()
				});

		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.AffiliationDao#update(com.soramame.web.biz.dto.AffiliationDto)
	 */
	@Override
	public int update(AffiliationDto affiliationDto) {
		
		int rowCount = jdbcTemplate.update(
				"update affiliation set "
				+ "affiliation_name = ?, "
				+ "address = ?, "
				+ "type_industry = ?, "
				+ "activities = ?, "
				+ "update_name = ?, "
				+ "update_date = CURRENT_TIMESTAMP "
				+ "where user_name = ?",
				new Object[]{
						affiliationDto.getAffiliationName(),
						affiliationDto.getAddress(),
						affiliationDto.getTypeIndustryCode(),
						affiliationDto.getActivities(),
						affiliationDto.getUpdateName(),
						affiliationDto.getUserName()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.AffiliationDao#delete(com.soramame.web.biz.dto.AffiliationDto)
	 */
	@Override
	public int delete(AffiliationDto affiliationDto) {
		
		int rowCount = jdbcTemplate.update(
				"delete from affiliation where user_name = ?", 
				new Object[]{
						affiliationDto.getUserName()
						});
		
		return rowCount;
		
	}

}
