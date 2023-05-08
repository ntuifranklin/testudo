package com.example.androidfinalprojectcmsc436
import android.util.Log

class PostRegisterCourseThread : Thread {
    private lateinit var registerCourseTaskActivity : RegisterClassesActivity
    private var result : String = "not set yet"
    private lateinit var courses : ArrayList<Course>
    private lateinit var coursesAsStrings : Array<String>
    private var studentUid : String = ""
    private lateinit var courseIds : ArrayList<Course>
    constructor( activity : RegisterClassesActivity, studentId:String, courses: ArrayList<Course>) {
        this.registerCourseTaskActivity = activity
        studentUid = studentId
        courseIds = courses


    }

    override fun run() {
        super.run()
        //Log.w( MainActivity.MA, "Inside run" )
        // connect to server, read data, assign it to result


        try {

            var backend : Backend = Backend()
            courses = backend.get_all_courses_from_database()
            coursesAsStrings = Gradebook().getCourses()

            var showRegisteredcoursesListAlertBox: ShowRegisteredcoursesListAlertBox = ShowRegisteredcoursesListAlertBox()
            StudentDashboardActivity.ALL_COURSES = courses
            StudentDashboardActivity.ALL_COURSE_CODES = coursesAsStrings
            registerCourseTaskActivity.runOnUiThread(showRegisteredcoursesListAlertBox);

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;

            registerCourseTaskActivity.finish() ;
        }
    }

    inner class ShowRegisteredcoursesListAlertBox : Runnable {
        override fun run() {
            registerCourseTaskActivity.showCourseRegistersuccess()
        }

    }


}