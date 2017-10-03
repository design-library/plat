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

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.soramame.web.biz.dao.UserBaseDao;
import com.soramame.web.biz.dto.UserBaseDto;
import com.soramame.web.biz.service.AuthenticationService;
import com.soramame.web.util.PasswordHash;

/**
 * AuthenticationServiceImpl.
 * 
 * @author Plat.
 * @version 1.0
 *
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	@Autowired 
	private PasswordHash passwordHash;
	
	@Autowired
	private UserBaseDao userDao;
	
	/* (non-Javadoc)
	 * @see com.soramame.web.biz.service.AuthenticationService#authenticate(com.soramame.web.biz.dto.UserBaseDto)
	 */
	@Override
	public boolean authenticate(UserBaseDto userBaseDto) {
		
		String stretchedPassword;
		try {
			stretchedPassword = passwordHash.getStretchedPassword(
					userBaseDto.getPassword(), 
					userBaseDto.getUserName());
		
			userBaseDto.setPassword(stretchedPassword);
		
			userDao.findByUserNameAndPassword(userBaseDto);
			
		} catch (EmptyResultDataAccessException e) {
			return false;
			
		} catch (NoSuchAlgorithmException e1) {
			return false;
			
		}
		return true;
	}

}
