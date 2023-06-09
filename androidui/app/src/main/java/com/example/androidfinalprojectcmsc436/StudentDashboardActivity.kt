package com.example.androidfinalprojectcmsc436

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class StudentDashboardActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var regCourseButton : Button
    lateinit var paramRegC : RelativeLayout.LayoutParams
    lateinit var viewRegisCourseButton : Button
    lateinit var paramViewReg : RelativeLayout.LayoutParams
    lateinit var viewGrades : Button
    lateinit var paramViewGr : RelativeLayout.LayoutParams
    lateinit var goBackButton : Button
    lateinit var paramGoBackButton : RelativeLayout.LayoutParams
    private var bw: Int = 0
    private var bh : Int = 0
    private var screenHeight : Int = 0
    private var screenWidth : Int = 0
    private lateinit var identityTV : TextView
    private lateinit var getCoursesThreadTask : GetCoursesListThread
    private lateinit var viewRegisteredCoursesTask : ViewStudentRegisteredCoursesThread
    private lateinit var viewCourseBackButton: Button
    private lateinit var rl : RelativeLayout

    private fun init () {

        screenWidth = Resources.getSystem( ).displayMetrics.widthPixels
        screenHeight = Resources.getSystem( ).displayMetrics.heightPixels

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        screenWidth = Resources.getSystem( ).displayMetrics.widthPixels
        screenHeight = Resources.getSystem( ).displayMetrics.heightPixels

        bw = (screenWidth.toFloat()*0.7).toInt()
        bh = (screenHeight/10).toInt()
        buildStudentDashboardMenuByCode()

    }

    fun goToRegisterClasses() {
        var regClassesIntent : Intent = Intent( this, RegisterClassesActivity::class.java )
        startActivity( regClassesIntent )
        overridePendingTransition( R.anim.slide_from_right, 0 )
    }

    fun goToRegisteredClasses() {
        var sc : ArrayList<String> = LOGGED_IN_STUDENT.getRegisteredCourses()
        if ( sc != null && sc.size > 0) {
            var viewCoursesIntent : Intent = Intent( this, ViewRegisteredCoursesActivity::class.java)
            startActivity( viewCoursesIntent )
            overridePendingTransition( R.anim.slide_from_right, 0 )
        } else {
            var t : Toast = Toast.makeText(this, "You do not have any registered courses yet. Please Register for a course", Toast.LENGTH_LONG)
            t.setGravity(Gravity.CENTER, (screenWidth/4).toInt(), (screenHeight/4).toInt())
            t.show()
            return
        }

    }

    fun goToViewGrades( v : View) {
        var viewGradesIntent : Intent = Intent( this, ShowGradesActivity::class.java)
        startActivity( viewGradesIntent )
        overridePendingTransition( R.anim.slide_from_right, 0 )
    }

    fun goBack( v : View) {
        finish( )
        overridePendingTransition( R.anim.slide_from_top, 0 )
    }

    fun buildStudentDashboardMenuByCode( ) {

        regCourseButton = Button(this)
        viewRegisCourseButton = Button(this)
        viewGrades = Button(this)
        goBackButton = Button(this)

        regCourseButton.setOnClickListener(this)
        goBackButton.setOnClickListener(this)
        viewGrades.setOnClickListener(this)

        regCourseButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        regCourseButton.setText(R.string.dashboard_register_course)
        goBackButton.setTextColor(resources.getColor(R.color.white))

        viewRegisCourseButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        viewRegisCourseButton.setText(R.string.dashboard_view_registered_courses)
        goBackButton.setTextColor(resources.getColor(R.color.white))
        viewRegisCourseButton.setOnClickListener(this)


        viewGrades.setBackgroundColor( resources.getColor(R.color.purple_700) )
        viewGrades.setText(R.string.dashboard_view_grades)
        goBackButton.setTextColor(resources.getColor(R.color.white))

        goBackButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        goBackButton.setText(R.string.dashboard_goback)
        goBackButton.setTextColor(resources.getColor(R.color.white))

        rl = RelativeLayout( this )

        var startTop : Int = (screenHeight/11).toInt()
        var verticalGap : Int = (screenHeight/7).toInt()
        var leftMargin : Int = (screenWidth/7).toInt()

        // student's first name
        identityTV = TextView(this)
        var identityString : String = ""
        identityString += "Logged In User "
        identityString += "\nUID: " + MainActivity.LOGGED_IN_STUDENT.getUid()
        identityString += "\nUsername: " + MainActivity.LOGGED_IN_STUDENT.getUsername()
        identityString += "\nFull Name: " + MainActivity.LOGGED_IN_STUDENT.getFirstName() +
                " " + MainActivity.LOGGED_IN_STUDENT.getLastName()
        identityTV.setText(identityString)
        var identityParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams( bw, (bh.toFloat()*1.6).toInt())
        identityParams.leftMargin = leftMargin
        identityParams.topMargin = 2
        rl.addView( identityTV, identityParams )

        paramRegC = RelativeLayout.LayoutParams( bw, bh)
        paramRegC.leftMargin = leftMargin
        paramRegC.topMargin = startTop + verticalGap
        rl.addView( regCourseButton, paramRegC )


        paramViewReg = RelativeLayout.LayoutParams( bw, bh)
        paramViewReg.leftMargin = leftMargin
        paramViewReg.topMargin = startTop + verticalGap*2
        rl.addView( viewRegisCourseButton, paramViewReg )


        paramViewGr = RelativeLayout.LayoutParams( bw, bh)
        paramViewGr.leftMargin = leftMargin
        paramViewGr.topMargin = startTop + verticalGap*3
        rl.addView( viewGrades, paramViewGr)

        paramGoBackButton = RelativeLayout.LayoutParams( bw, bh)
        paramGoBackButton.leftMargin = leftMargin
        paramGoBackButton.topMargin = startTop + verticalGap*4
        rl.addView( goBackButton, paramGoBackButton )

        setContentView( rl )

    }

    override fun onClick(v: View?) {

        if( v != null && v == goBackButton) {
            goBack(goBackButton)
        } else if (v != null && v == regCourseButton) {

            //goToRegisterClasses(regCourseButton)
            //start thread for getting courses from database
            // then create ui for listing those courses
            getCoursesThreadTask = GetCoursesListThread(this)
            getCoursesThreadTask.start()

        } else if (v != null && v == viewGrades) {
            goToViewGrades(viewGrades)

        } else if (v != null && v == viewRegisCourseButton) {
            viewRegisteredCoursesTask = ViewStudentRegisteredCoursesThread(this, MainActivity.LOGGED_IN_STUDENT.getUid())
            viewRegisteredCoursesTask.start()

        } else if ( v!= null && v == viewCourseBackButton) {
            if ( rl != null )
                setContentView(rl)
        }
    }


    companion object {
        //const val LOGIN_BASE_URL: String = "https://s56.cmsc436-2301.cs.umd.edu/"
        const val SERVER_BASE_URL: String = MainActivity.SERVER_BASE_URL
        const val AWS_BASE_URL: String = MainActivity.AWS_BASE_URL
        const val MA : String =  "FinalProjectMainActivity"
        lateinit var ALL_COURSES : ArrayList<Course>
        lateinit var ALL_COURSE_CODES : Array<String>
        lateinit var LOGGED_IN_STUDENT : Student

    }


}