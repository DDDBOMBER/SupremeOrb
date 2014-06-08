<?php 
	require_once "../lib/meekrodb.2.2.class.php";

	$id = $_GET["id"];
	$results = DB::query("SELECT * FROM UserItems WHERE UserID=%i", $id);
	$string = json_encode($results);
	echo $string;
?>	