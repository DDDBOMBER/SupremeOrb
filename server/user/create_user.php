<?php 
	
	require_once "../lib/meekrodb.2.2.class.php";

	$username = $_POST["username"];
	$password = $_POST["password"];

	if($username && $password){
		$salt = randomSalt();
		$hashPassword = hashPassword($password, $salt);
		$userKey = randomSalt(64);

		DB::insert("UserLogon", array(
			"UserName" => $username,
			"UserSalt" => $salt,
			"UserPassword" => $hashPassword,
			"UserKey" => $userKey
		));
	}

	function randomSalt($len = 8) {
		$chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()-=_+';
		$l = strlen($chars) - 1;
		$str = '';
		for ($i = 0; $i < $len; $i++) {
			$str .= $chars[rand(0, $l)];
	 	}
		return $str;
	}

	function hashPassword($password, $salt){
		$hash = "";
		for ($i=0; $i < 2206; $i++) { 
			$hash = sha1($hash.$password.$salt);
		}
		return $hash;
	}
	
 ?>