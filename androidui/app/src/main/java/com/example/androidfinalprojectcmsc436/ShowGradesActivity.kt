package com.example.androidfinalprojectcmsc436

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ShowGradesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var goBackButton : Button
    lateinit var emailButton: Button
    lateinit var addGradeButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_grades)
        goBackButton = findViewById(R.id.GoBackGrades)
        emailButton = findViewById(R.id.EmailButton)
        addGradeButton = findViewById(R.id.AddGrade)
        goBackButton.setOnClickListener(this)
        emailButton.setOnClickListener(this)
        addGradeButton.setOnClickListener(this)
    }

    fun goBack( v : View) {
        finish( )
        overridePendingTransition(R.anim.slide_from_top, 0)
    }

    fun goToEmail( v : View) {
        var emailViewIntent : Intent = Intent( this, EmailActivity::class.java)
        startActivity( emailViewIntent )
        overridePendingTransition(R.anim.slide_from_right, 0)
    }
    fun goToGrades( v : View) {
        var GradeViewIntent : Intent = Intent( this, AddGradeActivity::class.java)
        startActivity( GradeViewIntent )
        overridePendingTransition(R.anim.slide_from_right, 0)
    }

    override fun onClick(v: View?) {
        if (v != null && v == goBackButton) {
            goBack(goBackButton)
        } else if (v != null && v == emailButton) {
            goToEmail(emailButton)
        } else if (v != null && v == addGradeButton) {
            goToGrades(addGradeButton)
        }
    }
}