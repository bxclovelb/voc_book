<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>英语词汇本—逆序模式</title>

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
		setWordsProcess();
		setCurrModel();
		getWords('a',1);
	});

	$(document).ready(function(){
		$("#jquery_jplayer").jPlayer({
			swfPath:"/voc_book/res/js",
			supplied:"mp3"
		});
	});

	//根据首字母和当前页生成分页
	function createPagination(alphabet){
		$.getJSON("/voc_book/getWordsCountInverse?alphabet="+alphabet+"&theBand="+band,
			function(data){
				var pages = Math.ceil(data.count / 30);	
				pagination(data,alphabet,pages);
			});
	}

//  获得单词
	function getWords(alphabet,pageNo) {
		$("#hidden_pageNo").val(pageNo);
		$("#hidden_param").val(alphabet);
		$.ajax({
			type : "get",
			url : "/voc_book/getWordsInverse?alphabet="+alphabet+"&pageNo="+pageNo
					+"&userId="+userId+"&theBand="+band+"&rand="+Math.random(),
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
				createPagination(alphabet);
			}
		});
	}
	
	</script>
</head>
<body style="background-image: url(/voc_book/res/images/cloudsbg.jpg);background-repeat: repeat-x;">
	<!-- navbar -->
	<s:include value="voc_book_navbar_v.jsp"></s:include>
	<!-- navbar end -->	
	
	<div class="container" style="width: 930px; _width: 1005px">
		<s:include value="voc_book_head_v.jsp"></s:include>

		<div
			style="background-color: white; padding-top: 30px; height: 560px; background-color: #f3f9fb">
			<div class="tabbable tabs-left" style="width: 40px; float: left; _zoom: normal">
				<ul class="nav nav-tabs" id="nav-tabs"  style="width: 40px; margin-right: 0px"> 
					<li class="active" id="li_a" style="height: 38px"><a href="javascript:void(0);"
						data-toggle="tab"
						onclick="changeTag('a');getWords('a',1 );">A</a>
					</li>
					<li id="li_b" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('b');getWords('b',1 );">B</a>
					</li>
					<li id="li_c" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('c');getWords('c',1 );">C</a>
					</li>
					<li id="li_d" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('d');getWords('d',1 );">D</a>
					</li>
					<li id="li_e" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('e');getWords('e',1 );">E</a>
					</li>
					<li id="li_f" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('f');getWords('f',1 );">F</a>
					</li>
					<li id="li_g" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('g');getWords('g',1 );">G</a>
					</li>
					<li id="li_h" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('h');getWords('h',1 );">H</a>
					</li>
					<li id="li_i" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('i');getWords('i',1 );">I</a>
					</li>
					<li id="li_j" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('j');getWords('j',1 );">J</a>
					</li>
					<li id="li_k" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('k');getWords('k',1 );">K</a>
					</li>
					<li id="li_l" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('l');getWords('l',1 );">L</a>
					</li>
					<li id="li_m" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('m');getWords('m',1 );">M</a>
					</li>
					<li id="li_n" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						onclick="changeTag('n');getWords('n',1 );">N</a>
					</li>
				</ul>
			</div>
			<div id="div_words" style="width: 850px;height:540px; float: left;background-color: #f9f6c8;padding-top:10px">
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

				<div id="div_pagination" style="clear: both;margin-left:10px"></div>
			</div>
			<div class="tabbable tabs-right" style="width: 40px; float: right;">
				<ul class="nav nav-tabs" style="width: 40px;margin-left:0px;border:0">
					<li id="li_o" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('o');getWords('o',1 );">O</a>
					</li>
					<li id="li_p" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('p');getWords('p',1 );">P</a>
					</li>
					<li id="li_q" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('q');getWords('q',1 );">Q</a>
					</li>
					<li id="li_r" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('r');getWords('r',1 );">R</a>
					</li>
					<li id="li_s" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('s');getWords('s',1 );">S</a>
					</li>
					<li id="li_t" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('t');getWords('t',1 );">T</a>
					</li>
					<li id="li_u" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('u');getWords('u',1 );">U</a>
					</li>
					<li id="li_v" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('v');getWords('v',1 );">V</a>
					</li>
					<li id="li_w" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('w');getWords('w',1 );">W</a>
					</li>
					<li id="li_x" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('x');getWords('x',1 );">X</a>
					</li>
					<li id="li_y" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('y');getWords('y',1 );">Y</a>
					</li>
					<li id="li_z" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick="changeTag('z');getWords('z',1 );">Z</a>
					</li>
					<li id="li_1" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick=" "></a></li>
					<li id="li_2" style="height: 38px"><a href="javascript:void(0);" data-toggle="tab"
						style="min-width: 14px;"
						onclick=" "></a></li>
				</ul>
			</div>
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
	<input type="hidden" id="hidden_param" value="a">
	<input type="hidden" id="hidden_pageNo" value="1">
	<input type="hidden" id="hidden_band" value="<s:property value="theBand"/>">
	<input type="hidden" id="hidden_model" value="<s:property value="model"/>">
</body>
</html>
