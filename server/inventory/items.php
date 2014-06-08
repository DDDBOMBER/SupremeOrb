<?php 
	
	class Color {
		public $col, $col_light;

		function __construct($col, $col_light){
			$this->col = $col;
			$this->col_light = $col_light;
		}
	}

	class Item {
		public $type;
		public $metadata;
	}

	function get_color_list(){
		$a = array(
			new Color("4929356", "11705019"),
			new Color("4013373", "12369084"),
			new Color("5262400", "9194045"),
			new Color("5521469", "14269817"),
			new Color("7417620", "12374132"),
			new Color("6839950", "10772845"),
			new Color("4340574", "9453381"),
			new Color("4866368", "1477251"),
			new Color("8426345", "8426345"),
			new Color("3414926", "6839950"),
			new Color("8564358", "16777215"),
			new Color("10180517", "16777215"),
			new Color("13794604", "14341214"),
			new Color("9496319", "16777215"),
			new Color("9111552", "12600356"),
			new Color("13795158", "16777215"),
			new Color("16481371", "16633281"),
			new Color("13097277", "15332000"),
			new Color("6839950", "15520205"),
			new Color("10565988", "9334857"),
			new Color("5075243", "11974953"),
			new Color("2912926", "9186929"),
			new Color("317913", "15912806"),
			new Color("3043537", "7193290"),
			new Color("2650710", "13565854"),
			new Color("8009550", "13794604"),
			new Color("9585919", "15335620"),
		);

		return $a;
	}
 ?>