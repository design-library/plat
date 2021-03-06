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
package com.soramame.web.biz.service;

import com.soramame.web.biz.dto.TransmissionHistoryDto;

/**
 * HistoryService
 * 
 * @author Plat.
 * @version 1.0
 */
public interface HistoryService {
	
	/**
	 * get diff(register transmission date)
	 * @param transmissionHistoryDto
	 * @return
	 */
	public long getDiff(TransmissionHistoryDto transmissionHistoryDto);
	
	/**
	 * create transmissionHistory.
	 * @param transmissionHistoryDto
	 * @return
	 */
	public int createTransmissionHistory(TransmissionHistoryDto transmissionHistoryDto);

}
