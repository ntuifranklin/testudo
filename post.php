<?php

require_once("./library.php") ;
$unathorized = "{'result':'Unknown operation'}";
if(!array_key_exists("action", $_POST)) {
    die(json_encode($unathorized));
} ;
$action = $_POST['action'];
$action = strtolower($action);
switch ($action) {
    case 'register_course':{
        
        $studentuid = "" ;
        $courseid = "" ;
        
        if(array_key_exists("studentuid", $_POST)){
            $studentuid = $_POST['studentuid'];
        } ;

        if(array_key_exists("courseid", $_POST)){
            $$courseid = $_GET['courseid'];
        } ;
        $result = register_course($studentid,$courseid)  ;
        echo $result ;

    };
    break;
    default :
        echo json_encode($unathorized);
    ;
} ;


?>