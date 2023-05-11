package com.example.androidfinalprojectcmsc436

import android.util.Log
import android.widget.EditText

class PostStudentThread : Thread {
    private lateinit var taskActivity : SignUpActivity
    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    private var result : String = "not set yet"
    private lateinit var student : Student
    private var post_student_success = false
    var loginUrl : String = ""
    constructor( activity : SignUpActivity, student : Student ) {
        this.taskActivity = activity
        this.student = student
        if ( student != null )
        Log.w(MainActivity.MA, "Student received in Post Signup thread : $student")

    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result

        var showSuccessFailure : ShowStudentSuccessOrFailure = ShowStudentSuccessOrFailure()

        try {
            post_student_success = student.addStudentToDatabase()

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception Posting Student to database: " + e.message ) ;
            Log.w(MainActivity.MA, "Exception Posting Student to database: $e" ) ;

        }
        taskActivity.runOnUiThread(showSuccessFailure);
    }

    inner class ShowStudentSuccessOrFailure : Runnable {
        override fun run() {
            taskActivity.showSuccessMessage(post_student_success)
        }

    }


}