<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div id="div_head" class="div-head"
	style="margin-top:40px;color: white; font-size: 40pt; padding: 1%; height: auto;background: url('/voc_book/res/images/bg_head.jpg');">
	<img src="/voc_book/res/images/head.png"> <span style="margin-left: 2%">英语词汇本</span>
</div>
<div
	style="background-color: #54aff7; height: 10px; width: 100%"></div>
<div style="background-color: #c8e3f9; height: 75px; padding: 5px" id="div_direction">
	<div style="width:85%;height:100%;float:left;">
		<div style="height:50%;">
			<div style="float:left;width:13%;margin-right:1%" align="right">
				图解：
			</div>
			<div style="float:left;width:85%;">
				<img alt="" src="/voc_book/res/images/explain.png" style="margin: -2px 0 0 -3px">
			</div>
		</div>
		<div style="height: 5px"></div>
		<div style="height:50%">
			<div style="float:left;width:13%;;margin-right:1%" align="right">
				单词进度：
			</div>
			<div class="progress" style="float:left;width:85%;height:25px;margin: 5px 0 0 0">
			  <div id="div_bar_high" class="bar bar-success" style=""></div>
			  <div id="div_bar_middle" class="bar bar-warning" style=""></div>
			  <div id="div_bar_low" class="bar bar-danger" style=""></div>
			</div>
		</div>
	</div>
	<div style="width:3%;height:100%;float:left;">
		<img alt="" src="/voc_book/res/images/cut_line.png">
	</div>
	<div style="width:12%;height:100%;float:left;">
		<div style="margin-left:5%">
			<div style="float:left;">
				<div><a id="a_tooltip_button" href="###" onclick=""><img src="/voc_book/res/images/tooltip_open.png"></a></div>
			</div>
		</div>
	</div>
</div>
