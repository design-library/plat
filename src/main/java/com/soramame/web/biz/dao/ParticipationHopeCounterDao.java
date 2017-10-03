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

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.soramame.web.biz.dto.ParticipationHopeCounterDto;

/**
 * ParticipationHopeCounterDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface ParticipationHopeCounterDao {
	
	
	/**
	 * get participationHopeCount.
	 * @param projectId
	 * @return
	 * @throws DataAccessException
	 */
	public int getParticipationHopeCount(String projectId) throws DataAccessException;
	
	/**
	 * find All order by participationHopeCount, updateDate.
	 * @return
	 * @throws DataAccessException
	 */
	public List<ParticipationHopeCounterDto> findAllOrderByParticipationHopeCountAndUpdateDate() throws DataAccessException;
	
	/**
	 * find by projectId.
	 * @param participationHopeCounterDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public ParticipationHopeCounterDto findByProjectId(ParticipationHopeCounterDto participationHopeCounterDto) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;

	/**
	 * create.
	 * @param participationHopeCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException;
	
	/**
	 * update by primarykey.
	 * @param participationHopeCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int update(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException;
	
	/**
	 * delete by primarykey.
	 * @param participationHopeCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int delete(ParticipationHopeCounterDto participationHopeCounterDto) throws DataAccessException;

}
