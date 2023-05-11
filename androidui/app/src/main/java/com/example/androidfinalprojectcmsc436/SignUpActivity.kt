package com.example.androidfinalprojectcmsc436

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var firstName : String
    lateinit var middleInitial : String
    lateinit var lastName : String
    lateinit var UID : String
    lateinit var DOB : String
    lateinit var username : String
    lateinit var password : String
    lateinit var signUpButton : Button
    lateinit var goBackButton : Button
    private lateinit var signup_thread : PostStudentThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        signUpButton = findViewById<Button>(R.id.sign_up_button)
        goBackButton = findViewById<Button>(R.id.go_back_button)
        signUpButton.setOnClickListener(this)
        goBackButton.setOnClickListener(this)

    }

    fun goBack( ) : Unit {
        finish()
        overridePendingTransition(R.anim.slide_from_top, 0)
    }

    fun showSuccessMessage(success : Boolean) {
        if ( !success ) {
            Toast.makeText(this, "Failure to Signup Student", Toast.LENGTH_LONG).show()
            goBack()
        } else
            Toast.makeText(this, "Successful to Signup Student", Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        if (v != null && v == goBackButton) {
            goBack()
        } else if (v != null && v == signUpButton) {

            firstName = findViewById<EditText>(R.id.firstname_ET).text.toString()
            middleInitial = findViewById<EditText>(R.id.middle_initial_ET).text.toString()
            lastName = findViewById<EditText>(R.id.lastname_ET).text.toString()
            UID = findViewById<EditText>(R.id.UID_ET).text.toString()
            DOB = findViewById<EditText>(R.id.DOB_ET).text.toString()
            username = findViewById<EditText>(R.id.username_ET).text.toString()
            password = findViewById<EditText>(R.id.password_ET).text.toString()

            var student : Student = Student(UID, username, password, DOB, firstName, middleInitial, lastName)
            signup_thread = PostStudentThread(this, student)
            signup_thread.start()

            //goBack()
        }
    }


}