<?php

$servername = "localhost" ,
$username = "fnkokamn_dbadmin",
$password = "AV7y8JFAnSrkyzy", 
$database = "fnkokamn_terpdb" ,

function connectdb(
    $servername = "localhost" ,
    $username = "fnkokamn_dbadmin",
    $password = "AV7y8JFAnSrkyzy", 
    $database = "fnkokamn_terpdb") {

    $conn = new mysqli($servername, $username, $password, $database);
    if ( $conn -> connect_error ) {
        die("Connection failed: ". $conn -> connect_error ) ;
        return null ;
    } ;
    return $conn ;

}

function get_student_infos($username='',$password='') {
    $conn = connectdb();
    $stmt = $conn->prepare("SELECT * FROM STUDENT WHERE USERNAME=? AND PASSWORD=? ;");
    $stmt->bind_param("ss", $username, $password);
    $result = $stmt -> execute();
    return json_encode($result) ;
    $conn -> close();
}

function get_all_students() {
    
    $conn = connectdb();
    $sql = "SELECT * FROM STUDENT;";
    $result = $conn->query($sql);
    return json_encode($result) ;
    $conn -> close();

} ;





?>