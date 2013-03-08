	//转向个人词汇信息
	function goToVocInfo(){
		window.location = "/voc_info/index.php/voc_info_c/index/"+userId;
	} 
	
	//转向词汇量测试
	function goToVocTest(){
		window.location = "/voc_test/index.php/voc_test_c/index/"+userId;
	}

	//转向词汇练习
	function goToVocExer(){
		//题目套数
		var num_exer = 1;
		
		$.getJSON("/voc_info/index.php/voc_info_c/get_random_exers/"+num_exer,function(data){
			window.location = "/voc_exe/index.php/voc_exe_c/index/"+userId+"/v-31-"+data[0];	
		});
	}