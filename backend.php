<?php

require_once("./library.php") ;

if(!array_key_exists("action", $_GET)) {
    die("{'result':'Unauthorized'}");
} ;
$action = $_GET['action'];
$action = strtolower($action);
switch ($action) {
    case 'user':{
        
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
    case 'users':{
        echo get_all_students();
    } ; break ;
    case 'courses':{
        echo get_all_courses();
    } ; break ;

    case 'studentcourses':{
        
        $studentuid = "" ;
        
        if(array_key_exists("studentuid", $_GET)){
            $studentuid = $_GET['studentuid'];
        } ;
        
        $result = get_student_registered_courses($studentuid);
        echo $result;
    } ; break ;

    case 'course':{
        
        $courseid = "" ;
        $coursetitle = "" ;
        
        if(array_key_exists("courseid", $_GET)){
            $courseid = $_GET['courseid'];
        } ;

        if(array_key_exists("coursetitle", $_GET)){
            $coursetitle = $_GET['coursetitle'];
        } ;
        echo get_course_infos($courseid,$coursetitle);
        
    } ; break ;
    case 'putuser':
        echo "{'result':'TODO:PUTUSER'}";
        break;

    case 'studentgrades' : {
        // function get_grades($studentuid='')
        $studentuid = "";
        if (array_key_exists("studentuid", $_GET))
            $studentuid = $_GET['studentuid'];
        $r = get_grades($studentuid);
        echo json_encode($r,JSON_PRETTY_PRINT) ;
    } ; break ;
    default :
        echo "{'result':'Unknown operation'}";
    ;
} ;


?>