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

<h3>��P���i���p�K��̓K�p�͈͂���ѕύX�j</h3>
<h5>1.���p�K��i�ȉ��u�{�K��v�Ƃ����܂��j�́A�Ղ���ƁB���񋟂���T�[�r�X�i�ȉ��u�{�T�[�r�X�v�Ƃ����܂��j�������p���������ۂ́A�Ղ���ƁB�Ɨ��p�҂Ƃ̊Ԃ̈�؂̊֌W�ɓK�p����܂��B���p�҂͖{�K��ɓ��ӂ��������ŁA���Ȃ̐ӔC�ɂ����Ė{�T�[�r�X�𗘗p������̂Ƃ��܂��B�Ȃ��A�{�T�[�r�X�ɂ����ăA�J�E���g�o�^���̎葱�Ȃ����p�\�ȃT�[�r�X�ɂ��ẮA���Y�T�[�r�X�𗘗p���邱�Ƃɂ��{�K��ɓ��ӂ������̂Ƃ݂Ȃ���܂��B</h5>
<h5>2.�{�T�[�r�X�̂����A�{�K��Ƃ͕ʂɒǉ��̗��p�������݂����Ă���ꍇ�ɂ́A���p�҂͓��Y���p������{�K��ƍ��킹�ď��炵�Ȃ���΂Ȃ�Ȃ����̂Ƃ��܂��B���̏ꍇ�ɂ����āA�ǉ��̗��p�����Ɩ{�K��ɈقȂ��߂�����ꍇ�ɂ́A���̌��x�Œǉ��̗��p�������D�悵�ēK�p�������̂Ƃ��܂��B�܂��A�{�K��Ƃ͕ʂɃT�[�r�X�̌ʂ̗��p�K�񂪐݂����Ă���ꍇ�ɂ́A���Y�T�[�r�X�̗��p�ɂ������ẮA�ʂ̗��p�K�񂪓K�p�������̂Ƃ��܂��B</h5>
<h5>3.�Ղ���ƁB�́A���p�҂ւ̎��O���m�Ȃ��A�{�K��������I�Ȕ͈͓��ŕύX���邱�Ƃ��ł�����̂Ƃ��܂��B�{�K�񂪕ύX���ꂽ�ꍇ�A���p�҂́A�{�T�[�r�X�̗��p���p�����邱�Ƃɂ���ĕύX��̋K��ɓ��ӂ������̂Ƃ݂Ȃ���A�Ղ���ƁB�Ɨ��p�҂Ƃ̊Ԃ̈�؂̊֌W�ɂ͕ύX��̋K�񂪓K�p�������̂Ƃ��܂��B </h5>

<h3>��2���i�p��̒�`�j</h3>
<h5>1.�u���p�ҁv�Ƃ́A�{�T�[�r�X�𗘗p����S�Ă̎҂������A�A�J�E���g�o�^�����҂��܂݂܂��B</h5>
<h5>2.�u�A�J�E���g�v�Ƃ́A�A�J�E���g�o�^�����҂������܂��B</h5>
<h5>3.�u���[�U���v�Ƃ́A�v���C�����A�}�l�[�W�����A�v���W�F�N�g���������܂��B</h5>

<h3>��3���i���p��~���j</h3>
<h5>1.�Ղ���ƁB�́A���p�҂����̊e���̂����ꂩ�̎��R�ɊY������ꍇ�ɂ́A���O�ɒʒm���邱�ƂȂ��A���A���Y���p�҂̏����𓾂邱�ƂȂ��A���Y���p�҂ɂ��{�T�[�r�X�̗��p��~�A���Y���p�҂̃p�X���[�h�Ȃǂ̕ύX�A���Y���p�҂̃A�J�E���g���i�̎�����ȂǕK�v�ȑ[�u���Ƃ邱�Ƃ��ł�����̂Ƃ��܂��B�܂��A�Ղ���ƁB�́A���Y�[�u���Ƃ������R�𓖊Y���p�҂ɑ΂��ĊJ������`���𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>�i1�j�{�K��Ɉᔽ�����ꍇ</h5>
<h5>�i2�j���[�U���ɋ��U�����邱�Ƃ����������ꍇ</h5>
<h5>�i3�j�F�؏���s���Ɏg�p�����ꍇ</h5>
<h5>�i4�j�{�T�[�r�X�ɂ���Ē񋟂��ꂽ����s���Ɏg�p�����ꍇ</h5>
<h5>�i5�j�Ղ���ƁB�܂��͑�O�҂ɑ��Q��^����댯������ꍇ</h5>
<h5>�i6�j��4���ɒ�߂�֎~�s�ׂ��s�����ꍇ</h5>
<h5>�i7�j���̑��A�Ղ���ƁB���{�T�[�r�X�̗��p�ɂ��ĕs�K���Ɣ��f�����ꍇ</h5>
<h5>2.�Ղ���ƁB���O���ɒ�߂�[�u���Ƃ������Ƃɂ�藘�p�҂ɑ��Q�����������ꍇ�ł����Ă��A�Ղ���ƁB�͈�ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B </h5>

<h3>��4���i�Ɛӎ����j</h3>
<h5>1.�Ղ���ƁB����тՂ���ƁB�Ƌ��͊֌W�ɂ���p�[�g�i�[�́A�{�T�[�r�X�ɂ���Ē񋟂�����̐��m���A���S������ш��S���Ȃǂ�ۏ؂�����̂ł͂���܂���B���Y���ɋN�����ė��p�҂���ё�O�҂ɑ��Q�����������Ƃ��Ă��A�Ղ���ƁB����тՂ���ƁB�Ƌ��͊֌W�ɂ���p�[�g�i�[�͈�ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>2.�Ղ���ƁB�́A�{�T�[�r�X�Ɋւ��A���̃C���^�[�l�b�g�T�[�r�X���ƘA�g���Ĉꕔ�̋@�\��T�[�r�X��񋟂��邱�Ƃ�����܂��B���̏ꍇ�ɂ����āA�{�T�[�r�X��ɓ��e���ꂽ���e���A�������̉��A���̃C���^�[�l�b�g�T�[�r�X���Ɍ��J�����ꍇ�����邱�Ƃɂ��āA���p�҂́A�Ղ���ƁB�܂��͂Ղ���ƁB�̎w�肷���O�҂ɑ΂��Ĉًc���q�ׂ邱�Ƃ��ł��Ȃ����̂Ƃ��܂��B</h5>
<h5>3.�{�T�[�r�X�̓C���^�[�l�b�g�T�[�r�X�ł���A���p�҂́A�{�T�[�r�X�̗��p�ɂ������ẮA���e�̗�������ю��ȂɊւ�����̌��J�E����J����ѓ��e���e�ɂ��āA���p�Ҏ��g�̐ӔC�ɂ����ĊǗ�������̂Ƃ��A�����ꗘ�p�Ҏ��g�̑I���ɂ����̌��J�܂��͓��e�ɔ������Q�����������Ƃ��Ă��A�Ղ���ƁB�͈�ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>4.�Ղ���ƁB�́A���p�҂ɔ��������g�p�@��̈편�A�f�[�^�̖Ŏ��A�Ɩ��̒��f�A�܂��͂������ނ̑��Q�i�Ԑڑ��Q�A���ʑ��Q�A�t�����Q�A�h�����Q�A�편���v���܂ށj�ɑ΂��āA���Ƃ��Ղ���ƁB�������鑹�Q�̉\�������O�ɒʒm����Ă����Ƃ��Ă��A��ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>5.�Ղ���ƁB�́A���p�҂��{�T�[�r�X�̗��p�ɂ���āA���̗��p�҂���ё�O�҂ɑ΂��ė^�������Q�A�Ȃ�тɗ��p�Ҏ��g�ɐ��������Q����ѕ����ɂ��āA��ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>6.�{�T�[�r�X�̒񋟂��󂯂邽�߂̃C���^�[�l�b�g�ւ̐ڑ��́A���p�҂����Ȃ̔�p�ōs�����̂Ƃ��A�Ղ���ƁB�͈�؂̔�p����ѐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>7.���p�҂����{�@�ł������Ҍ_��@�̓K�p���󂯂�ꍇ�ɂ����āA�{�T�[�r�X�̒񋟂Ɋւ��A�Ղ���ƁB�̌̈ӂ܂��͏d�ߎ��ɂ���ė��p�҂ɑ��Q�����������Ƃ��́A�{�K��ɂ����ĂՂ���ƁB�̖Ɛӂ��߂�K��͓K�p����Ȃ����̂Ƃ��܂��B</h5>

<h3>��5���i�֎~�����j</h3>
<h5>1.�Ղ���ƁB�́A���p�҂��{�T�[�r�X�𗘗p����ɂ�����A���̊e���Ɍf����s�ׂ��֎~���܂��B</h5>
<h5>�i1�j�@�߂܂��͌����Ǒ��ɔ�����s��</h5>
<h5>�i2�j�ƍߓI�s�ׂ������A�܂��͂��̎��s���Î�����s��</h5>
<h5>�i3�j���̗��p�ҁA��O�҂܂��͂Ղ���ƁB�̒m�I���Y���A�ё����A�p�u���V�e�B���Ȃǂ̐����Ȍ�����N�Q����A�܂��͐N�Q�̂����ꂪ����s��</h5>
<h5>�i4�j���̗��p�ҁA��O�҂܂��͂Ղ���ƁB�̍��Y�A�M�p�A���_�܂��̓v���C�o�V�[��N�Q����A�܂��͐N�Q�̂����ꂪ����s��</h5>
<h5>�i5�j���̗��p�҂܂��͑�O�҂ɖ��f�œ��Y�l���̌l�����ł���������J����s��</h5>
<h5>�i6�j�{�T�[�r�X�̒񋟂����n��ɂ����Ė@�߂ɔ�����s��</h5>
<h5>�i7�j���̗��p�҂܂��͑�O�҂ɕs���v�܂��͑��Q��^����s��</h5>
<h5>�i8�j���̗��p�҂܂��͑�O�҂��排�������s��</h5>
<h5>�i9�j�ߓx�܂��͕s�K�؂ɓ���̊O���E�F�u�T�C�g�֗U�����邱�Ƃ��ړI�Ɣ��f�����s��</h5>
<h5>�i10�j�����E��N�҂ɑ΂����e��������Ɣ��f�����s��</h5>
<h5>�i11�j��@�E�L�Q�Ɣ��f�����A�_���g�T�C�g�E�o��n�T�C�g�Ȃǂւ̃����N�s��</h5>
<h5>�i12�j�F�؏����O�҂ɏ��n�A�ݗ^���邱�ƁA�܂��͑�O�҂Ƌ��p����s��</h5>
<h5>�i13�j�{�T�[�r�X�̒񋟂�W����s��</h5>
<h5>�i14�j�{�T�[�r�X�y�і{�T�[�r�X�̊֘A�A�v���P�[�V�����\�t�g�E�F�A�ɂ��āA�����A���ρA���o�[�X�E�G���W�j�A�����O�A�t�R���p�C���܂��͋t�A�Z���u��������s��</h5>
<h5>�i15�j�Ղ���ƁB���{�T�[�r�X��񋟂��邽�߂̐ݔ��i�Ղ���ƁB���{�T�[�r�X��񋟂��邽�߂Ɏg�p���܂��͎g�p���悤�Ƃ���d�q�v�Z�@���̑��̋@��y�у\�t�g�E�F�A�������܂��B�ȉ������B�j�̐���ȓ����W���A�܂��̓T�[�r�X�p�ݔ��������̓f�[�^��j��A���󂷂�s��</h5>
<h5>�i16�j�R���s���[�^�E�C���X���L�Q�ȃR���s���[�^�v���O��������{�T�[�r�X��ʂ��Ă܂��͂���Ɋ֘A���āA�z�z����s��</h5>
<h5>�i17�j�{�K��Ɉᔽ����s��</h5>
<h5>�i18�j���̑��A�Ղ���ƁB���s�K���Ɣ��f����s��</h5>
<h5>2.�O���e���ɊY������s�ׂɂ���ĂՂ���ƁB�����炩�̑��Q�������ꍇ�A�Ղ���ƁB�́A���Y���Q�����̗��p�҂ɔ�������悤�������邱�Ƃ��ł�����̂Ƃ��܂��B </h5>

<h3>��6���i�{�T�[�r�X�̈ꎞ�I�Ȓ��f�j</h3>
<h5>�Ղ���ƁB�́A���̊e���Ɍf���鎖�R������ꍇ�A���p�҂Ɏ��O�ɘA�����邱�ƂȂ��A�ꎞ�I�ɖ{�T�[�r�X�̒񋟂𒆒f���邱�Ƃ�����܂��B�{�T�[�r�X�̒��f�ɂ�鑹�Q�ɂ��āA�Ղ���ƁB�́A��ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B</h5>
<h5>�i1�j�Ղ���ƁB�̃V�X�e���̕ێ�A�_���A�C���Ȃǂ��s���ꍇ</h5>
<h5>�i2�j�΍ЁA��d�܂��͓V�Вn�ςɂ��{�T�[�r�X�̒񋟂��ł��Ȃ��Ȃ����ꍇ</h5>
<h5>�i3�j�^�p��܂��͋Z�p��A�{�T�[�r�X�̒񋟂��ł��Ȃ��Ȃ����ꍇ</h5>
<h5>�i4�j���̑��A�Ղ���ƁB�����f��������𓾂Ȃ��Ɣ��f�����ꍇ </h5>

<h3>��7���i�{�T�[�r�X�̒ǉ��E�ύX�E��~�E�p�~�j</h3>
<h5>1.�Ղ���ƁB�́A�{�T�[�r�X�̓��e���A���p�҂ւ̎��O���m�Ȃ��ǉ��܂��͕ύX���邱�Ƃ��ł��܂��B</h5>
<h5>2.�Ղ���ƁB�́A�{�T�[�r�X���A���p�҂ւ̎��O���m�Ȃ���~�܂��͔p�~���邱�Ƃ��ł�����̂Ƃ��܂��B</h5>
<h5>3.�O�񍀂Ɋ�Â����p�҂���ё�O�҂ɑ��Q�����������Ƃ��Ă��A�Ղ���ƁB�͈�ؐӔC�𕉂�Ȃ����̂Ƃ��܂��B </h5>

<h3>��8���i�{�K��̌��́j</h3>
<h5>�{�K��͕���27�N10��1�����甭��������̂Ƃ��A�ߋ��̋K��ɗD�悵�ēK�p�������̂Ƃ��܂��B </h5>

<h3>�y�����z</h3>
<h5>�ŏI�X�V���F����27�N10��1�� </h5>

<h5>�ȏ�</h5>


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
