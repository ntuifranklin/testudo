<?php
error_reporting(E_ALL);
require_once("./library.php") ;
$unathorized = "{'result':'Unknown operation'}";
if(!array_key_exists("name", $_POST)) {
    die(json_encode($unathorized));
} ;
$action = $_POST['name'];
$action = strtolower($action);
switch ($action) {
    case 'register_course':{
        
        $studentuid = "" ;
        $courseid = "" ;
        
        if(array_key_exists("studentuid", $_POST)){
            $studentuid = $_POST['studentuid'];
        } ;

        if(array_key_exists("courseid", $_POST)){
            $courseid = $_POST['courseid'];
        } ;
        $result = register_course($studentuid,$courseid)  ;
        echo $result ;

    };
    break;
    default :
        echo json_encode($unathorized);
        break;
    ;
} ;


?>