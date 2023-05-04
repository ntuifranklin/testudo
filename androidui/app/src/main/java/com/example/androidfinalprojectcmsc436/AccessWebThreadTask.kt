package com.example.androidfinalprojectcmsc436

import android.util.Log
import android.widget.EditText
import org.json.JSONArray
import java.io.InputStream
import java.net.URL
import java.util.*
import org.json.JSONException
import org.json.JSONObject

class AccessWebThreadTask : Thread {
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
            // create URL

            loginUrl = MainActivity.SERVER_BASE_URL

            var username : String = usernameET.text.toString()
            var password : String = passwordET.text.toString()
            loginUrl = "$loginUrl?action=user&username=$username"
            // now add password
            loginUrl = "$loginUrl&password=$password"
            Log.w(MainActivity.MA, "Accessing login URL : " + loginUrl)

            var urlObject: URL = URL(loginUrl)

            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            result = ""
            /*
                        *
            CREATE TABLE IF NOT EXISTS STUDENT  (
             UID VARCHAR(100) NOT NULL PRIMARY KEY,
             USERNAME VARCHAR(100),
             PASSWORD VARCHAR(100),
             DOB DATE NOT NULL,
             FIRSTNAME VARCHAR(100),
             MIDDLENAME VARCHAR(100),
             LASTNAME VARCHAR(100)
            ) ;
            * */
            while (scan.hasNext())
            {
                result += scan.nextLine()
            }

            var d : JSONArray = JSONArray(result)

            var jsa : JSONObject = d.optJSONObject(0)
            var uid : String = jsa.getString("UID")
            username = jsa.getString("USERNAME")
            password = jsa.getString("PASSWORD")
            var dob : String = jsa.getString("DOB")
            var firstname : String = jsa.getString("FIRSTNAME")
            var middlename : String = jsa.getString("MIDDLENAME")
            var lastname : String = jsa.getString("LASTNAME")
            student =
                Student(uid,
                    username,
                    password,
                    dob,
                    firstname,
                    middlename,
                    lastname
                )

            Log.w(MainActivity.MA, student.toString())
            var showDashboardGui: ShowDashboardGui = ShowDashboardGui()
            taskActivity.runOnUiThread(showDashboardGui);

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message ) ;
            Log.w(MainActivity.MA,"Current Result : " +result)
            taskActivity.finish() ;
        }
    }

    inner class ShowDashboardGui : Runnable {
        override fun run() {
            taskActivity.showStudentDashboard( student )
        }

    }


}