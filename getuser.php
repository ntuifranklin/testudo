<?php


/*	
MYSQL_PASSWORD="AV7y8JFAnSrkyzy"
MYSQL_USER="fnkokamn_dbadmin"
MYSQL_HOST="localhost"
MYSQL_DB="fnkokamn_terpdb"
*/

error_reporting(E_ERROR);
//header("Content-Type: application/json");
$servername = "localhost" ;
$dbusername = "fnkokamn_dbadmin";
$dbpassword = "AV7y8JFAnSrkyzy"; 
$database = "fnkokamn_terpdb" ;



$conn = new mysqli($servername, $dbusername, $dbpassword, $database);

if ( $conn -> connect_error ) {
  die(json_encode("Connection failed: ". $conn -> connect_error)  ) ;
} ;
//echo "Connected to mysql successfully";
$a = 1024 ;
$z = 9999 ;
//echo "<br/> Generating a random number between $a and $z <br/>that should be different every time ".rand($a,$z) ;
$stmt = $conn->prepare("SELECT UID, USERNAME, PASSWORD FROM STUDENT WHERE PASSWORD = ? AND (USERNAME=? OR UID = ?);");

$studentpassword = "" ;
$studentusername = "" ;
$studentuid = "";

if(array_key_exists("uid", $_GET)){
    $studentuid = $_GET['uid'];
} ;

if(array_key_exists("login", $_GET)){
    $studentusername = $_GET['login'];
} ;

if(array_key_exists("password", $_GET)){
    $studentuid = $_GET['uid'];
}
$stmt->bind_param("sss", $studentpassword, $studentusername, $studentuid);
$result = $stmt -> execute();
echo json_encode("{'password':$studentpassword, 'login':$studentusername, 'uid':$studentuid}");

if ($result->num_rows > 0) {
  // output data of each row
  $return_rows = [] ;
  while($row = $result->fetch_assoc()) {
    $return_rows.push($row);
  }
  echo json_encode($return_rows);
  
} else {
  echo json_encode("{'0 results'}");
}
$conn->close();


?>
