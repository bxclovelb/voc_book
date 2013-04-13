<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
	  date_default_timezone_set("PRC");

class Vocabulary_m extends CI_Model {

	function __construct() {
		parent::__construct();
		$this->load->database();
	}

	public function get_band_str($band,$is_important=false) {
		$band_str = "";
		switch ($band){
			case 1:$band_str = "band_3";break;
			case 2:$band_str = "band_6";break;
			case 3:$band_str = "pets_1";
				if(!$is_important){$band_str = "band_".$band_str;}break;
			case 4:$band_str = "pets_2";
				if(!$is_important){$band_str = "band_".$band_str;}break;
			case 5:$band_str = "pets_3";
				if(!$is_important){$band_str = "band_".$band_str;}break;
			case 6:$band_str = "pets_4";
				if(!$is_important){$band_str = "band_".$band_str;}break;
			case 7:$band_str = "pets_5";
				if(!$is_important){$band_str = "band_".$band_str;}break;
			case 8:$band_str = "band_graduate";break;
			default:$band_str = "band_4";
		}
		if($is_important){
			$band_str = "important_".$band_str;
		}else{
			$band_str = $band_str." = 1";
		}
		
		return $band_str;
	}
	
	function get_importants($band,$words) {
		$band_str = $this->get_band_str($band,true);
		foreach($words as $word){
			$wd = "";
			if(strrpos($word->word,"'")){
				$wd = str_replace("'", "\'", $word->word);
			}else{
				$wd = $word->word;
			}
			
			$sql = "SELECT distinct ".$band_str." band FROM vocbook_words WHERE word ='".$wd."'";
			$query = $this->db->query($sql);
			$result = $query->result();
			
			if($result != null){
				if($result[0]->band == 1){
					$important[] = true;
				}else{
					$important[] = false;
				}
			}else{
				$important[] = false;
			}
		}
		
		return $important;
	}
	
	function get_degrees($user_id,$words) {
		foreach($words as $word){
			$wd = "";
			if(strrpos($word->word,"'")){
				$wd = str_replace("'", "\'", $word->word);
			}else{
				$wd = $word->word;
			}
			$sql = "SELECT degree FROM vocbook_users_words WHERE user_id = '".$user_id."'"
					." AND word ='".$wd."'";
			$query = $this->db->query($sql);
			$result = $query->result();
			if($result == null || $result[0]->degree == "" || $result[0]->degree == 0){
				$degrees[] = -1;
			}else if($result[0]->degree == 100){
				$degrees[] = 1;
			}else{
				$degrees[] = 0;
			}
		}
		
		return $degrees;
	}
	
	function get_words($band,$alphabet,$from,$count) {
		$band_str = $this->get_band_str($band);
		$sql = "SELECT DISTINCT word FROM vocbook_words WHERE alphabet = '".$alphabet."'"
				." AND order_id = 1 AND ".$band_str." LIMIT ".$from.",".$count;
		$query = $this->db->query($sql);
		$result = $query->result();
		
		return $result;
	}
	
	function get_words_count($band,$alphabet) {
		$band_str = $this->get_band_str($band);
		$sql = "SELECT COUNT(DISTINCT word) count FROM vocbook_words WHERE alphabet = '"
				.$alphabet."' AND order_id = 1 AND ".$band_str;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result[0]->count;
	}
	
	function get_association($word) {
		$sql = "SELECT relativeword FROM vocbook_word_association WHERE coreword = '"
				.$word."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function get_first_detail($word){
		$sql = "SELECT word,meaning_ch,meaning_en,example FROM vocbook_words WHERE word = '"
				.$word."' AND order_id = 1";
		$query = $this->db->query($sql);
		$result = $query->result();
		
		return $result;
	}
	
