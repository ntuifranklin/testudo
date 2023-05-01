package com.example.androidfinalprojectcmsc436

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton

class StudentActivity : AppCompatActivity() {
    // V2
    lateinit var amountET : EditText
    lateinit var rateET : EditText
    lateinit var button10 : RadioButton
    lateinit var button15 : RadioButton
    lateinit var button30 : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //check if user is collegd in correctly, then build ui
        //setContentView(R.layout.activity_data)

        /*
        amountET = findViewById( R.id.data_amount )
        rateET = findViewById( R.id.data_rate )
        button10 = findViewById( R.id.ten )
        button15 = findViewById( R.id.fifteen )
        button30 = findViewById( R.id.thirty )

        // V2


         */
    }

    fun goBack( v : View) {
        // V2
        // go back
        finish( )
        overridePendingTransition( R.anim.fade_in_and_scale, 0 )
    }

    // V2
    fun buildguibycode( ) {


    }

}