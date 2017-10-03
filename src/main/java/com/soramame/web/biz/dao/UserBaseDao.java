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

import com.soramame.web.biz.dto.UserBaseDto;


/**
 * UserBaseDao
 * 
 * @author Plat.
 * @version 1.0
 */
public interface UserBaseDao {
	
	/**
	 * get count.
	 * @param userBaseDto
	 * @return
	 * @throws DataAccessException
	 */
	public int getCount(UserBaseDto userBaseDto) throws DataAccessException;
	
	/**
	 * find by primarykey.
	 * @param userBaseDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public UserBaseDto findByPrimaryKey(UserBaseDto userBaseDto) 
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * find by userName, password.
	 * @param userBaseDto
	 * @return
	 * @throws EmptyResultDataAccessException
	 * @throws IncorrectResultSizeDataAccessException
	 */
	public UserBaseDto findByUserNameAndPassword(UserBaseDto userBaseDto)
			throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException;
	
	/**
	 * find by role, order by updateDate.
	 * @param userBaseDto
	 * @return
	 * @throws DataAccessException
	 */
	public List<UserBaseDto> findByRoleOrderByUpdateDateDesc(UserBaseDto userBaseDto) throws DataAccessException;
	
	/**
	 * create.
	 * @param userBaseDto
	 * @return
	 * @throws DataAccessException
	 */
	public int create(UserBaseDto userBaseDto) throws DataAccessException;
	
	/**
	 * update by primarykey.
	 * @param userBaseDto
	 * @return
	 * @throws DataAccessException
	 */
	public int update(UserBaseDto userBaseDto) throws DataAccessException;
	
	/**
	 * delete by primarykey.
	 * @param userBaseDto
	 * @return
	 * @throws DataAccessException
	 */
	public int delete(UserBaseDto userBaseDto) throws DataAccessException;
	
}
