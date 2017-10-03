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

/**
 * business exception class.
 * 
 * @version 1.0
 * @author Plat.
 *
 */
public class BusinessException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7285364987589703182L;

	private String message;

	/**
	 * default Constructor
	 */
	public BusinessException() {
		super();
		
	}
		
	/**
	 * Constructor
	 * 
	 * @param throwable
	 */
	public BusinessException(Throwable throwable) {
		super(throwable);
		
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 * @param throwable
	 */
	public BusinessException(String message, Throwable throwable) {
		super(throwable.getMessage(), throwable);
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
