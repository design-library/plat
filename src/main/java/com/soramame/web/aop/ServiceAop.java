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
package com.soramame.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * It is a class that outputs a log of applications.
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Aspect
@Component
public class ServiceAop {

	private static final Logger logger = Logger.getLogger(ServiceAop.class);
	
	/**
	 * before.
	 * 
	 * @param jp
	 */
	@Before("execution(* com.soramame.web.biz.service..*.*(..))")
	public void before(JoinPoint jp) {
		
		Signature signature = jp.getSignature();
		
		logger.info(signature.getDeclaringTypeName() + "." + signature.getName() + " start");
		
	}
	
	/**
	 * after returning.
	 * 
	 * @param jp
	 * @param obj
	 */
	@AfterReturning(value="execution(* com.soramame.web.biz.service..*.*(..))", returning="obj")
	public void afterReturning(JoinPoint jp, Object obj) {
		
		Signature signature = jp.getSignature();
		
		logger.info(signature.getDeclaringTypeName() + "." + signature.getName() + " end");
		
	}
}
