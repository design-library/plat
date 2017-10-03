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
package com.soramame.web.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Throwables;
import com.soramame.web.util.TimestampUtil;

/**
 * GlobalExceptionResolver.
 * 
 * @version 1.0
 * @author plat
 *
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionResolver.class);
	
	@Autowired
	private MessageSource messageSource;

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler, Exception exception) 
	{
		
		
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
			
		}
		if (requestUri.lastIndexOf(".css") > -1 ||
				requestUri.lastIndexOf(".js") > -1) {
			return null;
			
		}
		
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			statusCode = 500;
		}
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);
		
		String timestampStr = TimestampUtil.timestamp(TimestampUtil.current(), "yyyy/MM/dd HH:mm:ss");
		String message = messageSource.getMessage("error.message.system", null, response.getLocale());

		ModelAndView mav = new ModelAndView ();
		mav.addObject("timestamp", timestampStr);  
		mav.addObject("message", message);  
		mav.addObject("errorCode", statusCode);  
		
		logger.fatal(timestampStr + " " + " statusCode=" + statusCode + " requestUri=" + requestUri + " " + exceptionMessage);
		
		mav.setViewName("global_error");
		
		return mav;
		
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			return Throwables.getRootCause(throwable).getMessage();
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		return httpStatus.getReasonPhrase();
	}

}
