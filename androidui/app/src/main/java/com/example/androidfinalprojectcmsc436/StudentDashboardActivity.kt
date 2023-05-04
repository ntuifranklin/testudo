package com.example.androidfinalprojectcmsc436

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class StudentDashboardActivity : AppCompatActivity(), View.OnTouchListener {

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

    private fun init () {

        screenWidth = Resources.getSystem( ).displayMetrics.widthPixels
        screenHeight = Resources.getSystem( ).displayMetrics.heightPixels

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        /*
        amountET = findViewById( R.id.data_amount )
        rateET = findViewById( R.id.data_rate )
        button10 = findViewById( R.id.ten )
        button15 = findViewById( R.id.fifteen )
        button30 = findViewById( R.id.thirty )
         */

        screenWidth = Resources.getSystem( ).displayMetrics.widthPixels
        screenHeight = Resources.getSystem( ).displayMetrics.heightPixels

        bw = (screenWidth.toFloat()*0.7).toInt()
        bh = (screenHeight/10).toInt()
        buildGuiByCode()
    }

    fun goBack( v : View) {
        // V2
        // go back
        finish( )
        overridePendingTransition( R.anim.fade_in_and_scale, 0 )
    }

    fun buildGuiByCode( ) {

        regCourseButton = Button(this)
        viewRegisCourseButton = DButton(this)
        viewGrades = Button(this)
        goBackButton = Button(this)

        goBackButton.setOnTouchListener(this)


        regCourseButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        regCourseButton.setText(R.string.dashboard_register_course)

        viewRegisCourseButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        viewRegisCourseButton.setText(R.string.dashboard_view_registered_courses)


        viewGrades.setBackgroundColor( resources.getColor(R.color.purple_700) )
        viewGrades.setText(R.string.dashboard_view_grades)

        goBackButton.setBackgroundColor( resources.getColor(R.color.purple_700) )
        goBackButton.setText(R.string.dashboard_goback)

        var rl : RelativeLayout = RelativeLayout( this )

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

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if( event != null && v != null && v.id == goBackButton.id) {
            goBack(goBackButton)


        }
        return true
    }

    inner class DButton : AppCompatButton {
        constructor(context: Context) : super(context) {
            init()
        }

        constructor(
            context: Context,
            attrs: AttributeSet?
        ) : super(context, attrs) {
            init()
        }

        constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
        ) : super(context, attrs, defStyleAttr) {
            init()
        }

        private fun init() {

            setBackgroundColor(ContextCompat.getColor(context,R.color.purple_700))
            this.width = bw
            this.height = bh
        }
    }

}