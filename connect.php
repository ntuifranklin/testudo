<?php

/* error_reporting(E_ALL); */
/*	
MYSQL_PASSWORD="AV7y8JFAnSrkyzy"
MYSQL_USER="fnkokamn_dbadmin"
MYSQL_HOST="localhost"
MYSQL_DB="fnkokamn_terpdb"
*/
$servername = "localhost" ;
$username = "fnkokamn_dbadmin";
$password = "AV7y8JFAnSrkyzy"; 
$database = "fnkokamn_terpdb" ;

/*
echo "<br/> server name = ". $servername ;
echo "<br/> username = ". $username ;
echo "<br/> password = ". $password ; 
echo "<br/> database = ". $database ;
*/

$conn = new mysqli($servername, $username, $password, $database);
if ( $conn -> connect_error ) {
  die("Connection failed: ". $conn -> connect_error ) ;
} ;
echo "Connected to mysql successfully";
$a = 1024 ;
$z = 9999 ;
echo "<br/> Generating a random number between $a and $z <br/>that should be different every time ".rand($a,$z) ;
$sql = "SELECT * FROM STUDENT;";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  // output data of each row
  while($row = $result->fetch_assoc()) {
    echo "<br/> UID: " . $row["UID"]. "<br/> - FIRST Name: " . $row["FIRSTNAME"]. " <br/> - LAST NAME: " . $row["LASTNAME"]. "<br>";
  }
} else {
  echo "0 results";
}
$conn->close();
?>
