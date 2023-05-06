package com.example.androidfinalprojectcmsc436

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import javax.security.auth.Subject

class EmailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sendEmailButton : Button
    lateinit var emailAddressET : EditText
    lateinit var emailSubjectET : EditText
    lateinit var emailMessageET : EditText
    lateinit var gradebook: Gradebook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email)
        sendEmailButton = findViewById(R.id.sendEmailButton)

        sendEmailButton.setOnClickListener(this)

        emailAddressET = findViewById(R.id.emailAddress)
        emailSubjectET = findViewById(R.id.emailSubject)
        emailMessageET = findViewById(R.id.emailMessage)
        gradebook = Gradebook()
    }

    override fun onClick(v: View?) {
        if (v != null && v == sendEmailButton) {

            var emailAddress : String = emailAddressET.text.toString()
            var emailSubject : String = emailSubjectET.text.toString()
            var emailMessage : String = emailMessageET.text.toString()

            gradebook.sendEmail(this, emailSubject, emailAddress, emailMessage)
        }
    }
}