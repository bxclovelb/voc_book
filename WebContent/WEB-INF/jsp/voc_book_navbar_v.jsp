<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<img src="/voc_info/res/images/bingo.png" style="float: left"> <a
			class="brand" href="javascript:void(0);">英语</a>
		<ul class="nav">
			<li><a href="javascript:void(0);" onclick="goToVocInfo();">个人词汇信息</a>
			</li>
			<li class="active"><a id="a_vocbook" href="javascript:void(0);">词汇本</a>
			</li>
			<li><a id="a_voc_test" href="javascript:void(0);" onclick="goToVocTest();">词汇量测试</a>
			</li>
			<li><a id="a_voc_exe" href="javascript:void(0);" onclick="goToVocExer();">词汇练习</a>
			</li>
			<li><a>|</a></li>
			<li>
				<select id="select_model" style="margin:5px 0 0 15px;width:150px">
					<option value="0">正常模式</option>
					<option value="1">逆序模式</option>
					<option value="2">分类模式</option>
					<option value="3">笔记本模式</option>
				</select>
				<button style="margin:5px 0 0 15px;" onclick="switchModel();">切换模式</button>
			</li>
		</ul>
	</div>
</div>
