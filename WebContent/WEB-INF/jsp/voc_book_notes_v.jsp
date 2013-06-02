<%@page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>英语词汇本—用户笔记本</title>

<script src="/voc_book/res/js/jquery-1.8.2.min.js"
	type="text/javascript"></script>
<script src="/voc_book/res/js/jquery-ui-1.9.2.custom.min.js"
	type="text/javascript"></script>
<script src="/voc_book/res/js/jquery.paginate.js"
	type="text/javascript"></script>
<script src="/voc_book/res/js/voc_book_normal_operation.js"
	type="text/javascript"></script>
<script src="/voc_book/res/js/jquery.jplayer.min.js"
	type="text/javascript"></script>
<script src="/voc_book/res/js/turner.js" type="text/javascript"></script>

<link href="/voc_book/res/css/jquery-ui-1.10.1.custom.min.css"
	rel="stylesheet">
<link href="/voc_book/res/css/bootstrap.min.css" rel="stylesheet">
<link href="/voc_book/res/css/style.css" rel="stylesheet">

<style type="text/css">
.dialogBorder {
	border: 2px solid #5c9ccc;
}

body {
	background: url(/voc_book/res/images/cloudsbg.jpg);
}
</style>

<script type="text/javascript">
	var userId = "";
	var band = 0;

	$(function(){
		userId = $("#hidden_userId").val();
		band = $("#hidden_band").val();
		setCurrModel();
		getWords(null,1);
	});

	$(document).ready(function(){
		$("#jquery_jplayer").jPlayer({
			swfPath:"/voc_book/res/js",
			supplied:"mp3"
		});
	});

	//根据 首字母,当前页和级别 生成分页
	function createPagination(){
		$.getJSON("/voc_book/getUserNotesCount?userId="+userId+"&rand="+Math.random(),
			function(data){
				var count = data.count;
				var pages = Math.ceil(count / 10);
				pagination(count,null,pages);	
			});
	}

	//更新单词熟悉程度
	function updateDegree(word){
		$.ajax({
			type : "get",
			url : "/voc_book/updateDegree?userId="+userId+"&word="+word+"&rand="+Math.random(),
			dataType : "json",
			success : function(data, textStatus) {
				var param = $("#hidden_param").val();
				var pageNo = $("#hidden_pageNo").val();
				getWords(param,pageNo);
			}
		});
	}

	function getWords(param,pageNo){
		$("#hidden_pageNo").val(pageNo);
		$.ajax({
			type : "get",
			url : "/voc_book/getUserNotes?userId="+userId+"&pageNo="+pageNo+"&rand="+Math.random(),
			dataType:"json",
			success:function(data,textStatus){
				//清除原来内容
				$("#tbody_notes").html("");	
				
				for(var i=0;i<data.result.length;i++){
					//生成单词按钮
					var button = $("<button style='width:197px;height:44px' class='btn btn-large btn-danger' type='button'>"+data.result[i][0]+"</button>");
	
					//根据熟悉程度改变按钮颜色
					setDegree(data.degree[i],button);
	
					//绑定点击更换单词熟悉度
					button.bind("click",{word:data.result[i][0]},
						function(event){
							updateDegree(event.data.word);
						}
					);
					
					//单词的弹出解释
					setFirstDetail(button);
	
					//联系按钮
					var btn_asso = $("<a class='btn btn-success btn-small' type='button' style='float:right;font-size:12px;margin-right:22px'>联</a>");
					btn_asso.bind("click",{word:data.result[i][0]},function(event){
						getAssociation(event.data.word);
					});
	
					//题目按钮
					var btn_exer = $("<a class='btn btn-warning btn-small' type='button' style='float:right;font-size:12px'>题</a>");
					btn_exer.bind("click",{word:data.result[i][0]},function(event){
						getRelatedExercise(event.data.word);
					});
	
					//详细按钮
					var btn_details = $("<a class='btn btn-info btn-small' type='button' style='float:right;font-size:12px'>详</a>");
					btn_details.bind("click",{word:data.result[i][0]},function(event){
						getDetails(event.data.word);
						getNotes(event.data.word);
					});
	
	
					//添加行到表格
					var tr = $("<tr></tr>");
					var td1 = $("<td width='25%'></td>");
					td1.append(button);
					td1.append("<br/>");
					td1.append(btn_asso);
					td1.append(btn_exer);
					td1.append(btn_details);
					tr.append(td1);
					var td2 = $("<td width='55%' style='font-size:12pt'></td>");
					if(data.result[i][1] != null)
					{
						td2.append(data.result[i][1].substring(0,50));
						if(data.result[i][1].length > 50){
							td2.append("...");
						}
					}
					tr.append(td2);
					var td4 = $("<td width='20%'>"+data.result[i][2].replace("T"," ")+"</td>");
					tr.append(td4);
					$("#tbody_notes").append(tr);
					
				}

				//生成分页
				createPagination();
			}
		});
			
	}
