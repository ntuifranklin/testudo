<?php

require_once("./library.php") ;

if(!array_key_exists("action", $_GET)) {
    die("{'result':'Unauthorized'}");
} ;
$action = $_GET['action'];
$action = strtolower($action);
switch ($action) {
    case 'getuser':{
        
        $studentpassword = "" ;
        $studentusername = "" ;
        
        if(array_key_exists("username", $_GET)){
            $studentusername = $_GET['username'];
        } ;

        if(array_key_exists("password", $_GET)){
            $studentpassword = $_GET['password'];
        } ;
        $result = get_student_infos($username=$studentusername,$password=$studentpassword) ;
        echo $result ;

    };
    break;
    case 'getusers':
        echo get_all_students();
        break;
    case 'putuser':
        echo "{'result':'TODO:PUTUSER'}";
        break;
    default :
        echo "{'result':'Unknown operation'}";
    ;
} ;


?>