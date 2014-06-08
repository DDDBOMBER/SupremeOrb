<?php 
	
	class Color {
		public $col, $col_light;

		function __construct($col, $col_light){
			$this->col = hexdec($col);
			$this->col_light = hexdec($col_light);
		}
	}

	class Item {
		public $type;
		public $metadata;
	}

	function get_color_list(){
		$a = array(
			new Color("8B0800", "C04424"),
			new Color("4D712B", "B6B929"),
			new Color("2E70D1", "6DC2CA"),

			new Color("D27D2C", "DAD45E"),
			new Color("90E6FF", "FFFFFF"),
			new Color("9244ff", "EA00C4"),

			new Color("FB7C5B", "FDCDC1"),
			new Color("C7D93D", "E9F2A0"),
			new Color("4B374C", "B29ABB"),
			
			new Color("3D3D3D", "606060"),
			new Color("287256", "CEFF9E"),
			new Color("7A374E", "D27D2C"),
			
			new Color("341B8E","685E8E"),
			new Color("504C40", "8C4A3D"),
			new Color("54403D", "D9BD79"),
			
			new Color("2C729E", "8C2E2E"),
			new Color("712F14", "BCD074"),
			new Color("685E8E", "A4616D"),
		);

		return $a;
	}
 ?>