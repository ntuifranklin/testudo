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
        student = Student()
    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result
        if (usernameET == null || passwordET == null )
            taskActivity.finish() ;
        var showDashboardGui: ShowDashboardGui = ShowDashboardGui()
        try {

            var username : String = usernameET.text.toString()
            var password : String = passwordET.text.toString()
            var backend : Backend = Backend()
            student = backend.get_one_student_from_database(username,password)
            student.setRegisteredCourses(backend.get_registered_courses(student.getUid()))






        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Logging In Student Exception: " + e.message )

        }
        taskActivity.runOnUiThread(showDashboardGui);

    }

    inner class ShowDashboardGui : Runnable {
        override fun run() {
            if (student == null || student.getUid().length < 1 )
                taskActivity.showErrorUserLoginToast(Exception("Wrong username or Password. Please Try again"))
            else
                taskActivity.showStudentDashboard( student )
        }

    }


}