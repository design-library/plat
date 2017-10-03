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

import com.soramame.web.biz.dao.UserCareerOverviewDao;
import com.soramame.web.biz.dto.UserCareerOverviewDto;

/**
 * UserCareerOverviewDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class UserCareerOverviewDaoImpl implements UserCareerOverviewDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#findByCareerId(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public UserCareerOverviewDto findByCareerId(
			UserCareerOverviewDto userCareerOverviewDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserCareerOverviewDto userCareerOverviewDtoRes = jdbcTemplate.queryForObject(
				"select * from user_career_overview "
				+ "where career_id = ?", 
				new BeanPropertyRowMapper<UserCareerOverviewDto>(UserCareerOverviewDto.class),
				new Object[]{
					userCareerOverviewDto.getCareerId()
					});
		
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#findByUserNameAndPeriodFromMin(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public UserCareerOverviewDto findByUserNameAndPeriodFromMin(
			UserCareerOverviewDto userCareerOverviewDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		UserCareerOverviewDto userCareerOverviewDtoRes = 
				// TODO
				// 以下の結果のうち、更新日付が新しいデータを抽出する
				jdbcTemplate.queryForObject(
						"select "
						  + "uco.career_id, uco.user_name, uco.affiliation_name, uco.job_category_code, "
						  + "uco.period_from, uco.period_to, uco.over_view, "
						  + "uco.register_name, uco.register_date, uco.update_name, uco.update_date "
						+ "from "
						  + "user_career_overview as uco, "
						  + "(select user_name, min(period_from) as period_from, max(update_date) as update_date from user_career_overview where user_name = ? group by user_name) as minpf "
						+ "where uco.period_from = minpf.period_from and uco.user_name = minpf.user_name and uco.update_date = minpf.update_date", 
						new BeanPropertyRowMapper<UserCareerOverviewDto>(UserCareerOverviewDto.class),
						new Object[]{
							userCareerOverviewDto.getUserName()
							});
		
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#findByUserNameOrderByPeriodFrom(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public List<UserCareerOverviewDto> findByUserNameOrderByPeriodFrom(
			UserCareerOverviewDto userCareerOverviewDto) throws DataAccessException {
		
		List<UserCareerOverviewDto> userCareerOverviewDtoRes = 
				jdbcTemplate.query(
						"select * from user_career_overview where user_name = ? order by period_from", 
						new BeanPropertyRowMapper<UserCareerOverviewDto>(UserCareerOverviewDto.class),
						new Object[]{
							userCareerOverviewDto.getUserName()
							});
		
		return userCareerOverviewDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#create(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int create(UserCareerOverviewDto userCareerOverviewDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into user_career_overview values("
				+ "?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, ?, CURRENT_TIMESTAMP)",
				new Object[]{
						userCareerOverviewDto.getCareerId(),
						userCareerOverviewDto.getUserName(),
						userCareerOverviewDto.getAffiliationName(),
						userCareerOverviewDto.getJobCategoryCode(),
						userCareerOverviewDto.getPeriodFrom(),
						userCareerOverviewDto.getPeriodTo(),
						userCareerOverviewDto.getOverView(),
						userCareerOverviewDto.getRegisterName(),
						userCareerOverviewDto.getUpdateName()
				});

		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#update(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int update(UserCareerOverviewDto userCareerOverviewDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"update user_career_overview set "
				+ "affiliation_name = ?, "
				+ "job_category_code = ?, "
				+ "period_from = ?, "
				+ "period_to = ?, "
				+ "over_view = ?, "
				+ "update_name = ?, "
				+ "update_date = CURRENT_TIMESTAMP "
				+ "where career_id = ?",
				new Object[]{
						userCareerOverviewDto.getAffiliationName(),
						userCareerOverviewDto.getJobCategoryCode(),
						userCareerOverviewDto.getPeriodFrom(),
						userCareerOverviewDto.getPeriodTo(),
						userCareerOverviewDto.getOverView(),
						userCareerOverviewDto.getUpdateName(),
						userCareerOverviewDto.getCareerId()
				});
		
		return rowCount;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.UserCareerOverviewDao#delete(com.soramame.web.biz.dto.UserCareerOverviewDto)
	 */
	@Override
	public int delete(UserCareerOverviewDto userCareerOverviewDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"delete from user_career_overview where career_id = ?", 
				new Object[]{
						userCareerOverviewDto.getCareerId()
						});
		
		return rowCount;
		
	}

}
