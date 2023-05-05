package com.example.androidfinalprojectcmsc436

import android.util.Log
import android.widget.EditText

class LogStudentByWebThreadTask : Thread {
    private lateinit var taskActivity : MainActivity
    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    private var result : String = "not set yet"
    private lateinit var student : Student
    var loginUrl : String = ""
    constructor( activity : MainActivity, uET : EditText?, pET: EditText? ) {
        this.taskActivity = activity
        if (uET != null && pET != null ) {
            usernameET = uET
            passwordET = pET
        } else {
            taskActivity.finish()
        }
    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result
        if (usernameET == null || passwordET == null )
            taskActivity.finish() ;

        try {

            var username : String = usernameET.text.toString()
            var password : String = passwordET.text.toString()
            var backend : Backend = Backend()
            student = backend.get_one_student_from_database(username,password)

            Log.w(MainActivity.MA, student.toString())
            var showDashboardGui: ShowDashboardGui = ShowDashboardGui()
            taskActivity.runOnUiThread(showDashboardGui);

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;

            taskActivity.finish() ;
        }
    }

    inner class ShowDashboardGui : Runnable {
        override fun run() {
            taskActivity.showStudentDashboard( student )
        }

    }


}