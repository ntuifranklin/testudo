package com.example.androidfinalprojectcmsc436

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableRow
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var usernameET : EditText
    lateinit var passwordET : EditText
    lateinit var loginButtonView : Button
    lateinit var signUpButtonView : Button
    lateinit var loginTask : AccessWebThreadTask
    var result: String = ""
    var loginUrl : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        usernameET = findViewById(R.id.login_name)
        passwordET = findViewById(R.id.login_password)
        loginButtonView = findViewById(R.id.login_button)
        signUpButtonView = findViewById(R.id.sign_up_button)
        loginButtonView.setOnClickListener(ButtonHandler())
        signUpButtonView.setOnClickListener(ButtonHandler())

        createAd( )
    }

    fun createAd( ) : Unit {
        var adView : AdView = AdView( this )
        var adSize : AdSize = AdSize( AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT)
        adView.setAdSize( adSize )
        var unitId : String = "ca-app-pub-3940256099942544/6300978111"
        adView.adUnitId = unitId

        var adRequestBuilder :AdRequest.Builder = AdRequest.Builder( )
        adRequestBuilder.addKeyword("School")
        adRequestBuilder.addKeyword("Student")
        adRequestBuilder.addKeyword("College")
        adRequestBuilder.addKeyword("Grades")
        var adRequest : AdRequest = adRequestBuilder.build()

        var adTableRow : TableRow = findViewById<TableRow>(R.id.ad)
        adTableRow.addView(adView)

        try {
            adView.loadAd(adRequest)
        } catch( e : Exception) {
            Log.w("MainActivity", "Error loading the ad")
        }
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


    fun showStudentDashboard(student: Student) {
        Log.w(MA, "Result from backend server" + student)
        try{

            //Toast.makeText(this,"Student Logged In successfully" + student.toString(),Toast.LENGTH_SHORT).show()
            // show a dashboard for the student
            LOGGED_IN_STUDENT = student
            var myIntent : Intent = Intent( this, StudentDashboardActivity::class.java )
            startActivity( myIntent )

            // V3
            overridePendingTransition( R.anim.slide_from_left, 0 )

        } catch ( e : Exception) {
            Log.w(MA, "Exception: " + e.message )
            //Toast.makeText(this,"Wrong User name or  passsword ",Toast.LENGTH_SHORT).show()
        }

    }

    fun goToSignUp( ) {
        var signUpIntent : Intent = Intent( this, SignUpActivity::class.java )
        startActivity( signUpIntent )
    }

    inner class ButtonHandler : View.OnClickListener {
        override fun onClick(v: View?) {
           if ( v != null && v == loginButtonView )
            checkUserLogin()
           else if (v != null && v == signUpButtonView)
               goToSignUp( )
        }

    }

    companion object {
        //const val LOGIN_BASE_URL: String = "https://s56.cmsc436-2301.cs.umd.edu/"
        const val SERVER_BASE_URL: String = "https://s56.cmsc436-2301.cs.umd.edu/server/backend.php"
        const val AWS_BASE_URL: String = "http://ec2-54-196-236-197.compute-1.amazonaws.com/cmsc436grproj//backend.php"
        const val MA : String =  "FinalProjectMainActivity"
        lateinit var LOGGED_IN_STUDENT : Student
    }

}