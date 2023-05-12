package com.example.androidfinalprojectcmsc436

import android.util.Log

class ViewStudentRegisteredCoursesThread : Thread {
    private lateinit var studentDashboardActivity : StudentDashboardActivity
    private var result : String = "not set yet"
    private lateinit var courses : ArrayList<Course>
    private lateinit var coursesAsStrings : ArrayList<String>
    var studentid : String = ""
    var loginUrl : String = ""
    constructor( activity : StudentDashboardActivity, studentid: String) {
        this.studentDashboardActivity = activity
        this.studentid = studentid

    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result


        try {

            var backend : Backend = Backend()
            courses = backend.get_all_courses_from_database()
            coursesAsStrings = backend.get_registered_courses(studentid)
            var showRegcourses : ShowRegisteredCoursesListUI = ShowRegisteredCoursesListUI()
            studentDashboardActivity.runOnUiThread(showRegcourses)

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;

            //studentDashboardActivity.finish() ;
        }
    }

    inner class ShowRegisteredCoursesListUI : Runnable {
        override fun run() {
            //studentDashboardActivity.viewRegisteredCoursesGUI(coursesAsStrings)
            MainActivity.LOGGED_IN_STUDENT.setRegisteredCourses(coursesAsStrings)
            StudentDashboardActivity.LOGGED_IN_STUDENT.setRegisteredCourses(coursesAsStrings)
            studentDashboardActivity.goToRegisteredClasses()
        }

    }


}