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


    fun showStudentDashboard(s: String) {
        Log.w(MA, "Reslt from backend server" + s)
        try{
            var d : JSONArray = JSONArray(s)

            var jsa : JSONObject = d.optJSONObject(0)
            var firstname : String = jsa.getString("FIRSTNAME")
            var lastname : String = jsa.getString("LASTNAME")

            Toast.makeText(this,"Student Logged In successfully",Toast.LENGTH_SHORT).show()
            Toast.makeText(this,"Student Logged Details :",Toast.LENGTH_SHORT).show()
            Toast.makeText(this,"First Name :" + firstname + " Last Name : "+ lastname,Toast.LENGTH_LONG).show()

        } catch ( e : Exception) {
            Log.w(MainActivity.MA, "Exception: " + e.message )
            Toast.makeText(this,"Wrong User name or  passsword ",Toast.LENGTH_SHORT).show()
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
        const val SERVER_BASE_URL: String = "https://s56.cmsc436-2301.cs.umd.edu/server/backend.php"
        const val MA : String =  "FinalProjectMainActivity"
    }

}