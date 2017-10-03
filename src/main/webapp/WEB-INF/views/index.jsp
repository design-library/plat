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
<%@taglib prefix="summary" uri="http://soramameweb.com/web/tag/SummaryStrTag"%>
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
    
    <title><spring:message code="common.pj.name" /> | <spring:message code="index.head.title" /> </title>
  </head>

  <body>
  
    <!-- nav -->
    <jsp:include page="nav.jsp"/>

    <div class="container">
      <div class="jumbotron">
        <h1>Plat for Projects</h1>
        <p>自身のスキルを磨く個人様、プロジェクトのプレイヤを招集したいプロジェクトマネージャー様へ</p>
        <p>プロジェクトとプレイヤ探しをお手伝いします。
        <p><a class="btn btn-primary btn-lg" href="<%=request.getContextPath() %>/learnmore" role="button">Learn More &raquo;</a></p>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <!-- side bar projects -->
          <jsp:include page="side_projects.jsp" flush="false" />
        </div>
        <div class="col-md-8">
          <table class="table table-hover">
            <thead>
              <caption class="cptn background-primary">
                <spring:message code="index.sub_title.new_project_info" />
              </caption>
              <tr>
                <th class="th-width-60 active"><spring:message code="index.label.title" /></th>
                <th class="th-width-30 active"><spring:message code="index.label.recruitment_area" /></th>
                <th class="th-width-10 active"></th>
              </tr>
              <tr>
                <th class="th-width-100 active" colspan="3"><spring:message code="index.label.overview" /></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="projectModel" items="${projectModelNew5List}">
              <tr>
                <td class="td-width-60 "><a href="<%=request.getContextPath() %>/projects/${projectModel.projectId}">${projectModel.title }</a></td>
                <td class="td-width-30 ">${projectModel.recruitmentAreaName }</td>
                <td class="td-width-10 "></td>
              </tr>
              <tr>
                <td class="td-width-100 border-bottom" colspan="3"><summary:str value="${projectModel.overView}" length="70" /><a href="<%=request.getContextPath() %>/projects/${projectModel.projectId}"><spring:message code="common.label.continue" /></a></td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-8">
          <h4><a href="<%=request.getContextPath() %>/projects?pageNumber=1"><spring:message code="index.label.more_project_info" /> &raquo;</a></h4>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
          <!-- side bar members -->
          <jsp:include page="side_players.jsp" flush="false" />
        </div>
        <div class="col-md-8">
          <table class="table table-hover">
            <thead>
              <caption class="cptn background-primary">
               <spring:message code="index.sub_title.new_player_info" />
              </caption>
              <tr>
                <th class="th-width-30 active"><spring:message code="index.label.user_name" /></th>
                <th class="th-width-30 active"><spring:message code="index.label.age_group" /></th>
                <th class="th-width-30 active"><spring:message code="index.label.live" /></th>
                <th class="th-width-10 active"></th>
              </tr>
              <tr>
                <th class="th-width-90 active" colspan="3"><spring:message code="index.label.job_category" /></th>
                <th class="th-width-10 active"></th>
              </tr>
              <tr>
                <th class="th-width-100 active" colspan="4"><spring:message code="index.label.overview" /></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="playerModel" items="${userModelNew5List}">
              <tr>
                <td class="td-width-30"><a href="<%=request.getContextPath() %>/players/${playerModel.userName}">${playerModel.userName}</a></td>
                <td class="td-width-30">${playerModel.ageGroup}</td>
                <td class="td-width-30">${playerModel.liveName}</td>
                <td class="td-width-10"></td>
              </tr>
              <tr>
                <td class="td-width-90" colspan="3">${playerModel.userCareerOverviewList[0].jobCategoryName}</td>
                <td class="td-width-10"></td>
              </tr>
              <tr>
                <td class="td-width-90 border-bottom" colspan="4"><summary:str value="${playerModel.userCareerOverviewList[0].overView}" length="70" /><a href="<%=request.getContextPath() %>/players/${playerModel.userName}"><spring:message code="common.label.continue" /></a></td>
              </tr>
              </c:forEach>
              
            </tbody>
          </table>
        </div>
      </div>

      <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-8">
          <h4><a href="<%=request.getContextPath() %>/players?pageNumber=1"><spring:message code="index.label.more_player_info" /> &raquo;</a></h4>
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
