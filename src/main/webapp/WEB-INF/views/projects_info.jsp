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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="out" uri="http://soramameweb.com/web/tag/OutputStrTag"%>
<%@page import="java.util.List" %>
<%@page import="com.soramame.web.model.UserModel" %>
<%@page import="com.soramame.web.model.JobCategoryMstModel" %>
<!DOCTYPE html>
<html lang="<%=request.getLocale()%>">
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
    
    <title><spring:message code="common.pj.name" /> | ${projectModel.title}</title>
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
                <spring:message code="projects_info.label.title.info" />
              </caption>
              <tr>
                <th class="th-width-70 active">
                  <spring:message code="projects_info.label.title" />
                </th>
                <th class="th-width-30 active">
                  <spring:message code="projects.label.recruitment_area" />
                </th>
              </tr>
              <tr>
                <th class="th-width-100 active" colspan="2">
                  <spring:message code="projects_info.label.over_view" />
                </th>
              </tr>
            </head>
            <tbody>
              <tr>
                <td class="td-width-70">${projectModel.title}</td>
                <td class="td-width-30">${projectModel.recruitmentAreaName}</td>
              </tr>
              <tr>
                <td class="td-width-100 border-bottom" colspan="2"><out:str value="${projectModel.overView}"/></td>
              </tr>
            </tbody>
          </table>
          
          <div>