</script>
</head>
<body
	style="background-image: url(/voc_book/res/images/cloudsbg.jpg); background-repeat: repeat-x;">
	<!-- navbar -->
	<s:include value="voc_book_navbar_v.jsp"></s:include>
	<!-- navbar end -->

	<div class="container">
		<!-- <div
			style="margin-top: 40px; padding: 10px; height: 60px; background: url(/voc_book/res/css/images/ui-bg_diagonals-thick_18_b81900_40x40.png) repeat fixed">
			<h1 style="color: white;">用户笔记本</h1>
		</div>  -->

		<div id="div_head" class="div-head"
			style="margin-top: 40px; color: white; font-size: 40pt; padding: 1%; height: auto; background: url('/voc_book/res/images/bg_head.jpg');">
			<img src="/voc_book/res/images/head.png"> <span
				style="margin-left: 2%">用户笔记本</span>
		</div>
		<div style="background-color: #54aff7; height: 10px; width: 100%"></div>

		<div style="background-color:white;padding-top:20px;background-color:#f3f9fb">
			<table class="table table-hover">
				<thead>
					<tr>
						<td width='25%'><h4>单词</h4></td>
						<td width='55%'><h4>笔记</h4></td>
						<td width='20%'><h4>修改日期</h4></td>
					</tr>
				</thead>
				<tbody id="tbody_notes"></tbody>
			</table>
			<div id="div_pagination" style="clear: both;"></div>
		</div>
	</div>

	<div id="div_exercise" style="display: none;">
		<div id="div_exercise_data"
			style="overflow: scroll; height: 330px; border-right: 1px solid #ddd"></div>
	</div>
	<div id="div_details" style="display: none;">
		<div style='float: left;'>
			<div id="div_details_sound"
				style="margin-left: 10px; width: 270px; height: 20px; border-top: 1px solid #c8e3f9">
				<a href="###" onclick="playMp3();"> <img
					src="/voc_book/res/images/voice.png">
				</a>
			</div>
			<div id="div_details_data"
				style="margin-left: 10px; overflow: auto; width: 270px; height: 300px; border-top: 1px solid #c8e3f9; border-bottom: 1px solid #c8e3f9"></div>
		</div>
		<div
			style="float: left; margin-left: 10px; border: 2px dotted #54aff7; height: 320px;"></div>
		<div
			style="float: left; width: 250px; margin-left: 10px; background-color: #eee; height: 330px;">
			<div style="background-color: #bbb; width: 100%">您的笔记</div>
			<div
				style="background-color: #ddd; border-bottom: 5px solid #54aff7;">
				<textarea id="notes" rows="12"
					style="margin: 5px; width: 225px; resize: none;"></textarea>
			</div>
			<div align="right" style="margin-top: 10px">
				<button class="btn btn-small" onclick="saveNotes();">保存</button>
			</div>
		</div>
	</div>

	<div id="jquery_jplayer"></div>

	<div id="div_association"
		style="width: 600px; height: 400px; display: none">
		<div id="div_association_1" style="float: left; width: 175px"></div>
		<div id="div_association_2" style="float: left; width: 175px"></div>
		<div id="div_association_3" style="float: left; width: 175px"></div>
	</div>
	
	<div id="div_message"></div>

	<input type="hidden" id="hidden_userId" value='<s:property value="userId"/>'>
	<input type="hidden" id="hidden_param" value="">
	<input type="hidden" id="hidden_pageNo" value="1">
	<input type="hidden" id="hidden_band" value="<s:property value="theBand"/>">
	<input type="hidden" id="hidden_model" value="<s:property value="model"/>">
</body>
</html>
