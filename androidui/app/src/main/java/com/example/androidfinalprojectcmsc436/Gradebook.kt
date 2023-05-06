package com.example.androidfinalprojectcmsc436

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class Gradebook {

    private val classes = arrayOf("CMSC131", "CMSC132", "CMSC216", "CMSC250", "CMSC330", "CMSC351", "CMSC412", "CMSC420", "CMSC430", "CMSC433", "CMSC436", "CMSC451", "CMSC456")

    constructor( ) {

    }

    fun getCourses( ) : Array<String> {
        return classes
    }

    fun setCourses( ) : Unit {

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