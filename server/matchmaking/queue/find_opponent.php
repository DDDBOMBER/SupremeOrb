<?php 
	define("SKILL_MAX_GAP", 100);

	// Find Players Within The Skill Gap, Find The Closest
	// Add To A List To Let Player Confirm Games

	$time_in_queue = 0; // time player has queued in seconds

	function get_skill_gap(){
		return SKILL_MAX_GAP + ($time_in_queue/3);
	}
 ?>