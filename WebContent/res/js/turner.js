	//转向个人词汇信息
	function goToVocInfo(){
		window.location = "/voc_info/showInfo?userId="+userId;
	} 
	
	//转向词汇量测试
	function goToVocTest(){
		window.location = "/voc_test/showPage?userId="+userId;
	}

	//转向词汇练习
	function goToVocExer(){
		//题目套数
		var numExer = 1;
		
		$.getJSON("/voc_info/getRandomExers?numExer="+numExer,function(data){
			window.location = "/voc_exer/showExpadding?userId="+userId+"&serialNumber=v-31-"+data.ids[0];	
		});
		/*$.getJSON("/voc_info/index.php/voc_info_c/get_random_exers/"+num_exer,function(data){
			window.location = "/voc_exe/index.php/voc_exe_c/index/"+userId+"/v-31-"+data[0];	
		});*/
	}