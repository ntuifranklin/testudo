package com.example.androidfinalprojectcmsc436

import android.util.Log
import android.widget.EditText
import java.io.InputStream
import java.net.URL
import java.util.*
import org.json.JSONException

class AccessWebThreadTask : Thread {
    private lateinit var taskActivity : MainActivity
    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    private var result : String = "not set yet"
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
            // create URL

            loginUrl = MainActivity.SERVER_BASE_URL

            val username : String = usernameET.text.toString()
            val password : String = passwordET.text.toString()
            loginUrl = "$loginUrl?action=user&username=$username"
            // now add password
            loginUrl = "$loginUrl&password=$password"
            Log.w(MainActivity.MA, "Accessing login URL : " + loginUrl)

            var urlObject: URL = URL(loginUrl)

            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            result = ""
            while (scan.hasNext())
            {
                result += scan.nextLine()
                Log.w(MainActivity.MA, result)
            }
            var showDashboardGui: ShowDashboardGui = ShowDashboardGui()
            taskActivity.runOnUiThread(showDashboardGui);

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;
            Log.w(MainActivity.MA, "Exception: " + e) ;
            //taskActivity.finish() ;
        }
    }

    inner class ShowDashboardGui : Runnable {
        override fun run() {
            taskActivity.showStudentDashboard( result )
        }

    }


}