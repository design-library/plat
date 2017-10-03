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
package com.soramame.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.soramame.web.model.UserModel;

/**
 * Session listener.
 * 
 * @version 1.0
 * @author Plat.
 *
 */
public class SessionListener implements HttpSessionListener {

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		session.setAttribute("session.map.user_access_counter", new HashMap<String, String>());
		session.setAttribute("session.map.user_scout_counter", new HashMap<String, String>());
		session.setAttribute("session.map.project_access_counter", new HashMap<String, String>());
		session.setAttribute("session.map.participation_hope_counter", new HashMap<String, String>());

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		HttpSession session = hse.getSession();
		ServletContext context = session.getServletContext();
		Map<String, UserModel> authenticatedUserMap = (Map<String, UserModel>)context.getAttribute("application.map.authenticated_user");
		
		String userName = (String)session.getAttribute("session.user_name");
		if (userName != null) {
			authenticatedUserMap.remove(userName);
			
		}
		
	}

}
