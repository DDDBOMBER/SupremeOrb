<?php 
	define("k_factor", 12);

	$a_id = $_POST["a_id"];
	$b_id = $_POST["b_id"];

	$winner_id = $_POST["winner_id"];

	if($winner_id != $a_id && $winner_id != $b_id){
		// ERROR - possible spoof of match
	}else{
		$a_ranking = 1400;// Get First Player Ranking
		$b_ranking = 1400;// Get Second Player Ranking

		$match_id = $_POST["match_id"];
		// Check Match ID is correct and has not been reported already

		$a_expected = 1/(1+10($b_ranking-$a_ranking)/400);
		$b_expected = 1/(1+10($a_ranking-$b_ranking)/400);

		if($winner_id == $a_id){
			$a_new = $a_ranking + $k_factor * (1-$a_expected);
			$b_new = $b_ranking - $k_factor * ($b_expected);
		}else if($winner_id == $a_id){
			$a_new = $a_ranking - $k_factor * ($a_expected);
			$b_new = $b_ranking + $k_factor * (1-$b_expected);
		}
	}
 ?>