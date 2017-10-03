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
package com.soramame.web.exception;

/**
 * NumberingException
 * 
 * @author Plat.
 * @version 1.0
 */
public class NumberingException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	/**
	 * constractor.
	 */
	public NumberingException() {
		super();
		
	}
	
	/**
	 * constractor.
	 * @param e
	 */
	public NumberingException(Throwable e) {
		super(e);
		
	}
	
	/**
	 * constractor.
	 * @param e
	 * @param message
	 */
	public NumberingException(String message, Throwable e) {
		super(e.getMessage(), e);
		this.setMessage(message);
		
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


}
