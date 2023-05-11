<?php

$servername = "localhost" ;
$username = "fnkokamn_dbadmin";
$password = "AV7y8JFAnSrkyzy"; 
$database = "fnkokamn_terpdb";

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
} ;

function get_student_infos($username='',$password='') {
    $conn = connectdb();
    $stmt = $conn->prepare("SELECT * FROM STUDENT WHERE USERNAME=? AND PASSWORD=? ;");
    $stmt->bind_param("ss", $username, $password);
    $stmt -> execute();
    $result = $stmt -> get_result() ;
    $r = [] ;
    while($row = $result->fetch_assoc()) {
        array_push($r, $row);
    } ;
    $conn -> close();
    return json_encode($r, JSON_PRETTY_PRINT) ;
    
} ;

function get_student_registered_courses($studentuid='') {
    $conn = connectdb();
    $stmt = $conn->prepare("SELECT * FROM ENROLLEDCOURSE WHERE STUDENTUID=?;");
    $stmt->bind_param("s", $studentuid);
    $stmt -> execute();
    $result = $stmt -> get_result() ;
    $r = [] ;
    while($row = $result->fetch_assoc()) {
        array_push($r, $row);
    } ;
    $conn -> close();
    return json_encode($r, JSON_PRETTY_PRINT) ;
    
} ;

function register_course($studentuid='',$courseid='') {
    $r = "";
    $conn = connectdb();
    $stmt = $conn->prepare("INSERT INTO ENROLLEDCOURSE VALUES (?, ?, ?, ?) ;");
    $enrollid = uniqid("$studentuid"."_"."$courseid", true);
    $now = new DateTime('now', new DateTimeZone('America/New_York'));
    $stmt->bind_param("ssss", $enrollid,$courseid,$studentuid, $now->format('Y-m-d H:i:s'));
    try{

        
        if ($stmt -> execute() ) {
            $conn -> close();
            $r = '{"COURSEID":"$courseid", "STATUS":"success","ERROR":""}' ;
        } 
    } catch(Exception $e) {
        
        $r = '{"COURSEID":"$courseid", "STATUS":"FAILURE","ERROR":"$e"}' ;

    }
    
    return json_encode($r, JSON_PRETTY_PRINT) ;
    
} ;

/*

CREATE TABLE IF NOT EXISTS `STUDENT` (
  `UID` varchar(100) NOT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  `DOB` date NOT NULL,
  `FIRSTNAME` varchar(100) DEFAULT NULL,
  `MIDDLENAME` varchar(100) DEFAULT NULL,
  `LASTNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



*/

function register_student(
                        $uid='',$username='', 
                        $password='', $dob='', 
                        $firstname='', $middlename='',
                        $lastname='') {
    $r = "";
    $conn = connectdb();
    $stmt = $conn->prepare("INSERT INTO STUDENT VALUES (?, ?, ?, ?, ?, ?, ?) ;");
    
    
    $stmt->bind_param(
        "sssssss", 
        $uid,
        $username,
        $password,
        $dob,
        $firstname,
        $middlename,
        $lastname
    );
    try{
        if ($stmt -> execute() ) {
            $conn -> close();
            $r = '[{"UID":"'.$uid.'", "STATUS":"success","ERROR":""}]' ;
        } 
    } catch(Exception $e) {
        $r = '[{"UID":"'.$uid.'", "STATUS":"failure","ERROR":"'.$e.'"}]' ;
    } ;
    
    return json_encode($r, JSON_PRETTY_PRINT) ;
} ;

function get_all_students() {
    
    $conn = connectdb();
    $sql = "SELECT * FROM STUDENT;";
    $result = $conn->query($sql);

    $conn -> close();
    $r = [] ;
    while($row = $result->fetch_assoc()) {
        array_push($r, $row);
    } ;
    return json_encode($r, JSON_PRETTY_PRINT) ;
    

} ;

function get_all_courses() {
    
    $conn = connectdb();
    $sql = "SELECT * FROM COURSE;";
    $result = $conn->query($sql);

    $conn -> close();
    $r = [] ;
    while($row = $result->fetch_assoc()) {
        array_push($r, $row);
    } ;
    return json_encode($r, JSON_PRETTY_PRINT) ;
    

} ;


/**
 * You can only query a course by course id or corse title
 */
function get_course_infos($courseid='',$coursetitle='') {
    /*
    * Return if course id is empty
    */
    if (strlen(strval($courseid)) == 0 )
        return json_decode("{'result':'Requesting a course without a course id'}", JSON_PRETTY_PRINT) ;

    $conn = connectdb();
    $partial_sql = "SELECT * FROM COURSE WHERE CID=?";
    
    // The course title was specified
    if (strlen(strval($coursetitle)) > 0 ) {
        $partial_sql .= " AND COURSETITLE=?; ";
        $stmt->bind_param("ss", $courseid, $coursetitle);
    } else {
        $stmt = $conn->prepare($partial_sql);
        $stmt->bind_param("s", $courseid);
    }
    
    $stmt -> execute();
    $result = $stmt -> get_result() ;
    $r = [] ;
    while($row = $result->fetch_assoc()) {
        array_push($r, $row);
    } ;
    $conn -> close();
    return json_encode($r, JSON_PRETTY_PRINT) ;
    
} ;





?>