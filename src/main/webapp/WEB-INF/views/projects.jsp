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
<%@page import="java.util.List" %>
<%@page import="com.soramame.web.model.RecruitmentAreaMstModel" %>
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
    
    <title><spring:message code="common.pj.name" /> | 
    <c:choose>
      <c:when test="${pageStatus == 'update'}">
        <spring:message code="projects.head.title.update" />
      </c:when>
      <c:otherwise>
        <spring:message code="projects.head.title.register" />
      </c:otherwise>
    </c:choose> </title>
    
  </head>

  <body>
  
    <!-- nav -->
    <jsp:include page="nav.jsp" flush="false" />

    <div class="container">
      <div class="row">
        <div class="col-md-4">
        
          <!-- side messages -->
          <jsp:include page="side_messages.jsp" flush="false" />
        
        </div>
        <div class="col-md-8">
          <div class="panel panel-primary">
            <div class="panel-heading">
            <c:choose>
              <c:when test="${pageStatus == 'update'}">
              <h3 class="panel-title"><spring:message code="projects.label.title.update" /></h3>
              </c:when>
              <c:otherwise>
              <h3 class="panel-title"><spring:message code="projects.label.title.register" /></h3>
              </c:otherwise>
            </c:choose>
            </div>
            <div class="panel-body">
              <c:choose>
                <c:when test="${pageStatus == 'update'}">
                  <form class="form-horizontal" name="sysform" action="<%=request.getContextPath() %>/users/${userName}/projects/${projectId}" method="post">
                  <input type="hidden" name="_method" value="" >
                </c:when>
                <c:otherwise>
                  <form class="form-horizontal" name="sysform" action="<%=request.getContextPath() %>/users/${userName}/projects/new" method="post">
                </c:otherwise>
              </c:choose>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="projects.label.title" /></label>
                  <div class="col-md-9">
                    <input type="text" class="form-control" placeholder="<spring:message code="projects.placeholder.title" />" name="title" value="${projectModel.title}">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="projects.label.recruitment_area" /></label>
                  <div class="col-md-4">
                    <select class="form-control" name="recruitmentAreaCode">
                      <%
                      	List<RecruitmentAreaMstModel> recruitmentAreaMstModelList = 
                                                (List<RecruitmentAreaMstModel>)request.getAttribute("recruitmentAreaModelList");
                                              for (RecruitmentAreaMstModel recruitmentAreaMstModel : recruitmentAreaMstModelList) {
                      %>
                      <option value="<%=recruitmentAreaMstModel.getRecruitmentAreaCode()%>" <%=recruitmentAreaMstModel.getSelect()%>><%=recruitmentAreaMstModel.getRecruitmentAreaName()%>
                      <%
                      	}
                      %>
                    </select>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-md-3 control-label"><spring:message code="projects.label.overView" /></label>
                  <div class="col-md-9">
                    <textarea class="form-control" rows="15" name="overView">${projectModel.overView }</textarea>
                  </div>
                </div>
                <div class="col-md-3">
                </div>
                
                <c:choose>
                  <c:when test="${pageStatus == 'update'}">
                <div class="col-md-4">
                  <button type="submit" class="btn btn-primary btn-lg btn-block" onclick="doAction('put')"><spring:message code="projects.button.label.update" /></button>
                </div>
                <div class="col-md-1">
                </div>
                <div class="col-md-4">
                  <button type="submit" class="btn btn-primary btn-lg btn-block" onclick="doAction('delete')"><spring:message code="projects.button.label.delete" /></button>
                </div>
                  </c:when>
                  <c:otherwise>
                <div class="col-md-9">
                  <button type="submit" class="btn btn-primary btn-lg btn-block"><spring:message code="projects.button.label.register" /></button>
                </div>
                  </c:otherwise>
                </c:choose>
                  
              </form>
            </div>
          </div>
        </div>
      </div>

      <hr>

      <!-- footer -->
      <jsp:include page="footer.jsp" flush="false" />

    </div> <!-- /container -->
    <script type="text/javascript">
    function doAction(verb) {
    	document.sysform._method.value = verb;
    	document.sysform.submit();
    }
    </script>
    <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
      (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
      m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
      })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

      ga('create', 'UA-70509304-1', 'auto');
      ga('send', 'pageview');
    </script> 

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/jquery-2.1.3.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

  </body>
</html>
