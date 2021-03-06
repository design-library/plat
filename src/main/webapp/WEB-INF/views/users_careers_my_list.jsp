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
<%@taglib prefix="out" uri="http://soramameweb.com/web/tag/OutputStrTag"%>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-responsive.css" />">
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
    
    <title><spring:message code="common.pj.name" /> | <spring:message code="user_career_overview_my_list.head.title.list" /> </title>
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
          <table class="table table-hover">
            <thead>
              <caption class="cptn background-primary">
                <spring:message code="user_career_overview_my_list.label.title.list" />
              </caption>
              <tr>
                <th class="th-width-30 active"><spring:message code="user_career_overview_my_list.label.affiliation_name" /></th>
                <th class="th-width-70 active"></th>
              </tr>
              <tr>
                <th class="th-width-30 active"><spring:message code="user_career_overview_my_list.label.period_from_to" /></th>
                <th class="th-width-70 active"><spring:message code="user_career_overview_my_list.label.job_category_name" /></th>
              </tr>
              <tr>
                <th class="th-width-100 active" colspan="2" ><spring:message code="user_career_overview.label.over_view" /></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="userCareerOverviewModel" items="${userCareerOverviewModelList}">
              <tr>
                <td class="td-width-30"><a href="<%=request.getContextPath() %>/users/${userName}/careers/${userCareerOverviewModel.careerId}/edit">
                <c:out value="${userCareerOverviewModel.affiliationName}" /></a></td>
                <td class="td-width-70"></td>
              </tr>
              <tr>
                <td class="td-width-30">${userCareerOverviewModel.periodFrom} - ${userCareerOverviewModel.periodTo}</td>
                <td class="td-width-70">${userCareerOverviewModel.jobCategoryName}</td>
              </tr>
              <tr>
                <td class="td-width-100 border-bottom" colspan="2" ><out:str value="${userCareerOverviewModel.overView}" /></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
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
