<?php 

	require_once "items.php";

	function get_item() {

		$item = new Item();

		$a = get_color_list();

		$seed = rand(1, 20);

		$second_seed;

		if($seed == 1){

			// Ethereal
			$item->type = "Ethereal";
			$colors = 2;

			$second_seed = rand(1, 125);

			if($second_seed == 1){
				$colors = 5;
			}else if($second_seed < 6){
				$colors = 4;
			}else if($second_seed < 31){
				$colors = 3;
			}

			$cols = array();
			for ($i=0; $i < $colors; $i++) { 
				$second_seed = rand(1,9);
				$third_seed = rand(0,8);

				if($second_seed < 4){
					// Common
					$cols[] = $a[$third_seed];
				}else if($second_seed < 8){
					// Mid
					$cols[] = $a[$third_seed+9];
				}else if($second_seed < 10){
					// Rare
					$cols[] = $a[$third_seed+18];
				}

				$item->metadata = $cols;
			}

		}else if($seed == 2){

			// Black Hole
			$item->type = "BlackHole";

		}else{

			//Standard
			$item->type = "Standard";

			$second_seed = rand(1,9);
			$third_seed = rand(0,8);

			if($second_seed < 5){
				// Common
				$item->metadata = $a[$third_seed];
			}else if($second_seed < 8){
				// Mid
				$item->metadata = $a[$third_seed+9];
			}else if($second_seed < 10){
				// Rare
				$item->metadata = $a[$third_seed+18];
			}

		}

		return $item;
	}

	echo "<pre>";
		var_dump(get_item());
	echo "</pre>"
 ?>