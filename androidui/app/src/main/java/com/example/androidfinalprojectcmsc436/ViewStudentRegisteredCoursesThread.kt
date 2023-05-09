package com.example.androidfinalprojectcmsc436

import android.util.Log

class ViewStudentRegisteredCoursesThread : Thread {
    private lateinit var studentDashboardTaskActivity : StudentDashboardActivity
    private var result : String = "not set yet"
    private lateinit var courses : ArrayList<Course>
    private lateinit var coursesAsStrings : Array<String>
    var loginUrl : String = ""
    constructor( activity : StudentDashboardActivity) {
        this.studentDashboardTaskActivity = activity

    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result


        try {

            var backend : Backend = Backend()
            courses = backend.get_all_courses_from_database()
            coursesAsStrings = Gradebook().getCourses()

            var showcoursesListUI: ShowcoursesListUI = ShowcoursesListUI()
            StudentDashboardActivity.ALL_COURSES = courses
            StudentDashboardActivity.ALL_COURSE_CODES = coursesAsStrings
            studentDashboardTaskActivity.runOnUiThread(showcoursesListUI);

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;

            studentDashboardTaskActivity.finish() ;
        }
    }

    inner class ShowcoursesListUI : Runnable {
        override fun run() {
            studentDashboardTaskActivity.goToRegisterClasses()

        }

    }


}