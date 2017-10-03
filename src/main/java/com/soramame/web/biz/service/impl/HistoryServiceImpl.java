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
package com.soramame.web.biz.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soramame.web.biz.dao.TransmissionHistoryDao;
import com.soramame.web.biz.dto.TransmissionHistoryDto;
import com.soramame.web.biz.service.HistoryService;

/**
 * HistoryServiceImpl
 * 
 * @author Plat.
 * @version 1.0
 */
@Service
public class HistoryServiceImpl implements HistoryService {
	
	@Autowired
	private TransmissionHistoryDao transmissionHistoryDao;

	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.HistoryService#getDiff(com.soramame.web.biz.dto.TransmissionHistoryDto)
	 */
	@Override
	public long getDiff(TransmissionHistoryDto transmissionHistoryDto) {
		
		int existCount = transmissionHistoryDao.getCountByTypeAndSendersAndRecipient(transmissionHistoryDto);
		if (existCount > 0) {
			TransmissionHistoryDto transmissionHistoryDtoRes = 
					transmissionHistoryDao.findByTypeAndSendersAndRecipient(transmissionHistoryDto);
			Date registerDate = transmissionHistoryDtoRes.getRegisterDate();
			long registerDateL = registerDate.getTime();
			long currentTimeMillis = System.currentTimeMillis();
			
			return currentTimeMillis - registerDateL;
			
		} else {
			return -1;
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.HistoryService#createTransmissionHistory(com.soramame.web.biz.dto.TransmissionHistoryDto)
	 */
	@Override
	public int createTransmissionHistory(
			TransmissionHistoryDto transmissionHistoryDto) {
		
		int rowCount = transmissionHistoryDao.create(transmissionHistoryDto);
		
		return rowCount;
		
	}

}
