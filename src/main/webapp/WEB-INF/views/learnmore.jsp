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
    
    <title><spring:message code="common.pj.name" /> | <spring:message code="index.head.title" /> </title>
  </head>

  <body>

    <!-- nav -->
    <jsp:include page="nav.jsp"/>

    <div class="container">
      <div class="row">
        <div class="col-md-2">
        </div>
        <div class="col-md-8">
          <article>
            <header><h1>Plat for Projects</h1></header>
            <section>
              <h5>自身のスキルを磨く個人様、プロジェクトのプレイヤを招集したいプロジェクトマネージャ様へ、コミュニケーションのきっかけをおつくり致します。</h5>
            </section>
            <section>
              <h4>ルールは簡単。</h4>
              <h5>プロジェクトマネージャ様、個人様それぞれがアカウントをご登録すること。</h5>
              <h5>プロジェクトマネージャ様、個人様それぞれが情報を公開すること（全ての情報を登録すること）。</h5>
              <h5>同一のプロジェクトまたは個人様に対して発信するスカウトや参加希望は<small>(*1)</small>一定時に１回。</h5>
            </section>
            <section>
              <h5>以上のルールからプロジェクトからスカウトをしたり、プロジェクトへの参加希望を行うことができます<small>(*2)</small>。</h5>
            </section>
            <section>
              <div><small>(*1)スパム防止の観点より3日間スカウトや参加希望はできません。</small></div>
              <div><small>(*2)自身が登録したメールアドレスを相手に提供することになります。</small></div>
            </section>
            <footer>
              <a class="btn btn-primary btn-lg btn-block" href="<%=request.getContextPath() %>/users/new" role="button">
                <spring:message code="learnmore.button.title.register" />
              </a>
            </footer>
          </article>
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
