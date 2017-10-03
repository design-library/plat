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

import com.soramame.web.biz.dao.TransmissionHistoryDao;
import com.soramame.web.biz.dto.TransmissionHistoryDto;

/**
 * TransmissionHistoryDaoImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Repository
public class TransmissionHistoryDaoImpl implements TransmissionHistoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.TransmissionHistoryDao#getCountByTypeAndSendersAndRecipient(com.soramame.web.biz.dto.TransmissionHistoryDto)
	 */
	@Override
	public int getCountByTypeAndSendersAndRecipient(TransmissionHistoryDto transmissionHistoryDto) throws DataAccessException {
		
		int result = jdbcTemplate.queryForInt(
				"select count(*) from transmission_history where transmission_type = ? and senders_name = ? and recipient_name = ?",
				new Object[]{
						transmissionHistoryDto.getTransmissionType(),
						transmissionHistoryDto.getSendersName(),
						transmissionHistoryDto.getRecipientName()
					});
		
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.TransmissionHistoryDao#findByTypeAndSendersAndRecipient(com.soramame.web.biz.dto.TransmissionHistoryDto)
	 */
	@Override
	public TransmissionHistoryDto findByTypeAndSendersAndRecipient(
			TransmissionHistoryDto transmissionHistoryDto) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
		
		TransmissionHistoryDto transmissionHistoryDtoRes = 
				(TransmissionHistoryDto) jdbcTemplate.queryForObject(
				"select "
					+ "th.history_id, "
					+ "th.transmission_type, "
					+ "th.senders_name, "
					+ "th.recipient_name, "
					+ "th.register_date "
				+ "from "
					+ "transmission_history as th, "
					+ "(select "
						+ "transmission_type, "
						+ "senders_name, "
						+ "recipient_name, "
						+ "max(register_date) as register_date "
					+ "from "
						+ "transmission_history "
					+ "where "
						+ "transmission_type = ? and "
						+ "senders_name = ? and "
						+ "recipient_name = ? "
					+ "group by "
						+ "transmission_type, "
						+ "senders_name, "
						+ "recipient_name) as rdt "
				+ "where "
					+ "th.register_date = rdt.register_date",
				new BeanPropertyRowMapper<TransmissionHistoryDto>(TransmissionHistoryDto.class),
				new Object[]{
					transmissionHistoryDto.getTransmissionType(),
					transmissionHistoryDto.getSendersName(),
					transmissionHistoryDto.getRecipientName()
				});
		
		return transmissionHistoryDtoRes;
		
	}

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.dao.TransmissionHistoryDao#create(com.soramame.web.biz.dto.TransmissionHistoryDto)
	 */
	@Override
	public int create(TransmissionHistoryDto transmissionHistoryDto) throws DataAccessException {
		
		int rowCount = jdbcTemplate.update(
				"insert into transmission_history values (?, ?, ?, ?, CURRENT_TIMESTAMP)", 
				new Object[]{
						transmissionHistoryDto.getHistoryId(),
						transmissionHistoryDto.getTransmissionType(),
						transmissionHistoryDto.getSendersName(), 
						transmissionHistoryDto.getRecipientName(), 
				});
		
		return rowCount;
		
	}

}