	function get_details($word){
		$sql = "SELECT word,meaning_ch,meaning_en,example FROM vocbook_words WHERE word = '"
				.$word."' ORDER BY order_id";
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function get_notes($user_id,$word){
		$sql = "SELECT notes FROM vocbook_users_words WHERE word = '"
				.$word."' AND user_id='".$user_id."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function save_notes($user_id,$notes,$word){
		$sql = "SELECT COUNT(*) count FROM vocbook_users_words "
				."WHERE user_id = '".$user_id."' AND word = '".$word."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		if($result[0]->count == 0){
			$sql = "SELECT voc_id,alphabet,anti_alphabet,cat_id FROM vocbook_words "
					."WHERE voc_id = (SELECT voc_id FROM vocbook_words WHERE word = '"
					.$word."' AND order_id = 1)";
			$query = $this->db->query($sql);
			$result = $query->result();
			
			$sql = "INSERT INTO vocbook_users_words(user_id,voc_id,degree,notes
					,is_additional,create_date,alphabet,anti_alphabet,cat_id,word
					,exercise_date) VALUES('".$user_id."',".$result[0]->voc_id.",0,'"
					.$notes."',0,'".date('Y-m-d H:i:s',time())."','".$result[0]->alphabet
					."','".$result[0]->anti_alphabet."',".$result[0]->cat_id.",'"
					.$word."',null)";
			$query = $this->db->query($sql);
			return $query;
		}else if($result[0]->count == 1){
			$sql = "UPDATE vocbook_users_words set notes='".$notes
					."',create_date = '".date('Y-m-d H:i:s',time())."'"
					." WHERE user_id = '".$user_id."' AND word = '".$word."'";
			$query = $this->db->query($sql);
			return $query;
		}
		return false;
	}
	
	function get_category_words_count($band,$cat_id) {
		$band_str = $this->get_band_str($band);
		$sql = "SELECT COUNT(DISTINCT c.word) count FROM vocbook_words w,cat_".$cat_id." c"
				." WHERE w.word = c.word AND w.".$band_str;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result[0]->count;
	}
	
