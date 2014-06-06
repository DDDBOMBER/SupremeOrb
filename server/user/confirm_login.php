<?php 
	
	require_once "../lib/meekrodb.2.2.class.php";

	$username = $_POST["username"];
	$password = $_POST["password"];

	$results = DB::query("SELECT * FROM UserLogon WHERE UserName=%s0", $username);
		if(count($results) == 0){
			echo "Failed";
		}else{
			foreach ($results as $row) {
				$id = $row["UserId"]; 
				$name = $row["UserName"];
				$salt = $row["UserSalt"]; 
				$DBpassword = $row["UserPassword"];
				$key = $row["UserKey"];

				if($DBpassword == hashPassword($password, $salt)){
					echo $row["UserId"]."\n";
					echo $row["UserName"]."\n";
					echo $row["UserKey"];
				}else{
					echo "Failed";
				}
			}
		}
	

	function hashPassword($password, $salt){
		$hash = "";
		for ($i=0; $i < 2206; $i++) { 
			$hash = sha1($hash.$password.$salt);
		}
		return $hash;
	}

 ?>