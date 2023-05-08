package com.example.androidfinalprojectcmsc436
import android.util.Log

class PostRegisterCourseThread : Thread {
    private lateinit var registerCourseTaskActivity : RegisterClassesActivity
    private var result : String = "not set yet"
    private lateinit var courses : ArrayList<Course>
    private lateinit var coursesAsStrings : Array<String>
    private lateinit var student : Student
    private lateinit var courseIds : ArrayList<String>
    constructor( activity : RegisterClassesActivity, student:Student, courses: ArrayList<String>) {
        this.registerCourseTaskActivity = activity
        this.student = student
        courseIds = courses


    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result


        try {

            var backend : Backend = Backend()
            result = backend.post_registered_courses(student, courseIds)

            var showRegisteredCoursesListAlertBox: ShowRegisteredCoursesListAlertBox = ShowRegisteredCoursesListAlertBox()
            StudentDashboardActivity.ALL_COURSES = courses
            StudentDashboardActivity.ALL_COURSE_CODES = coursesAsStrings
            registerCourseTaskActivity.runOnUiThread(showRegisteredCoursesListAlertBox)


        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;

            registerCourseTaskActivity.finish() ;
        }
    }

    inner class ShowRegisteredCoursesListAlertBox : Runnable {
        override fun run() {
            registerCourseTaskActivity.showCourseRegistersuccess()
        }

    }


}