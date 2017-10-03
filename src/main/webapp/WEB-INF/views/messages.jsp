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
<%@page import="com.soramame.web.util.TimestampUtil"%>
<%@page import="java.util.Map"%>
<%@page session="false"%>
<!DOCTYPE html>
<html lang="<%=request.getLocale() %>">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="<spring:message code="common.pj.name" />">
    <meta name="author" content="Plat">

    <!-- Le styles -->
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->
    
    <title><spring:message code="common.pj.name" /> | <spring:message code="message.page.title" /> </title>
  </head>

  <%
    if("logout".equals(request.getAttribute("messages_type")) ||
    		"userDelete".equals(request.getAttribute("messages_type"))) {
  	    HttpSession session = request.getSession(false);
        if(session != null) {
            UserModel user  = (UserModel)session.getAttribute("user");
            if(user != null) {
  	            ServletContext context = session.getServletContext();
  	            Map<String, UserModel> authenticatedUserMap = 
  	    		        (Map<String, UserModel>)context.getAttribute("application.map.authenticated_user");
  	            if(authenticatedUserMap.containsKey(user.getUserName())) {
  	                authenticatedUserMap.remove(user.getUserName());
  	            }
            }
            session.invalidate();
        }
    }
  %>
  
  <body>
    <!-- nav -->
    <jsp:include page="nav.jsp" flush="false" />
    <div class="container">
      <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title"><spring:message code="message.label.title" /></h3>
            </div>
            <div class="panel-body">
              <%String[] messages = (String[]) request.getAttribute("messages");
                if(messages != null) {
                    for (String message : messages) {%>
                        <p class="side-messages-info"><%=message%></p>
                  <%}%>
                <%} else {%>
                        <p class="side-messages-info"><spring:message code="message.message.information" /></p>
                <%} %>
            </div>
          </div>
        </div>
        <div class="col-md-2">
        </div>
      </div>

      <hr>

      <!-- footer -->
      <jsp:include page="footer.jsp" flush="false" />

    </div> <!-- /container -->
    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-70509304-1', 'auto');
      ga('send', 'pageview');
    </script> 
  
  </body>
</html>
