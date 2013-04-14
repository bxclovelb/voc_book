<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>冰果英语词汇本—分类模式</title>

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
<script src="/voc_book/res/js/turner.js"
	type="text/javascript"></script>

<link href="/voc_book/res/css/jquery-ui-1.10.1.custom.min.css"
	rel="stylesheet">
<link href="/voc_book/res/css/bootstrap.min.css" rel="stylesheet">
<link href="/voc_book/res/css/style.css" rel="stylesheet">
<link href="/voc_book/res/css/models.css" rel="stylesheet">


<script type="text/javascript">

	var userId = "";
	var band = 0;
	var tooltip_on = true;

	$(function() {
		userId = $("#hidden_userId").val();
		band = $("#hidden_band").val();
		setCurrModel();
		setWordsProcess();
		getWords(1,1);
	});

	$(document).ready(function(){
		$("#jquery_jplayer").jPlayer({
			swfPath:"/voc_book/res/js",
			supplied:"mp3"
		});
	});

	//根据首字母和当前页生成分页
	function createPagination(catId){
		$.getJSON("/voc_book/getWordsCountCategory?catId="+catId+"&theBand="+band,
			function(data){
				var pages = Math.ceil(data.count / 30);	
				pagination(data,catId,pages);
			});
	}

//  获得单词
	function getWords(catId,pageNo) {
		$("#hidden_pageNo").val(pageNo);
		$("#hidden_param").val(catId);
		$.ajax({
			type : "get",
			url : "/voc_book/getWordsCategory?catId="+catId+"&pageNo="+pageNo+"&userId="
					+userId+"&band="+band+"&rand="+Math.random(),
			dataType : "json",
			success : function(data, textStatus) {
				//清除原有内容
				clearDivs();
				$("#div_pagination").html("");
				
				//生成各按钮
				createButtons(data);

				//设置tooltip状态
				toggleTooltip();
				
				//生成分页
				createPagination(catId);
			}
		});
	}
	
	</script>

<!--[if IE 6]>
	<link href="/voc_book/res/css/ie6.min.css" rel="stylesheet">
<![endif]-->
</head>
<body style="background-image: url(/voc_book/res/images/cloudsbg.jpg);background-repeat: repeat-x;">
	<!-- navbar -->
	<s:include value="voc_book_navbar_v.jsp"></s:include>
	<!-- navbar end -->	
	
	<div class="container" style="width: 1005px">
		<s:include value="voc_book_head_v.jsp"></s:include>

		<div
			style="background-color: white; padding-top: 30px; height: 560px; background-color: #f3f9fb">

			<div class="tabbable tabs-left"
				style="width: 80px; float: left; _zoom: normal">
				<ul class="nav nav-tabs" id="nav-tabs"
					style="width: 80px; margin-right: 0px">
					<li class="active" id="li_1" style="height: 38px"><a
						href="javascript:void(0);" data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('1');getWords(1,1 );">伟人记事</a>
					</li>
					<li id="li_2" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('2');getWords(2,1 );">家庭家人</a>
					</li>
					<li id="li_3" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('3');getWords(3,1 );">音乐电影</a>
					</li>
					<li id="li_4" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('4');getWords(4,1 );">交通旅游</a>
					</li>
					<li id="li_5" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('5');getWords(5,1 );">饭店银行</a>
					</li>
					<li id="li_6" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('6');getWords(6,1 );">学习教育</a>
					</li>
					<li id="li_7" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('7');getWords(7,1 );">天气气候</a>
					</li>
					<li id="li_8" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('8');getWords(8,1 );">科学技术</a>
					</li>
					<li id="li_9" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('9');getWords(9,1 );">工作求职</a>
					</li>
					<li id="li_10" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('10');getWords(10,1 );">健康人生</a>
					</li>
					<li id="li_11" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('11');getWords(11,1 );">人际关系</a>
					</li>
					<li id="li_12" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('12');getWords(12,1 );">文化生活</a>
					</li>
					<li id="li_13" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('13');getWords(13,1 );">金融经济</a>
					</li>
					<li id="li_14" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						style="min-width: 68px; width: 68px; padding-right: 0"
						onclick="changeTag('14');getWords(14,1 );">植物农业</a>
					</li>
				</ul>
			</div>
			<div id="div_words"
				style="width: 850px; height: 540px; float: left; background-color: #f9f6c8; padding-top: 10px">
				<div id="div_word_1"
					style="width: 130px; float: left; margin-left: 10px;"></div>
				<div id="div_word_2"
					style="width: 130px; float: left; margin-left: 10px;"></div>
				<div id="div_word_3"
					style="width: 130px; float: left; margin-left: 10px;"></div>
				<div id="div_word_4"
					style="width: 130px; float: left; margin-left: 10px;"></div>
				<div id="div_word_5"
					style="width: 130px; float: left; margin-left: 10px;"></div>
				<div id="div_word_6"
					style="width: 130px; float: left; margin-left: 10px;"></div>

				<div id="div_pagination" style="clear: both; margin-left: 10px"></div>
			</div>
			<!-- <div class="tabbable tabs-right" style="width: 80px; float: right;">
				<ul class="nav nav-tabs"
					style="width: 80px; margin-left: 0px; border: 0">
					<li id="li_o" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick="">自定义</a></li>
					<li id="li_p" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_q" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_r" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_s" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_t" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_u" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_v" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_w" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_x" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_y" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_z" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_1" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
					<li id="li_2" style="height: 38px"><a href="javascript:void(0);"
						style="min-width: 68px; width: 68px; padding-right: 0"
						data-toggle="tab" onclick=""> </a></li>
				</ul>
			</div> -->
		</div>
	</div>
	<div id="div_exercise" style="display: none;">
		<div id="div_exercise_data"
			style="overflow: auto; height: 330px; border-right: 1px solid #ddd"></div>
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
	<div id="div_message" style="display: none;"></div>
	<div id="div_temp_area" style="width:1px;height:0px;position:fixed;"></div>
	<input type="hidden" id="hidden_userId" value="<s:property value="userId"/>">
	<input type="hidden" id="hidden_param" value="1">
	<input type="hidden" id="hidden_pageNo" value="1">
	<input type="hidden" id="hidden_band" value="<s:property value="theBand"/>">
	<input type="hidden" id="hidden_model" value="<s:property value="model"/>">

	<script>
		$(function(){if($.browser.msie&&parseInt($.browser.version,10)===6){$('.row div[class^="span"]:last-child').addClass("last-child");$(':button[class="btn"], :reset[class="btn"], :submit[class="btn"], input[type="button"]').addClass("button-reset");$(":checkbox").addClass("input-checkbox");$('[class^="icon-"], [class*=" icon-"]').addClass("icon-sprite");$(".pagination li:first-child a").addClass("pagination-first-child")}})
	</script>
</body>
</html>
