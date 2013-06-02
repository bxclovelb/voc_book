//根据答题情况更新数据库
	function updateDegreeByExer(word,score) {
		$.ajax({
			url:"/voc_book/updateDegreeByExer?userId="+userId+"&word="+word+
				"&score="+score+"&rand="+Math.random(),
			type:"get",
			dataType:"json",
			success:function(){
				var param = $("#hidden_param").val();
				var pageNo = $("#hidden_pageNo").val();
				getWords(param,pageNo);
			}
		});
	} 	

//获得相关练习
	function getRelatedExercise(word){
		var param = $("#hidden_param").val();
		var pageNo = $("#hidden_pageNo").val();
		$.getJSON("/voc_book/getRelatedExercise?word="+word,
			function(data){
				$("#div_exercise_data").html("");
				var ans = new Array();
				for(var i=0;i<data.relatedExercise.length;i++){
					var div = $("<div id='div_question_"+i+"'>"+"</div>");
					if(i%2 == 0){
						div.css("background-color","#c8e3f9");
					}else{
						div.css("background-color","#8fd6df");
					}
					$("#div_exercise_data").append(div);

					if(data.relatedExercise[i][6] == null || data.relatedExercise[i][6] == ""){
						div.append("<span class='badge badge-success'>"+(i+1) + "</span> <strong>选择单词的正确释义:</strong>  <span id='span_result_"+i+"'></span> <br/>");
					}else{
						div.append("<span class='badge badge-success'>"+(i+1) + "</span> <strong>" + data.relatedExercise[i][6] + "</strong>  <span id='span_result_"+i+"'></span><br/>");
					}
					
					div.append("<form id='form_"+i+"'><table style='width:100%'><tr>"
						+"<td><input type='radio' name='choice' style='width:20px' value='"+data.relatedExercise[i][2]+"'><span id='span_a_"+i+"'>A."+data.relatedExercise[i][2]+"</span></td>"
						+"<td><input type='radio' name='choice' style='width:20px' value='"+data.relatedExercise[i][3]+"'><span id='span_b_"+i+"'>B."+data.relatedExercise[i][3]+"</span></td>"
						+"<td><input type='radio' name='choice' style='width:20px' value='"+data.relatedExercise[i][4]+"'><span id='span_c_"+i+"'>C."+data.relatedExercise[i][4]+"</span></td>"
						+"<td><input type='radio' name='choice' style='width:20px' value='"+data.relatedExercise[i][5]+"'><span id='span_d_"+i+"'>D."+data.relatedExercise[i][5]+"</span></td>"
						+"</tr></table>"+"</form>");
				}
				
				if(data.relatedExercise.length != 0){
					var button = $("<input type='button' value='     提交     ' class='btn btn-large btn-info'>");
					button.click(function(){
						//清理标签
						for(var i=0;i<data.relatedExercise.length;i++){
							$("#span_result_"+i).removeClass();
							$("#span_result_"+i).html("");
							$("#span_a_"+i).removeClass();
							$("#span_b_"+i).removeClass();
							$("#span_c_"+i).removeClass();
							$("#span_d_"+i).removeClass();
						}
						
						var rightCount = 0;//记录答对题数
						for(var i=0;i<data.relatedExercise.length;i++){
							if(data.relatedExercise[i][1] == $("#form_"+i+" :checked").val()){//答对
								$("#span_result_"+i).addClass("label label-success");
								$("#span_result_"+i).html("<i class='icon-ok'></i");
								rightCount++;
							}else{//答错
								$("#span_result_"+i).addClass("label label-important");
								$("#span_result_"+i).html("<i class='icon-remove'></i");
								
								if(data.relatedExercise[i][1] == data.relatedExercise[i][2]){
									$("#span_a_"+i).addClass("label label-warning");
								}else if(data.relatedExercise[i][1] == data.relatedExercise[i][3]){
									$("#span_b_"+i).addClass("label label-warning");
								}else if(data.relatedExercise[i][1] == data.relatedExercise[i][4]){
									$("#span_c_"+i).addClass("label label-warning");
								}else if(data.relatedExercise[i][1] == data.relatedExercise[i][5]){
									$("#span_d_"+i).addClass("label label-warning");
								}
							}
						}
						
						//根据答题情况更新数据库
						var score = Math.ceil(rightCount*100/data.relatedExercise.length);
						updateDegreeByExer(word,score);
						
						//用户提示
						switch (score) {
						case 0:
							$("#div_message").html("您的得分是：0分。该词为生词。");
							break;
						case 100:
							$("#div_message").html("您的得分是：100分。该词为熟词。");
							break;
						default:
							$("#div_message").html("您的得分是："+score+"分。该词为半熟词。");
						}
						$("#div_message").dialog({
							autoOpen:false,
							draggable:true,
							resizable:false,
							width:300,
							height:200,
							dialogClass:"dialogBorder",
							title:"<div style='font-size:16px;'><i class='icon-ok icon-white'></i><strong>冰果英语助手提醒您</strong></div>"
						});
						$("#div_message").dialog("open");
						
						//更新显示
						//getWords(param,pageNo);
					});
					var d = $("<div align='center'></div>")
					d.append(button);
					$("#div_exercise_data").append(d);
					
				}else{
					$("#div_exercise_data").append("无相关题目");
				}
				
				$("#div_exercise").dialog({
					autoOpen:false,
					draggable:true,
					modal:true,
					resizable:false,
					width:600,
					height:400,
					dialogClass:"dialogBorder",
					title:"<div style='font-size:18px;'><i class='icon-book'></i><strong> "+word+" 的相关练习</strong></div>"
				});
				$("#div_exercise").dialog("open");
		});
	}

	// 获得单词的相关词
	function getAssociation(word){
		$.getJSON("/voc_book/getAssociation?word="+word,
				function(data){
				for(var i=0;i<3;i++){
					clearDiv("div_association_"+(i+1));
				}
				if(data.association.length == 0){
					$("#div_association_1").append("无联想词。");
				}
				for(var i=0;i<data.association.length;i++){
					var button = $("<button style='width:150px;height:40px;margin-left:25px;border:1px solid #ddd' class='btn btn-danger' type='button'>"+data.association[i]+"</button>");
					button.bind("click",{relativeword:data.association[i]},function(event){getAssociation(event.data.relativeword);});
					$("#div_association_"+(i%3+1)).append(button);
					$("#div_association_"+(i%3+1)).append("<br/>");

					//题目按钮
					var btn_exer = $("<a class='btn btn-warning btn-mini' type='button' style='float:right;font-size:12px;margin:0'>题</a>");
					btn_exer.bind("click",{relativeword:data.association[i]},function(event){
						getRelatedExercise(event.data.relativeword);
					});
					$("#div_association_"+(i%3+1)).append(btn_exer);
					//详细按钮
					var btn_details = $("<a class='btn btn-info btn-mini' type='button' style='float:right;font-size:12px;margin:0'>详</a>");
					btn_details.bind("click",{relativeword:data.association[i]},function(event){
						getDetails(event.data.relativeword);
						getNotes(event.data.relativeword);
					});
					$("#div_association_"+(i%3+1)).append(btn_details);
					
					//if((i+1) % 3 == 0){
						$("#div_association_"+(i%3+1)).append("<br/><br/><br/>");
					//}
				}

				$("#div_association").dialog({
					autoOpen:false,
					draggable:true,
					modal:true,
					resizable:false,
					width:600,
					height:400,
					dialogClass:"dialogBorder",
					title:"<div style='font-size:18px;'><i class='icon-bookmark'></i><strong> "+word+" 的相关词</strong></div>"
				});
				$("#div_association").dialog("open");
				
		});
	}

	// 设置单词的tooltip解释
	function setFirstDetail(button){
		button.tooltip({
			items:"button",
			hide:{duration:1},
			show:{duration:1}
		});
		$.getJSON("/voc_book/getFirstDetail?word="+button.text(),
				function(data){
				var str = "无"; 
				if(data != null && data != ""){
					str = "<p><i class='icon-hand-right'></i>单词："+data.firstDetail[0][0]+"</p>";
					if(data.firstDetail[0][1] != null && data.firstDetail[0][1] != ""){
						str += "<p><i class='icon-hand-right'></i>中文解释：" + data.firstDetail[0][1]+"</p>";
					}
					if(data.firstDetail[0][2] != null && data.firstDetail[0][2] != ""){
						str += "<p><i class='icon-hand-right'></i>英文解释：" + data.firstDetail[0][2]+"</p>";
					}
					if(data.firstDetail[0][3] != null && data.firstDetail[0][3] != ""){
						str += "<p><i class='icon-hand-right'></i>例子：" + data.firstDetail[0][3]+"</p>";
					}
				}
				button.tooltip("option","content",str);
		});
	}
	
	// 获得单词的详细信息 
	function getDetails(word){
		$.getJSON("/voc_book/getDetails?word="+word,
				function(data){
				$("#div_details_data").html("");
				for(i = 0;i < data.details.length;i++){
					if(data.details.length > 1){
						$("#div_details_data").append("<p><strong>["+(i+1)+"]</strong></p>");
					}
					
					if(data.details[i][1] != null && data.details[i][1] != ""){
						str = "中文解释：" + data.details[i][1];
						$("#div_details_data").append("<p>"+str+"</p>");
					}
					if(data.details[i][2] != null && data.details[i][2] != ""){
						str = "英文解释：" + data.details[i][2];
						$("#div_details_data").append("<p>"+str+"</p>");
					}
					if(data.details[i][3] != null && data.details[i][3] != ""){
						str = "例子：" + data.details[i][3];
						$("#div_details_data").append("<p>"+str+"</p>");
					}
					$("#div_details_data").append("<br/>");
				}
				
				$("#div_details").dialog({
					autoOpen:false,
					draggable:true,
					modal:true,
					resizable:false,
					width:600,
					height:400,
					dialogClass:"dialogBorder",
					title:"<div style='font-size:18px;'><i class='icon-tags'></i><strong id='strong_details_title'> "+word+"</strong></div>"
				});
				$("#div_details").dialog("open");
		});
	}
	
	//获得用户笔记
	function getNotes(word){
		$.getJSON("/voc_book/getNotes?userId="+userId+"&word="+word+"&rand="+Math.random(),
				function(data){
				$("#notes").val("");
				if(data.notes.length != 0){
					$("#notes").val(data.notes[0]);
				}
		});
	}
	
	//保存用户笔记
	function saveNotes(){
		if($("#notes").val() != null && $("#notes").val() != ""){
			$.ajax({
				url:"/voc_book/saveNotes?rand="+Math.random(),
				type:"post",
				dataType:"json",
				data:{
					userId:userId,
					notes:$("#notes").val(),
					word:$("#strong_details_title").text()
				},
				success:function(data,textStatus){
					if(data.success){
						$("#div_message").html("保存成功！");
						$("#div_message").dialog({
							autoOpen:false,
							draggable:true,
							resizable:false,
							width:300,
							height:200,
							dialogClass:"dialogBorder",
							title:"<div style='font-size:16px;'><i class='icon-ok icon-white'></i><strong>冰果英语助手提醒您</strong></div>"
						});
					}else{
						$("#div_message").html("保存失败，请稍后重试。");
						$("#div_message").dialog({
							autoOpen:false,
							draggable:true,
							resizable:false,
							width:300,
							height:200,
							dialogClass:"dialogBorder",
							title:"<div style='font-size:16px;'><i class='icon-remove icon-white'></i><strong>冰果英语助手提醒您</strong></div>"
						});
					}
					$("#div_message").dialog("open");
				}
			});
		}
	}
	
	//转换标签
	function changeTag(param){
		$(".nav-tabs .active").removeClass();
		$("#li_"+param).addClass("active");
	}
	
	//待更改程度单词队列
	var wordsQueue = new Array();
	//更改程度信号量
	var wordSemaphore = true;
	
	//更新单词熟悉程度
	function updateDegree(word){
		$.ajax({
			type : "get",
			url : "/voc_book/updateDegree?userId="+userId+"&word="+word+"&rand="+Math.random(),
			dataType : "json",
			success : function(data, textStatus) {
				setWordsProcess();
				
				if(wordsQueue.length != 0){
					updateDegree(wordsQueue.shift());
				}else{
					wordSemaphore = true;
				}
			}
		});
		
	}
	
	//删除div里的数据
	function clearDiv(divId){
		$("#"+divId).html("");
	}
	
	//删除divs
	function clearDivs(){
		for(var i=0;i<6;i++){
			clearDiv("div_word_"+(i+1));
		}
	}

	//播放Mp3
	function playMp3_2(){
		var word = $.trim($("#strong_details_title").text());
		var alphabet = word.substr(0,1);
		
		jwplayer("div_mp3").setup({
			file:"http://www.bingoenglish.com/mediafile/words/"+alphabet+"/"+word+".mp3",
	        width:1,
	        height:0
		    });
		jwplayer().play();
	}
	
	//播放Mp3
	function playMp3(){
		var word = $.trim($("#strong_details_title").text());
		var alphabet = word.substr(0,1);
		var filename = "http://www.bingoenglish.com/mediafile/words/"+alphabet+"/"+word+".mp3";
		$("#jquery_jplayer").jPlayer("setMedia",{
			mp3: filename
		});
		$("#jquery_jplayer").jPlayer("play");
	}
	
	//切换模式
	function switchModel(){
		var model = $("#select_model :selected").val();
		if(model == 0){
			window.location = "/voc_book/showNormal?userId="+userId+"&theBand="+band+"&model=0";
		}else if(model == 1){
			window.location = "/voc_book/showInverse?userId="+userId+"&theBand="+band+"&model=1";
		}else if(model == 2){
			window.location = "/voc_book/showCategory?userId="+userId+"&theBand="+band+"&model=2";
		}else if(model == 3){
			window.location = "/voc_book/showNotes?userId="+userId+"&theBand="+band+"&model=3";
		}
	}
	
	//设置当前模式
	function setCurrModel(){
		var model = $("#hidden_model").val();
		$("#select_model :nth-child("+(parseInt(model)+1)+")").attr("selected","selected");
		
	}
	
	//获得单词按钮
	function getWordButton(word){
		return $("<button style='white-space: normal;width:130px;height:42px;margin:0;border:1px solid #ddd;' class='btn btn-large btn-danger tooltip-flag' type='button'>"+word+"</button>");
	}
	
	//如果是重点词则加图标
	function setImportant(important,button,parentDiv){
		if(important){
			if($.browser.msie&&parseInt($.browser.version,10)===6){
				//button.css("background-image","url('/voc_book/res/images/star.gif')");
				var span = $("<span style='position:absolute;'><img src='/voc_book/res/images/flag.png'></span>");
				parentDiv.append(span);
				span.offset({top:span.offset().top,left:span.offset().left+110});
			}else{
				//button.css("background-image","url('/voc_book/res/images/star.png')");
				var span = $("<span style='position:absolute;'><img src='/voc_book/res/images/flag.png'></span>");
				parentDiv.append(span);
				span.offset({top:span.offset().top,left:span.offset().left+110});
			}
		}
	}
	
	//根据熟悉程度改变按钮颜色
	function setDegree(degree,button){
		if(degree == 0){
			button.removeClass("btn-danger");
			button.addClass("btn-warning");
		}else if(degree == 1){
			button.removeClass("btn-danger");
			button.addClass("btn-success");
		}else{
			button.removeClass("btn-success");
			button.addClass("btn-danger");
		}
	}
	

	//更新单词显示
	function updateWordDisplay(degree,button,word){
		var messageDiv = $("<div style='position:fixed'></div>");
		$("#div_temp_area").append(messageDiv);
		messageDiv.html("");
		messageDiv.attr("align","center");
		messageDiv.show();
		messageDiv.width(130);
		messageDiv.height(40);
		messageDiv.offset({top:button.offset().top - 20,left:button.offset().left});
		messageDiv.hide("drop",{direction:"up"},1300,function(){messageDiv.remove();});
		if(degree + 1 > 1){
			degree = -1;
		}else{
			degree ++;
		}
		
		//改变单词按钮颜色
		setDegree(degree,button);
		
		//提示单词程度改变
		if(degree == -1){
			messageDiv.html("降为 <strong style='color:#d64b45'>生词</strong> <i class='icon-arrow-down'></i>");
		}else if(degree == 0){
			messageDiv.html("提升为 <strong style='color:#f9a52d'>半熟词</strong> <i class='icon-arrow-up'></i>");
		}else{
			messageDiv.html("提升为 <strong style='color:#58b258'>熟词</strong> <i class='icon-arrow-up'></i>");
		}
		
		//程度改变，重新绑定
		button.unbind("click");
		bindChangeDegree(word,button,degree);
	}
	
	//绑定点击更换单词熟悉度
	function bindChangeDegree(word,button,degree){
		button.bind("click",{word:word},
				function(event){
					updateWordDisplay(degree,button,event.data.word);
					
					//
					wordsQueue.push(event.data.word);
					
					if(wordSemaphore){
						wordSemaphore = false;
						updateDegree(wordsQueue.shift());
					}
					
				});
	}
	
	//添加 练习，详细，联系 三个按钮
	function addTheThreeButtons(word,i){
		//联系按钮
		var btn_asso = $("<a class='btn btn-success btn-mini' type='button' style='float:right;font-size:12px;margin:0'>联</a>");
		btn_asso.bind("click",{word:word},function(event){
			getAssociation(event.data.word);
		});
		$("#div_word_"+(i%6+1)).append(btn_asso);

		//题目按钮
		var btn_exer = $("<a class='btn btn-warning btn-mini' type='button' style='float:right;font-size:12px;margin:0'>题</a>");
		btn_exer.bind("click",{word:word},function(event){
			getRelatedExercise(event.data.word);
		});
		$("#div_word_"+(i%6+1)).append(btn_exer);

		//详细按钮
		var btn_details = $("<a class='btn btn-info btn-mini' type='button' style='float:right;font-size:12px;margin:0'>详</a>");
		btn_details.bind("click",{word:word},function(event){
			getDetails(event.data.word);
			getNotes(event.data.word);
		});
		$("#div_word_"+(i%6+1)).append(btn_details);
	}
	
	//生成各按钮
	function createButtons(data){
		for(var i=0;i<data.words.length;i++){
			var button = getWordButton(data.words[i]);

			//如果是重点词则加图标
			setImportant(data.importants[i],button,$("#div_word_"+(i%6+1)));

			//根据熟悉程度改变按钮颜色
			setDegree(data.degrees[i],button);

			//绑定点击更换单词熟悉度
			bindChangeDegree(data.words[i],button,data.degrees[i]);
			
			//单词的弹出解释
			setFirstDetail(button);

			//添加按钮
			$("#div_word_"+(i%6+1)).append(button);

			//添加 练习，详细，联系 三个按钮
			addTheThreeButtons(data.words[i],i);
			
			$("#div_word_"+(i%6+1)).append("<br/><br/><br/><br/>");
		}
	}
	
	//分页
	function pagination(data,param,pages){
		var pageNo = $("#hidden_pageNo").val();
		if(pages > 0){
			$("#div_pagination").paginate({
				count 		: pages,
				start 		: pageNo,
				display     : 7,
				border					: true,
				border_color			: '#bd362f',
				text_color  			: '#fff',
				background_color    	: '#da4f49',	
				border_hover_color		: '#ccc',
				text_hover_color  		: '#000',
				background_hover_color	: '#fff', 
				images					: false,
				mouse					: 'press',
				onChange     			: function(page){
											getWords(param,page);
										  }
			});
		}
	}
	
	//设置单词进度
	function setWordsProcess(){
		var model = $("#hidden_model").val();
		$.getJSON("/voc_book/getCountDiffDegree?userId="+userId+"&model="+model+"&rand="+Math.random(),
			function(data){
				var high = Math.ceil(data.high *100 / (data.high + data.middle + data.low));
				var middle = Math.ceil(data.middle *100 / (data.high + data.middle + data.low));
				
				$("#div_bar_high").css("width",high+"%");
				$("#div_bar_high").attr("title","熟词 "+data.high+" 个");
				$("#div_bar_middle").css("width",middle+"%");
				$("#div_bar_middle").attr("title","半熟词 "+data.middle+" 个");
				$("#div_bar_low").css("width",(100-high-middle)+"%");
				$("#div_bar_low").attr("title","生词 "+data.low+" 个");
				
			}
		);
		
	}
	
	//转换tooltip的状态
	function toggleTooltip(){
		if(tooltip_on){
			$("#a_tooltip_button").toggle(
				function(){
					tooltip_on = false;
					$("#a_tooltip_button img").attr("src","/voc_book/res/images/tooltip_close.png")
					$(".tooltip-flag").tooltip("disable");
				},
				function(){
					tooltip_on = true;
					$("#a_tooltip_button img").attr("src","/voc_book/res/images/tooltip_open.png");
					$(".tooltip-flag").tooltip("enable");
				}
			);
		}else{
			$(".tooltip-flag").tooltip("disable");
			$("#a_tooltip_button").toggle(
					function(){
						tooltip_on = true;
						$("#a_tooltip_button img").attr("src","/voc_book/res/images/tooltip_open.png");
						$(".tooltip-flag").tooltip("enable");
					},
					function(){
						tooltip_on = false;
						$("#a_tooltip_button img").attr("src","/voc_book/res/images/tooltip_close.png")
						$(".tooltip-flag").tooltip("disable");
					}
				);
		}
	}
	
	function exit(){
		$.getJSON("/voc_info/logout",
			function(data){
			if(!data.success){
				alert("未知错误！");
			}else{
				alert("退出成功！");
				window.location = "/voc_info/showLogin";
			}
		});
	}