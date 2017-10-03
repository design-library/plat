<%--
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
 --%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title"><spring:message code="common.label.side.message.title" /></h3>
            </div>
            <div class="panel-body">
            <%String messagesType = (String) request.getAttribute("messages_type");
              String[] messages = (String[]) request.getAttribute("messages");
              if(messages != null) {
                for (String message : messages) {
                  if("W".equals(messagesType)) {%>
                    <p class="side-messages-waring"><%=message%></p>
                <%}else if("I".equals(messagesType)) {%>
                    <p class="side-messages-info"><%=message%></p>
                <%}else{%>
                    <p><%=message%></p>
                <%}%>
              <%}%>
            <%}%>
            </div>
          </div>
