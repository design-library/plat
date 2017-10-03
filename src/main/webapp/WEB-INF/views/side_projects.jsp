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

          <div class="list-group">
            <div class="list-group-item background-primary">
              <h4><spring:message code="side.project.top5.access.title" /></h4>
            </div>
            <c:forEach var="projectAccessCounterModel" items="${projectAccessTop5List }" end="4">
              <a href="<%=request.getContextPath() %>/projects/${projectAccessCounterModel.projectId}" class="list-group-item">${projectAccessCounterModel.title}<span class="badge">${projectAccessCounterModel.projectAccessCount}</span></a>
            </c:forEach>
          </div>
          <div class="list-group">
            <div class="list-group-item background-primary">
              <h4><spring:message code="side.project.top5.ph.title" /></h4>
            </div>
            <c:forEach var="participationHopeCounterModel" items="${participationHopeTop5List }" end="4">
              <a href="<%=request.getContextPath() %>/projects/${participationHopeCounterModel.projectId}" class="list-group-item">${participationHopeCounterModel.title}<span class="badge">${participationHopeCounterModel.participationHopeCount}</span></a>
            </c:forEach>
          </div>
