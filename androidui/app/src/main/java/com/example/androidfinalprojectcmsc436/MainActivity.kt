package com.example.androidfinalprojectcmsc436

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.text.isDigitsOnly
import org.json.JSONException
import java.io.InputStream
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    lateinit var loginButtonView : Button
    lateinit var loginTask : AccessWebThreadTask
    var result: String = ""
    var loginUrl : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usernameET = findViewById(R.id.login_name)
        passwordET = findViewById(R.id.login_password)
        loginButtonView = findViewById(R.id.login_button)
        loginButtonView.setOnClickListener(ButtonHandler())
    }



    fun goBack( v : View) {
        // go back
        finish( )
        overridePendingTransition( R.anim.fade_in_and_scale, 0 )
    }

    fun checkUserLogin()  {
        //sending to thread task the username and password text

        loginTask = AccessWebThreadTask( this, usernameET, passwordET )
        loginTask.start()


    }

    fun showStudentDashboard(student: String) {
        Log.w(MA, "Student is logged in" + student);
    }

    inner class ButtonHandler : View.OnClickListener {
        override fun onClick(v: View?) {
           if ( v == null )
               return
            checkUserLogin()
        }

    }

    companion object {
        //const val LOGIN_BASE_URL: String = "https://s56.cmsc436-2301.cs.umd.edu/"
        const val SERVER_BASE_URL: String = "http://localhost:8298/cmsc436grproj/backend.php"
        const val MA : String =  "FinalProjectMainActivity"
    }

}