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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soramame.web.biz.dao.AgeGroupMstDao;
import com.soramame.web.biz.dto.AgeGroupMstDto;

/**
 * AgeGroupMstDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class AgeGroupMstDaoImpl implements AgeGroupMstDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.AgeGroupMstDao#findAllOrderBySortNumber()
	 */
	@Override
	public List<AgeGroupMstDto> findAllOrderBySortNumber() {
		
		List<AgeGroupMstDto> ageGroupMstDtoRes = 
				(List<AgeGroupMstDto>) jdbcTemplate.query(
						"select * from age_group_mst order by sort_number",
						new BeanPropertyRowMapper<AgeGroupMstDto>(AgeGroupMstDto.class)
						);
		
		return ageGroupMstDtoRes;
		
	}

}