<a href="https://twitter.com/share" class="twitter-share-button" data-via="plat4u" data-related="plat4ucom">Tweet</a>
<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+'://platform.twitter.com/widgets.js';fjs.parentNode.insertBefore(js,fjs);}}(document, 'script', 'twitter-wjs');</script>
          </div>
          
          <hr>
          
      <c:choose>
        <c:when test="${loginSessionState}">
          
          <table class="table table-hover">
            <thead>
              <caption class="cptn background-primary">
                <spring:message code="projects_info.label.manager_title_info" />
              </caption>
              <tr>
                <th class="th-width-100 active" colspan="2">
                  <spring:message code="projects_info.label.manager_user_name" />
                </th>
              </tr>
              <tr>
                <th class="th-width-50 active">
                  <spring:message code="projects_info.label.manager_age_group" />
                </th>
                <th class="th-width-50 active">
                  <spring:message code="projects_info.label.manager_live" />
                </th>
              </tr>
            </head>
            <tbody>
              <tr>
                <td class="td-width-100" colspan="2">${managerModel.userName}</td>
              </tr>
              <tr>
                <td class="td-width-50 border-bottom">${managerModel.ageGroup}</td>
                <td class="td-width-50 border-bottom">${managerModel.liveName}</td>
              </tr>
            </tbody>
          </table>
          
          <table class="table table-hover">
            <thead>
              <caption class="cptn background-primary">
                <spring:message code="projects_info.label.manager_career_list" />
              </caption>
              <tr>
                <th class="th-width-30 active">
                  <spring:message code="projects_info.label.manager_career_affiliation_name" />
                </th>
                <th class="th-width-70 active"></th>
              </tr>
              <tr>
                <th class="th-width-30 active"><spring:message code="projects_info.label.manager_career_period_from_to" /></th>
                <th class="th-width-70 active"><spring:message code="projects_info.label.manager_career_job_category_name" /></th>
              </tr>
              <tr>
                <th class="th-width-100 active" colspan="2" ><spring:message code="projects_info.label.manager_career_over_view" /></th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="userCareerOverviewModel" items="${managerModel.userCareerOverviewList}">
              <tr>
                <td class="td-width-30">
                <c:out value="${userCareerOverviewModel.affiliationName}" /></td>
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
        
          <form class="form-horizontal" name="sysform" action="<%=request.getContextPath() %>/projects/${projectId}" method="post">
            <div class="form-group">
              <label class="col-md-3 control-label"><spring:message code="projects.label.access_count" /></label>
              <div class="col-md-3">
                <input type="text" class="form-control" name="projectModel.projectAccessCounter.projectAccessCount" value="${projectModel.projectAccessCounter.projectAccessCount}" readonly>
              </div>
              <label class="col-md-3 control-label"><spring:message code="projects.label.query_count" /></label>
              <div class="col-md-3">
                <input type="text" class="form-control" name="projectModel.participationHopeCounter.participationHopeCount" value="${projectModel.participationHopeCounter.participationHopeCount}" readonly>
              </div>
            </div>
                
            <div class="form-group">
              <div class="col-md-12">
              <c:choose>
                <c:when test="${sessionUserModel.role == '10'}">
                  <button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target=".bs-example-modal-lg">
                  <spring:message code="projects.button.label.query" /></button>
                </c:when>
                <c:otherwise>
                  <button type="button" class="btn btn-primary btn-lg btn-block" name="query" onclick="toRegistInfo()">
                    <spring:message code="projects.button.label.query" />
                  </button>
                </c:otherwise>
              </c:choose>
              </div>
            </div>
          
            <!-- modal -->
            <div class="modal fade bs-example-modal-lg" id="myModal" role="dialog" aria-labelledby="gridSystemModalLabel">
              <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="gridSystemModalLabel"><spring:message code="projects.label.query.title" /></h4>
                    <div>Åi<spring:message code="projects.label.query.supplement" />Åj</div>
                  </div>
                  <div class="modal-body">
                    <div class="container-fluid">
                      <div class="row">
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="projects.label.query.player_name" /></label>
                          <div class="col-md-4">
                            <input type="text" class="form-control" name="userName" value="${playerModel.userName}" readonly>
                          </div>
                          <div class="col-md-5">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="projects.label.query.player_email" /></label>
                          <div class="col-md-9">
                            <input type="text" class="form-control" name="email" value="${playerModel.email}" readonly>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="projects.label.query.player_age_group" /></label>
                          <div class="col-md-4">
                            <input type="text" class="form-control" name="ageGroup" value="${playerModel.ageGroup}" readonly>
                          </div>
                          <label class="col-md-2 control-label"><spring:message code="projects.label.query.player_live" /></label>
                          <div class="col-md-3">
                            <input type="text" class="form-control" name="liveName" value="${playerModel.liveName}" readonly>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="projects.label.query.player_job_category" /></label>
                          <div class="col-md-9">
                            <input type="text" class="form-control" name="userCareerOverviewList[0].jobCategoryName" value="${playerModel.userCareerOverviewList[0].jobCategoryName}" readonly>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="col-md-3 control-label"><spring:message code="projects.label.query.player_over_view" /></label>
                          <div class="col-md-9">
                            <textarea class="form-control" rows="15" name="userCareerOverviewList[0].overView" readonly><c:out value="${playerModel.userCareerOverviewList[0].overView}"/></textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                    <input type="hidden" name="userCareerOverviewList[0].periodFrom" value="${playerModel.userCareerOverviewList[0].periodFrom}">
                    <input type="hidden" name="userCareerOverviewList[0].affiliationName" value="${playerModel.userCareerOverviewList[0].affiliationName}">
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="projects.button.label.cancel" /></button>
                      <button type="submit" class="btn btn-primary"><spring:message code="projects.button.label.query" /></button>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div><!-- /.modal -->
              
            </div>
          </form>
            
        </c:when>
      <c:otherwise>
      
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title"><spring:message code="guide.label.no_register" /></h3>
            </div>
            <div class="panel-body">
              <form class="form-horizontal" name="sysform" action="<%=request.getContextPath() %>/users/new">
                <button type="submit" class="btn btn-primary btn-lg btn-block" name="actionbtn"><spring:message code="guide.label.user.register" /></button>
              </form>
            </div>
          </div>
          
          <div class="panel panel-primary">
            <div class="panel-heading">
              <h3 class="panel-title"><spring:message code="guide.label.no_login" /></h3>
            </div>
            <div class="panel-body">
              <form class="navbar-form navbar-left" action="<%=request.getContextPath() %>/login" method="post">
                <div class="form-group">
                  <input type="text" name="userName" placeholder="<spring:message code="guide.placeholder.user_id" />" class="form-control" value="">
                </div>
                <div class="form-group">
                  <input type="password" name="password" autocomplete="off" placeholder="<spring:message code="guide.placeholder.password" />" class="form-control" value="">
                </div>
                <button type="submit" class="btn btn-primary btn-lg"><spring:message code="guide.button.label.login" /></button>
              </form>
            </div>
          </div>
            
      </c:otherwise>
    </c:choose>
          
        </div>
        
      </div>

      <hr>

      <!-- footer -->
      <jsp:include page="footer.jsp" flush="false" />

    </div> <!-- /container -->
    <script type="text/javascript">
    function toRegistInfo() {
    	document.sysform.action = "<%=request.getContextPath() %>/guideRd";
    	document.sysform.method = "get";
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
