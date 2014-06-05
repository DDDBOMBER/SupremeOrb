<?php 
	

	function randomSalt($len = 8) {
		$chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-=_+';
		$l = strlen($chars) - 1;
		$str = '';
		for ($i = 0; $i &lt; $len; ++$i) {
			$str .= $chars[rand(0, $l];
	 	}
		return $str;
	}
	
 ?>