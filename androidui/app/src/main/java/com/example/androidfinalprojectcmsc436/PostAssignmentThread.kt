package com.example.androidfinalprojectcmsc436


import android.util.Log
import android.widget.EditText

class PostAssignmentThread : Thread {
    private lateinit var taskActivity : AddGradeActivity
    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    private var result : String = "not set yet"

    private var post_student_success = false
    private var assignmentTitle : String = ""
    private var weight : Double = 0.0
    private var studentuid : String = ""
    private var earnedgrade : Double = 0.0

    constructor( activity : AddGradeActivity, assignmentTitle:String, weight : Double, studentuid: String, earnedgrade : Double ) {
        this.taskActivity = activity
        this.assignmentTitle = assignmentTitle
        this.weight = weight
        this.studentuid = studentuid
        this.earnedgrade = earnedgrade

    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result

        var returnToPreviousActivity :ReturnToPreviousActivity = ReturnToPreviousActivity()
        var backend : Backend = Backend()
        var result : Boolean = false
        try {
            result = backend.post_course_assignment(assignmentTitle,weight,studentuid,earnedgrade)

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception Posting Student to database: " + e.message ) ;
            Log.w(MainActivity.MA, "Exception Posting Student to database: $e" ) ;

        }
        taskActivity.runOnUiThread(returnToPreviousActivity);
    }

    inner class ReturnToPreviousActivity : Runnable {
        override fun run() {
            // if result is false, say it

            // if result is true say it
            //taskActivity.showSuccessMessage(post_student_success)
        }

    }


}