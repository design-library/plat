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

<%@page import="com.soramame.web.model.UserModel"%>
<%
UserModel userModel = (UserModel)session.getAttribute("sessionUserModel");
if (userModel == null){
%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%=request.getContextPath() %>/index"><spring:message code="navi.label.title" /></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="<%=request.getContextPath() %>/index"><spring:message code="navi.label.home" /></a></li>
            <li><a href="<%=request.getContextPath() %>/agreement"><spring:message code="navi.label.agreement" /></a></li>
            <li><a href="<%=request.getContextPath() %>/about"><spring:message code="navi.label.about" /></a></li>
          </ul>
          <form class="navbar-form navbar-right" action="<%=request.getContextPath() %>/login" method="post">
            <div class="form-group">
              <input type="text" name="userName" placeholder="<spring:message code="navi.placeholder.user_id" />" class="form-control" value="">
            </div>
            <div class="form-group">
              <input type="password" name="password" autocomplete="off" placeholder="<spring:message code="navi.placeholder.password" />" class="form-control" value="">
            </div>
            <button type="submit" class="btn btn-primary"><spring:message code="navi.button.label.login" /></button>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<%
} else {
%>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<%=request.getContextPath() %>/index"><spring:message code="navi.label.title" /></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="<%=request.getContextPath() %>/index"><spring:message code="navi.label.home" /></a></li>
            <li><a href="<%=request.getContextPath() %>/agreement"><spring:message code="navi.label.agreement" /></a></li>
            <li><a href="<%=request.getContextPath() %>/about"><spring:message code="navi.label.about" /></a></li>
            <%
            if ("10".equals(userModel.getRole())) {%>
            <li class="dropdown"><a href="#contact" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="navi.label.player" /><span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/edit"><spring:message code="navi.menu.user.edit" /></a></li>
                <li class="divider"></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/careers/new"><spring:message code="navi.menu.user.career.register" /></a></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/careers"><spring:message code="navi.menu.user.career.list" /></a></li>
              </ul>
            </li>
            <%} else if ("20".equals(userModel.getRole())) {%>
            <li class="dropdown"><a href="#contact" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="navi.label.manager" /><span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/edit"><spring:message code="navi.menu.user.edit" /></a></li>
                <li class="divider"></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/careers/new"><spring:message code="navi.menu.user.career.register" /></a></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/careers"><spring:message code="navi.menu.user.career.list" /></a></li>
                <li class="divider"></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/projects/new"><spring:message code="navi.menu.project.register" /></a></li>
                <li><a href="<%=request.getContextPath() %>/users/<%=userModel.getUserName()%>/projects"><spring:message code="navi.menu.project.list" /></a></li>
              </ul>
            </li>
            <%} else if ("99".equals(userModel.getRole())) {%>
            <li><a href="#contact"><spring:message code="navi.label.you" /></a></li>
            <%} %>
          </ul>
          <form class="navbar-form navbar-right" action="<%=request.getContextPath() %>/toLogout" method="get">
            <div class="form-group">
              <p class="navbar-nav login-user"><spring:message code="navi.label.welcome" />  <%=userModel.getUserName()%> <spring:message code="navi.label.mr" /></p>
            </div>
            <button type="submit" class="btn btn-primary"><spring:message code="navi.button.label.logout" /></button>
          </form>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

<%
}
%>
