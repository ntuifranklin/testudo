package com.example.androidfinalprojectcmsc436

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class Gradebook {

    private val classes = arrayOf("CMSC131", "CMSC132", "CMSC216", "CMSC250", "CMSC330", "CMSC351", "CMSC412", "CMSC420", "CMSC430", "CMSC433", "CMSC436", "CMSC451", "CMSC456")

    constructor( ) {
        courses  = Backend().get_all_courses_from_database()
        courseIDs = Array<String>(courses.size, {i -> courses.get(i).courseid})
    }

    fun getCourses( ) : Array<String> {
        if (courseIDs == null ) {
            var c : ArrayList<Course> = getAllCourseObjects()
            courseIDs = Array<String>(c.size, {i -> c.get(i).courseid})
        }
        return courseIDs
    }

    fun getAllCourseObjects() : ArrayList<Course> {
        if ( courses == null )
            courses  = Backend().get_all_courses_from_database()
        return courses
    }

    fun setCourses( ) : Unit {

    }


    companion object {
        lateinit var courses : ArrayList<Course>
        lateinit var courseIDs : Array<String>
    }

    fun sendEmail(context : Context, subject : String, emailAddress : String, message : String) : Unit {
        var emailIntent : Intent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
        emailIntent.putExtra(Intent.EXTRA_TEXT, message)

        startActivity(context, Intent.createChooser(emailIntent, "Email your professor"), null)
    }



}