	function get_category_words($band,$cat_id,$from,$count){
		$band_str = $this->get_band_str($band);
		$sql = "SELECT DISTINCT c.word FROM vocbook_words w,cat_".$cat_id." c"
				." WHERE w.word = c.word AND w.".$band_str." LIMIT ".$from.",".$count;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function get_words_inverse($band,$anti_alphabet,$from,$count) {
		$band_str = $this->get_band_str($band);
		$sql = "SELECT DISTINCT word FROM vocbook_words WHERE anti_alphabet = '"
				.$anti_alphabet."'"." AND order_id = 1 AND ".$band_str." LIMIT ".$from.",".$count;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function get_words_count_inverse($band,$anti_alphabet) {
		$band_str = $this->get_band_str($band);
		$sql = "SELECT COUNT(DISTINCT word) count FROM vocbook_words "
				."WHERE anti_alphabet = '".$anti_alphabet."' AND order_id = 1 AND ".$band_str;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result[0]->count;
	}
	
	function get_related_exercise($word){
		$sql = "SELECT word,choice,choice_a,choice_b,choice_c,choice_d,content "
				."FROM vocbook_word_related_exercise WHERE word = '".$word."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function check_user_id($user_id,$band) {
		$sql = "SELECT COUNT(*) count FROM vocbook_users_profile "
				."WHERE user_id = '".$user_id."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		if($result[0]->count == 0){
			$sql = "INSERT INTO vocbook_users_profile VALUES('".$user_id."',".$band
				.",'".date('Y-m-d H:i:s',time())."')";
			$this->db->query($sql);
		}
	}
	
	function update_degree($user_id,$word) {
		$sql = "SELECT degree FROM vocbook_users_words"." WHERE user_id='".$user_id."' AND word='".$word."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		
		$degree = 0;
		if($result != null && $result != ""){
			if($result[0]->degree == 0){
				$degree = 50;
			}else if($result[0]->degree == 100){
				$degree = 0;
			}else{
				$degree = 100;
			}
		
			$sql = "UPDATE vocbook_users_words SET degree = ".$degree
					.",create_date = '".date('Y-m-d H:i:s',time())."'"
					." WHERE user_id = '".$user_id."' AND word = '".$word."'";
			$this->db->query($sql);
		}else{
			$degree = 50;
			$sql = "SELECT voc_id,alphabet,anti_alphabet,cat_id FROM vocbook_words "
					."WHERE voc_id = (SELECT MIN(voc_id) FROM vocbook_words WHERE word = '"
					.$word."' AND order_id = 1)";
			$query = $this->db->query($sql);
			$result = $query->result();
			
			$sql = "INSERT INTO vocbook_users_words(user_id,voc_id,degree,notes
					,is_additional,create_date,alphabet,anti_alphabet,cat_id,word
					,exercise_date) VALUES('".$user_id."',".$result[0]->voc_id.",".$degree.",null,0,'".date('Y-m-d H:i:s',time())."','".$result[0]->alphabet
					."','".$result[0]->anti_alphabet."',".$result[0]->cat_id.",'"
					.$word."',null)";
			$query = $this->db->query($sql);
		}
		
		if ($degree == 0){
			return -1;
		}else if($degree == 100){
			return 1;
		}else{
			return 0;
		}
	}
	
	function update_degree_by_exer($user_id,$word,$score) {
		$sql = "SELECT degree FROM vocbook_users_words"." WHERE user_id='".$user_id."' AND word='".$word."'";
		$query = $this->db->query($sql);
		$result = $query->result();
		
		if($result != null && $result != ""){
			$sql = "UPDATE vocbook_users_words SET degree = ".$score
					.",exercise_date = '".date('Y-m-d H:i:s',time())."'"
					." WHERE user_id = '".$user_id."' AND word = '".$word."'";
			$this->db->query($sql);
		}else{
			$sql = "SELECT voc_id,alphabet,anti_alphabet,cat_id FROM vocbook_words "
					."WHERE voc_id = (SELECT voc_id FROM vocbook_words WHERE word = '"
					.$word."' AND order_id = 1)";
			$query = $this->db->query($sql);
			$result = $query->result();
			
			$sql = "INSERT INTO vocbook_users_words(user_id,voc_id,degree,notes
					,is_additional,create_date,alphabet,anti_alphabet,cat_id,word
					,exercise_date) VALUES('".$user_id."',".$result[0]->voc_id.","
					.$score.",null,0,'".date('Y-m-d H:i:s',time())."','"
					.$result[0]->alphabet."','".$result[0]->anti_alphabet
					."',".$result[0]->cat_id.",'".$word."','".date('Y-m-d H:i:s',time())."')";
			$query = $this->db->query($sql);
		}
	}
	
	function get_user_notes($user_id,$from,$count) {
		$sql = "SELECT word,notes,create_date FROM vocbook_users_words WHERE user_id = '"
				.$user_id
				."' AND notes IS NOT NULL and notes <> '' ORDER BY create_date desc LIMIT "
				.$from.",".$count;
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result;
	}
	
	function get_user_notes_count($user_id) {
		$sql = "SELECT COUNT(*) count FROM vocbook_users_words WHERE user_id = '"
				.$user_id."' AND notes IS NOT NULL and notes <> ''";
		$query = $this->db->query($sql);
		$result = $query->result();
		return $result[0]->count;
	}
	
	//获得用户上一次所选级别
	function get_user_band($user_id){
		$sql = "SELECT band FROM vocbook_users_band WHERE user_id = '".$user_id."'";
		$result = $this->db->query($sql)->result();
	
		if($result == null || $result == ""){
			$result = -1;
		}else{
			$result = $result[0]->band;
		}
	
		return $result;
	}
	
	//获得不同程度个数
	function get_count_diff_degree($user_id,$model){
		$band = $this->get_user_band($user_id);
		$band_str = $this->get_band_str($band);
			
		$sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w ".
				"WHERE u.user_id = '".$user_id."' AND u.degree = 100 AND u.word = w.word ".
				"AND w.".$band_str."=1";
		$query = $this->db->query($sql);
		$data["high"] = $query->num_rows();

		$sql = "SELECT DISTINCT u.word FROM vocbook_users_words u,vocbook_words w ".
				"WHERE u.user_id = '".$user_id."' AND u.degree = 50 AND u.word = w.word ".
				"AND w.".$band_str."=1";
		$query = $this->db->query($sql);
		$data["middle"] = $query->num_rows();
		
		$sql = "SELECT DISTINCT word FROM vocbook_words WHERE ".$band_str."=1";
		$query = $this->db->query($sql);
		$result = $query->num_rows();
		$data["low"] = $result - $data["high"] - $data["middle"];
		
		
		return $data;
	}
}

