package com.example.androidfinalprojectcmsc436

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import javax.security.auth.Subject

class EmailActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var sendEmailButton : Button
    lateinit var goBackButton: Button
    lateinit var emailAddressET : EditText
    lateinit var emailSubjectET : EditText
    lateinit var emailMessageET : EditText
    lateinit var gradebook: Gradebook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.email)
        sendEmailButton = findViewById(R.id.sendEmailButton)
        goBackButton = findViewById(R.id.goBack)

        sendEmailButton.setOnClickListener(this)
        goBackButton.setOnClickListener(this)

        emailAddressET = findViewById(R.id.emailAddress)
        emailSubjectET = findViewById(R.id.emailSubject)
        emailMessageET = findViewById(R.id.emailMessage)
    }

    override fun onClick(v: View?) {
        if (v != null && v == sendEmailButton) {

            var emailAddress : String = emailAddressET.text.toString()
            var emailSubject : String = emailSubjectET.text.toString()
            var emailMessage : String = emailMessageET.text.toString()

            var emailIntent : Intent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress)
            emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessage)

            ContextCompat.startActivity(
                this,
                Intent.createChooser(emailIntent, "Email your professor"),
                null
            )
        } else if (v != null && v == goBackButton) {
            finish()
            overridePendingTransition(R.anim.slide_from_top, 0)
        }
    }
}