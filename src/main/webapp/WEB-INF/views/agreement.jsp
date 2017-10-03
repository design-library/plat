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
	<%
	response.setHeader("Pragma","no-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("X-Frame-Options","deny");
	response.setHeader("X-XSS-Protection","1; mode=block");
	response.setHeader("X-Content-Type-Options","nosniff");
	%>

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

<h3>第１条（利用規約の適用範囲および変更）</h3>
<h5>1.利用規約（以下「本規約」といいます）は、ぷらっと。が提供するサービス（以下「本サービス」といいます）をご利用いただく際の、ぷらっと。と利用者との間の一切の関係に適用されます。利用者は本規約に同意したうえで、自己の責任において本サービスを利用するものとします。なお、本サービスにおいてアカウント登録等の手続なく利用可能なサービスについては、当該サービスを利用することにより本規約に同意したものとみなされます。</h5>
<h5>2.本サービスのうち、本規約とは別に追加の利用条件が設けられている場合には、利用者は当該利用条件を本規約と合わせて順守しなければならないものとします。この場合において、追加の利用条件と本規約に異なる定めがある場合には、その限度で追加の利用条件が優先して適用されるものとします。また、本規約とは別にサービスの個別の利用規約が設けられている場合には、当該サービスの利用にあたっては、個別の利用規約が適用されるものとします。</h5>
<h5>3.ぷらっと。は、利用者への事前告知なく、本規約を合理的な範囲内で変更することができるものとします。本規約が変更された場合、利用者は、本サービスの利用を継続することによって変更後の規約に同意したものとみなされ、ぷらっと。と利用者との間の一切の関係には変更後の規約が適用されるものとします。 </h5>

<h3>第2条（用語の定義）</h3>
<h5>1.「利用者」とは、本サービスを利用する全ての者をいい、アカウント登録した者を含みます。</h5>
<h5>2.「アカウント」とは、アカウント登録した者をいいます。</h5>
<h5>3.「ユーザ情報」とは、プレイヤ情報、マネージャ情報、プロジェクト情報をいいます。</h5>

<h3>第3条（利用停止等）</h3>
<h5>1.ぷらっと。は、利用者が次の各号のいずれかの事由に該当する場合には、事前に通知することなく、かつ、当該利用者の承諾を得ることなく、当該利用者による本サービスの利用停止、当該利用者のパスワードなどの変更、当該利用者のアカウント資格の取消しなど必要な措置をとることができるものとします。また、ぷらっと。は、当該措置をとった理由を当該利用者に対して開示する義務を負わないものとします。</h5>
<h5>（1）本規約に違反した場合</h5>
<h5>（2）ユーザ情報に虚偽があることが判明した場合</h5>
<h5>（3）認証情報を不正に使用した場合</h5>
<h5>（4）本サービスによって提供された情報を不正に使用した場合</h5>
<h5>（5）ぷらっと。または第三者に損害を与える危険がある場合</h5>
<h5>（6）第4条に定める禁止行為を行った場合</h5>
<h5>（7）その他、ぷらっと。が本サービスの利用について不適当と判断した場合</h5>
<h5>2.ぷらっと。が前項に定める措置をとったことにより利用者に損害が発生した場合であっても、ぷらっと。は一切責任を負わないものとします。 </h5>

<h3>第4条（免責事項）</h3>
<h5>1.ぷらっと。およびぷらっと。と協力関係にあるパートナーは、本サービスによって提供する情報の正確性、完全性および安全性などを保証するものではありません。当該情報に起因して利用者および第三者に損害が発生したとしても、ぷらっと。およびぷらっと。と協力関係にあるパートナーは一切責任を負わないものとします。</h5>
<h5>2.ぷらっと。は、本サービスに関し、他のインターネットサービス等と連携して一部の機能やサービスを提供することがあります。この場合において、本サービス上に投稿された内容が、一定条件の下、他のインターネットサービス等に公開される場合があることについて、利用者は、ぷらっと。またはぷらっと。の指定する第三者に対して異議を述べることができないものとします。</h5>
<h5>3.本サービスはインターネットサービスであり、利用者は、本サービスの利用にあたっては、投稿の履歴および自己に関する情報の公開・非公開および投稿内容について、利用者自身の責任において管理するものとし、万が一利用者自身の選択による情報の公開または投稿に伴い損害が発生したとしても、ぷらっと。は一切責任を負わないものとします。</h5>
<h5>4.ぷらっと。は、利用者に発生した使用機会の逸失、データの滅失、業務の中断、またはあらゆる種類の損害（間接損害、特別損害、付随損害、派生損害、逸失利益を含む）に対して、たとえぷらっと。がかかる損害の可能性を事前に通知されていたとしても、一切責任を負わないものとします。</h5>
<h5>5.ぷらっと。は、利用者が本サービスの利用によって、他の利用者および第三者に対して与えた損害、ならびに利用者自身に生じた損害および紛争について、一切責任を負わないものとします。</h5>
<h5>6.本サービスの提供を受けるためのインターネットへの接続は、利用者が自己の費用で行うものとし、ぷらっと。は一切の費用および責任を負わないものとします。</h5>
<h5>7.利用者が日本法である消費者契約法の適用を受ける場合において、本サービスの提供に関し、ぷらっと。の故意または重過失によって利用者に損害が発生したときは、本規約においてぷらっと。の免責を定める規定は適用されないものとします。</h5>

<h3>第5条（禁止事項）</h3>
<h5>1.ぷらっと。は、利用者が本サービスを利用するにあたり、次の各号に掲げる行為を禁止します。</h5>
<h5>（1）法令または公序良俗に反する行為</h5>
<h5>（2）犯罪的行為を助長、またはその実行を暗示する行為</h5>
<h5>（3）他の利用者、第三者またはぷらっと。の知的財産権、肖像権、パブリシティ権などの正当な権利を侵害する、または侵害のおそれがある行為</h5>
<h5>（4）他の利用者、第三者またはぷらっと。の財産、信用、名誉またはプライバシーを侵害する、または侵害のおそれがある行為</h5>
<h5>（5）他の利用者または第三者に無断で当該人物の個人を特定できる情報を公開する行為</h5>
<h5>（6）本サービスの提供される地域において法令に反する行為</h5>
<h5>（7）他の利用者または第三者に不利益または損害を与える行為</h5>
<h5>（8）他の利用者または第三者を誹謗中傷する行為</h5>
<h5>（9）過度または不適切に特定の外部ウェブサイトへ誘導することが目的と判断される行為</h5>
<h5>（10）児童・若年者に対し悪影響があると判断される行為</h5>
<h5>（11）違法・有害と判断されるアダルトサイト・出会い系サイトなどへのリンク行為</h5>
<h5>（12）認証情報を第三者に譲渡、貸与すること、または第三者と共用する行為</h5>
<h5>（13）本サービスの提供を妨げる行為</h5>
<h5>（14）本サービス及び本サービスの関連アプリケーションソフトウェアについて、複製、改変、リバース・エンジニアリング、逆コンパイルまたは逆アセンブルをする行為</h5>
<h5>（15）ぷらっと。が本サービスを提供するための設備（ぷらっと。が本サービスを提供するために使用しまたは使用しようとする電子計算機その他の機器及びソフトウェアをいいます。以下同じ。）の正常な動作を妨げ、またはサービス用設備もしくはデータを破壊、損壊する行為</h5>
<h5>（16）コンピュータウイルス等有害なコンピュータプログラム等を本サービスを通じてまたはこれに関連して、配布する行為</h5>
<h5>（17）本規約に違反する行為</h5>
<h5>（18）その他、ぷらっと。が不適当と判断する行為</h5>
<h5>2.前項各号に該当する行為によってぷらっと。が何らかの損害を被った場合、ぷらっと。は、当該損害をその利用者に賠償するよう請求することができるものとします。 </h5>

<h3>第6条（本サービスの一時的な中断）</h3>
<h5>ぷらっと。は、次の各号に掲げる事由がある場合、利用者に事前に連絡することなく、一時的に本サービスの提供を中断することがあります。本サービスの中断による損害について、ぷらっと。は、一切責任を負わないものとします。</h5>
<h5>（1）ぷらっと。のシステムの保守、点検、修理などを行う場合</h5>
<h5>（2）火災、停電または天災地変により本サービスの提供ができなくなった場合</h5>
<h5>（3）運用上または技術上、本サービスの提供ができなくなった場合</h5>
<h5>（4）その他、ぷらっと。が中断をせざるを得ないと判断した場合 </h5>

<h3>第7条（本サービスの追加・変更・停止・廃止）</h3>
<h5>1.ぷらっと。は、本サービスの内容を、利用者への事前告知なく追加または変更することができます。</h5>
<h5>2.ぷらっと。は、本サービスを、利用者への事前告知なく停止または廃止することができるものとします。</h5>
<h5>3.前二項に基づき利用者および第三者に損害が発生したとしても、ぷらっと。は一切責任を負わないものとします。 </h5>

<h3>第8条（本規約の効力）</h3>
<h5>本規約は平成27年10月1日から発効するものとし、過去の規約に優先して適用されるものとします。 </h5>

<h3>【附則】</h3>
<h5>最終更新日：平成27年10月1日 </h5>

<h5>以上</h5>


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
