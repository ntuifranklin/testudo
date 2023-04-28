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
    var result: String = ""
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

        try {
            // create URL

            var url: String = LOGIN_BASE_URL
            val uid : String  = usernameET.text.toString()
            val login : String = usernameET.text.toString()
            val password : String = passwordET.text.toString()
            if (login.isDigitsOnly()) {
                // user is trying to log in with uid
                url = "$url?uid=$uid"
            }else {
                url = "$url?login=$login"
            }
            // now add password
            url = "$url&password=$password"
            Log.w(MA, "" + url)

            var urlObject: URL = URL(url)

            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            result = ""
            while (scan.hasNext())
            {
                result += scan.nextLine()
                Log.w(MA, result)
            }

        } catch ( e : JSONException) {
            Log.w( MA, "Exception: " + e.message )
        }

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
        const val LOGIN_BASE_URL: String = "https://localhost/cmsc436grproj/getuser.php"
        const val MA : String =  "FinalProjectMainActivity"
    }

}