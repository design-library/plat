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
package com.soramame.web.biz.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.soramame.web.biz.dto.TransmissionHistoryDto;

/**
 * SendHistoryDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface TransmissionHistoryDao {
	
	/**
	 * get count by type and senders and recipient
	 * @param transmissionHistoryDto
	 * @return
	 * @throws DataAccessException
	 */
	public int getCountByTypeAndSendersAndRecipient(TransmissionHistoryDto transmissionHistoryDto)
			throws DataAccessException;
	
	/**
	 * find by type and senders and recipient
	 * @param transmissionHistoryDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public TransmissionHistoryDto findByTypeAndSendersAndRecipient(TransmissionHistoryDto transmissionHistoryDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * create.
	 * @param transmissionHistoryDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(TransmissionHistoryDto transmissionHistoryDto)
			throws DataAccessException;

}
