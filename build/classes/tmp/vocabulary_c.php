<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Vocabulary_c extends CI_Controller {
	function test(){
		$this->load->view('test');
	}
	
	//默认页面
	function index($user_id){
		$data["user_id"] = $user_id;
		$this->load->view('vocabulary_index',$data);
	}
	
	//跳转页面
	function redirect($user_id,$band="0",$model)
	{
		$data["user_id"] = $user_id;
		$data["band"] = $band;
		$data["model"] = $model;
		$this->load->view('vocabulary_redirect',$data);
	}
	
	//选择页面
	function show_choose()
	{
		$this->load->view('vocabulary_choose_v');
	}
	
	//正序页面
	function show_normal($user_id,$band="0",$model)
	{
		$data["user_id"] = $user_id;
		$data["band"] = $band;
		$data["model"] = $model;
		$this->load->view('vocabulary_normal_v',$data);
	}
	
	//逆序页面
	function show_inverse($user_id,$band="0",$model)
	{
		$data["user_id"] = $user_id;
		$data["band"] = $band;
		$data["model"] = $model;
		$this->load->view('vocabulary_inverse_v',$data);
	}
	
	//分类页面
	function show_category($user_id,$band="0",$model)
	{
		$data["user_id"] = $user_id;
		$data["band"] = $band;
		$data["model"] = $model;
		$this->load->view('vocabulary_category_v',$data);
	}
	
	//笔记页面
	function show_notes($user_id,$band="0",$model)
	{
		$data["user_id"] = $user_id;
		$data["band"] = $band;
		$data["model"] = $model;
		$this->load->view('vocabulary_notes_v',$data);
	}
	
	//json检查页面
	function show_json()
	{
		$this->load->view('vocabulary_json_v');
	}
	
	
	
	//检查用户ID，如果没有则保存
	function check_user_id($user_id,$band="0") {
		$this->load->model("vocabulary_m");
		$this->vocabulary_m->check_user_id($user_id,$band);
	}
	
	//获得以某一字母打头的单词
	function get_words($alphabet,$pageNo,$user_id,$band) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_words($band,$alphabet,($pageNo-1)*30,30);
		$degrees = $this->vocabulary_m->get_degrees($user_id,$result);
		$importants = $this->vocabulary_m->get_importants($band,$result);
		
		$data["result"] = $result;
		$data["degree"] = $degrees;
		$data["important"] = $importants;
		echo json_encode($data) ;
	}
	
	//获得以某一字母打头的单词个数
	function get_words_count($alphabet,$band) {
		$this->load->model("vocabulary_m");
		$count = $this->vocabulary_m->get_words_count($band,$alphabet);
		$arr = array("count"=>$count,"alphabet"=>$alphabet);
		echo json_encode($arr);
	}
	
	//获得单词的相关单词
	function get_association($word) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_association($word);
		echo json_encode($result) ;
	}
	
	//获得单词的tooltip解释
	function get_first_detail($word) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_first_detail($word);
		echo json_encode($result) ;
	}
	
	//获得单词的全部详细信息
	function get_details($word) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_details($word);
		echo json_encode($result) ;
	}
	
	//获得某单词的用户笔记
	function get_notes($user_id,$word) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_notes($user_id,$word);
		echo json_encode($result) ;
	}
	
	//保存用户笔记
	function save_notes() {
		$user_id = $_POST["user_id"];
		$notes = $_POST["notes"];
		$word = $_POST["word"];
		
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->save_notes($user_id,$notes,trim($word));
		echo json_encode($result) ;
	}
	
	//根据分类ID查找对应表中的单词个数
	function get_words_count_category($cat_id,$band) {
		$this->load->model("vocabulary_m");
		$count = $this->vocabulary_m->get_category_words_count($band,$cat_id);
		$arr = array("count"=>$count);
		echo json_encode($arr) ;
	}
	
	//根据分类ID查找对应表中的单词
	function get_words_category($cat_id,$pageNo,$user_id,$band) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_category_words($band,$cat_id,($pageNo-1)*30,30);
		$degrees = $this->vocabulary_m->get_degrees($user_id,$result);
		$importants = $this->vocabulary_m->get_importants($band,$result);
		
		$data["result"] = $result;
		$data["degree"] = $degrees;
		$data["important"] = $importants;
		echo json_encode($data) ;
	}
	
	//获得以某一字母结尾的单词
	function get_words_inverse($anti_alphabet,$pageNo,$user_id,$band) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_words_inverse($band,$anti_alphabet,($pageNo-1)*30,30);
		if ($result == null){
			$degrees = null;
			$importants = null;
		}else{
			$degrees = $this->vocabulary_m->get_degrees($user_id,$result);
			$importants = $this->vocabulary_m->get_importants($band,$result);
		}
		
		$data["result"] = $result;
		$data["degree"] = $degrees;
		$data["important"] = $importants;
		echo json_encode($data) ;
	}
	
	//获得以某一字母结尾的单词个数
	function get_words_count_inverse($anti_alphabet,$band) {
		$this->load->model("vocabulary_m");
		$count = $this->vocabulary_m->get_words_count_inverse($band,$anti_alphabet);
		$arr = array("count"=>$count,"anti_alphabet"=>$anti_alphabet);
		echo json_encode($arr);
	}
	
	//获得单词的相关练习
	function get_related_exercise($word){
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_related_exercise($word);
		echo json_encode($result);
	}
	
	//更新单词熟悉程度
	function update_degree($user_id,$word) {
		$this->load->model("vocabulary_m");
		$degree = $this->vocabulary_m->update_degree($user_id,$word);
		echo json_encode($degree);
	}
	
	//根据答题情况更新数据库
	function update_degree_by_exer($user_id,$word,$score){
		$this->load->model("vocabulary_m");
		$this->vocabulary_m->update_degree_by_exer($user_id,$word,$score);
	}
	
	//获得用户的所有笔记
	function get_user_notes($user_id,$pageNo) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_user_notes($user_id,($pageNo-1)*10,10);
		$degrees = $this->vocabulary_m->get_degrees($user_id,$result);
		
		$data["result"] = $result;
		$data["degree"] = $degrees;
		echo json_encode($data);
	}
	
	//获得用户的所有笔记个数
	function get_user_notes_count($user_id) {
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_user_notes_count($user_id);
	
		echo json_encode($result);
	}
	
	//获得最高和中等程度单词个数
	function get_count_diff_degree($user_id,$model){
		$this->load->model("vocabulary_m");
		$result = $this->vocabulary_m->get_count_diff_degree($user_id,$model);
		
		echo json_encode($result);
	}
}

