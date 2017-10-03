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

import com.soramame.web.biz.dto.UserScoutCounterDto;

/**
 * UserScoutCounterDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface UserScoutCounterDao {
	
	
	/**
	 * get userScoutCount.
	 * @param userName
	 * @return
	 * @throws DataAccessException
	 */
	public int getUserScoutCount(String userName) throws DataAccessException;
	
	/**
	 * find All order by userScoutCount, updateDate.
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserScoutCounterDto> findAllOrderByUserScoutCountAndUpdateDate() throws DataAccessException;
	
	/**
	 * find by userName.
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public UserScoutCounterDto findByUserName(UserScoutCounterDto userScoutCounterDto) 
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * create.
	 * @param userScoutCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(UserScoutCounterDto userScoutCounterDto) throws DataAccessException;
	
	/**
	 * update by primarykey.
	 * @param userScoutCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int update(UserScoutCounterDto userScoutCounterDto) throws DataAccessException;
	
	/**
	 * delete by primarykey.
	 * @param userScoutCounterDto
	 * @return
	 * @throws DataAccessException
	 */
	public int delete(UserScoutCounterDto userScoutCounterDto) throws DataAccessException;

}
