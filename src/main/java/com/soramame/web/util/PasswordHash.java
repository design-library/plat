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
package com.soramame.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * PasswordHash
 * 
 * @version 1.0
 * @author Plat.
 * @see <a href="http://www.websec-room.com/2013/02/27/237">Preservation method of a safe password</a>

 */
@Component
public class PasswordHash {
	
	private static int STRETCH_COUNT = 1000;
	
	/**
	 * get salt
	 * 
	 * @param password
	 * @param userId
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String getSaltedPassword(String password, String userId) throws NoSuchAlgorithmException {
		String salt = getSha256(userId);
		return getSha256(salt + password);
		
	}

	/**
	 * streched salt
	 * 
	 * @param password
	 * @param userId
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public String getStretchedPassword(String password, String userId) throws NoSuchAlgorithmException {
		String salt = getSha256(userId);
		String hash = "";
		
		for(int i = 0;i < STRETCH_COUNT;i++) {
			hash = getSha256(hash + salt + password);
		}
		
		return hash;
	}
	
	private String getSha256(String target) throws NoSuchAlgorithmException {
		MessageDigest md = null;
		StringBuffer buf = new StringBuffer();
		md = MessageDigest.getInstance("SHA-256");
		md.update(target.getBytes());
		byte[] digest = md.digest();
		
		for(int i = 0;i < digest.length;i++) {
			buf.append(String.format("%02x", digest[i]));
			
		}
		return buf.toString();
		
	}
	
}
