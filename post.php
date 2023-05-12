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
    case 'signup_student':{
        
        $uid = "" ;
        $username = "" ;
        $password = "" ;
        $dob = "";
        $firstname = "";
        $middlename = "";
        $lastname = "";
        
        if(array_key_exists("uid", $_POST)){
            $uid = $_POST['uid'];
        } ;

        if(array_key_exists("username", $_POST)){
            $username= $_POST['username'];
        } ;
        if(array_key_exists("password", $_POST)){
            $password = $_POST['password'];
        } ;
        
        if(array_key_exists("dob", $_POST)){
            $dob= $_POST['dob'];
        } ;

        if(array_key_exists("firstname", $_POST)){
            $firstname= $_POST['firstname'];
        } ;

        if(array_key_exists("middlename", $_POST)){
            $middlename= $_POST['middlename'];
        } ;
        if(array_key_exists("lastname", $_POST)){
            $lastname= $_POST['lastname'];
        } ;

        $result = register_student(
            $uid,$username, 
            $password, $dob, 
            $firstname, $middlename,
            $lastname)  ;
        echo $result ;

    };
    break;
    case 'submit_assignment': {
        /*
         submit_assignment($assignmenttitle='', $weight=0.0, $courseid='', $studentuid='', $earnedgrade = 0.0)
        */
            $assigntmentTitle = "" ; 
            $weight = 0.0;
            $courseid = "" ;
            $studentuid = "";
            $earnedgrade= 0.0;

            if (array_key_exists("assignmentTitle", $_POST))
                $assignmentTitle = $_POST["assignmentTitle"];
            if (array_key_exists("weight", $_POST))
                $weight = $_POST["weight"];
                
            if (array_key_exists("courseid", $_POST))
                $courseid = $_POST["courseid"];
                
            if (array_key_exists("studentuid", $_POST))
                $studentuid = $_POST["studentuid"];
                
            if (array_key_exists("earnedgrade", $_POST))
                $earnedgrade = $_POST["earnedgrade"];
            $r = submit_assignment($assignmentTitle, $weight, $courseid, $studentuid, $earnedgrade) ;

            echo json_encode($r);

    } ; break ;
    default :
        echo json_encode($unathorized);
        break;
    ;
} ;


?>