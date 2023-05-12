package com.example.androidfinalprojectcmsc436

import android.content.Context
import android.widget.Toast

class Student {
    private var uid : String = ""
    private  var username :  String = ""
    private var password : String = ""
    private var dob :  String = ""
    private var firstname : String = ""
    private var middlename : String = ""
    private var lastname : String = ""
    private var registeredCourses : ArrayList<String>
    private var courseAssignments : HashMap<String, ArrayList<Assignment>>

    //May want to make a default construtor as will so that students can be added to the database without needing to be signed in
    constructor(uid: String,
                username: String,
                password: String,
                dob: String,
                firstname: String,
                middlename: String,
                lastname: String
        ) {
        this.uid = uid
        this.username = username
        this.password = password
        this.dob = dob
        this.firstname = firstname
        this.middlename = middlename
        this.lastname = lastname
        this.registeredCourses = ArrayList<String>()
        this.courseAssignments = HashMap<String, ArrayList<Assignment>>()
    }

    /* Overloading constructor*/
    constructor(
    ) : this("","","","","","","") {

    }
    private fun copy (uid: String = getUid(),
                      username: String = getUsername(),
                      password: String = getPassword(),
                      dob: String = getDob(),
                      firstname: String = getFirstName(),
                      middlename: String = getMiddleName(),
                      lastname: String = getLastName()) :  Student {

        return Student(uid,
            username,
            password,
            dob,
            firstname,
            middlename,
            lastname)

    }

    fun getUid() : String {
        return this.uid
    }

    fun getUsername() : String {
        return this.username
    }

    fun getDob() : String {
        return this.dob
    }

    fun getFirstName() : String {
        return this.firstname
    }

    fun getMiddleName() : String {
        return this.middlename
    }

    fun getLastName() : String {
        return this.lastname
    }
    fun getPassword() : String {
        return this.password
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun setUsername(username : String) {
        this.username = username
    }

    fun setDob(dob : String) {
        this.dob = dob
    }

    fun setFirstName(firstname: String)  {
        this.firstname =  firstname
    }

    fun setMiddleName(middlename: String)  {
        this.middlename = middlename
    }

    fun setLastName(lastname: String) {
        this.lastname = lastname
    }

    fun setPassword(password: String) {
        this.password = password
    }
    fun getRegisteredCourses() : ArrayList<String> {
        return registeredCourses
    }

    fun setRegisteredCourses(registeredCourses : ArrayList<String>) {
        this.registeredCourses = registeredCourses
    }

    fun getCourseAssignments() : HashMap<String, ArrayList<Assignment>> {
        return courseAssignments
    }

    fun setCourseAssignments(course : String, assignment: Assignment) : Unit {
        if (!registeredCourses.contains(course)) {
            return
        } else if (!courseAssignments.containsKey(course)) {
            val assignments : ArrayList<Assignment> = ArrayList<Assignment>()
            assignments.add(assignment)
            courseAssignments[course] = assignments
        } else {
            var assignments : java.util.ArrayList<Assignment>? = courseAssignments[course]
            assignments!!.add(assignment)
            courseAssignments[course] = assignments!!
        }
    }

    fun getStudentObjectAsURLParams(): String {
        var params = ""
        params += "firstname=${getFirstName()}"
        params += "&middlename=${getMiddleName()}"
        params += "&lastname=${getLastName()}"
        params += "&dob=${getDob()}"
        params += "&uid=${getUid()}"
        params += "&username=${getUsername()}"
        params += "&password=${getPassword()}"
        return params
    }
    fun addStudentToDatabase() : Boolean {
        var b : Backend = Backend()
        return b.post_signup_student(this.copy())

    }
    fun calculateGrade(course: String, context: Context) : Double {
        var grade = 0.0
        if ( !courseAssignments.containsKey(course) ) {
            val toast = Toast.makeText(context, "Course does not exist!", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            var assignments = courseAssignments[course]
            if ( assignments == null) {
                val toast = Toast.makeText(context, "Course does not exist!", Toast.LENGTH_SHORT)
                toast.show()
            }
            for ( a in assignments!!) {
                grade += a.getWeight()* a.getScore() * 100
            }
        }
        return grade
    }

    override fun toString(): String {
        return  "UID: $uid \n" +
                "Username: $username \n" +
                "Date Of Birth: $dob \n" +
                "First Name: $firstname \n" +
                "Middle Name: $middlename \n" +
                "Last Name: $lastname \n"

    }

}