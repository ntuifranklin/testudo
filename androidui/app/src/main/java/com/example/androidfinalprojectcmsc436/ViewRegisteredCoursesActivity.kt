package com.example.androidfinalprojectcmsc436

import android.content.res.Resources
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewRegisteredCoursesActivity : AppCompatActivity(), View.OnClickListener {

    private var registeredCourses : ArrayList<String> = MainActivity.LOGGED_IN_STUDENT.getRegisteredCourses()
    lateinit var rl : RelativeLayout
    lateinit var backButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_registered_courses)

        rl = findViewById(R.id.relLay)

        viewRegisteredregisteredCoursesGUI( )
    }

    fun viewRegisteredregisteredCoursesGUI( ) {
        if (registeredCourses == null || registeredCourses.size == 0) {
            Toast.makeText(this, "You do not have any registered courses yet. Please Register for a course", Toast.LENGTH_LONG).show()
            //goBack()
            return
        }

        var registeredCourseIdMappings : HashMap<String, Int> = HashMap<String, Int>()

        var title : TextView = TextView(this)
        title.text = ("List Of registeredCourses Registered By " + MainActivity.LOGGED_IN_STUDENT.getUid())
        var titleParams : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        title.gravity = Gravity.CENTER
        titleParams.topMargin = 20
        title.id = View.generateViewId()
        rl.addView(title, titleParams)

        var currPosInArray : Int = 0

        for (registeredCourse in registeredCourses) {
            var courseRules : HashMap<Int, Int> = HashMap<Int, Int>()
            if (currPosInArray == 0) {
                courseRules[RelativeLayout.BELOW] = title.id
            } else {
                var previousregisteredCourse : String = registeredCourses[currPosInArray-1]
                courseRules[RelativeLayout.BELOW] = registeredCourseIdMappings[previousregisteredCourse]!!
            }

            var courseParams : RelativeLayout.LayoutParams = setRelativeLayoutParams(courseRules)
            var course : TextView = TextView(this)

            course.text = registeredCourse
            course.textSize = 30f
            var courseId = View.generateViewId()
            registeredCourseIdMappings[registeredCourse] = courseId
            course.id = courseId
            course.gravity = Gravity.CENTER
            course.gravity = Gravity.CENTER
            course.setTypeface(null, Typeface.BOLD)
            courseParams.topMargin = 50
            course.layoutParams = courseParams
            rl.addView(course)
            currPosInArray++
        }

        var buttonRules : HashMap<Int, Int> = HashMap<Int, Int>()
        buttonRules[RelativeLayout.BELOW] = registeredCourseIdMappings[registeredCourses[registeredCourses.size-1]]!!
        var params : RelativeLayout.LayoutParams = setRelativeLayoutParams(buttonRules)
        params.topMargin = 30

        var goBackButton : Button = Button(this)
        goBackButton.text="Go Back"

        params = setRelativeLayoutParams(buttonRules)
        params.topMargin = 75

        goBackButton.layoutParams = params

        goBackButton.setBackgroundColor( resources.getColor(R.color.purple_700))
        goBackButton.setTextColor(resources.getColor(R.color.white))

        goBackButton.id= View.generateViewId()

        goBackButton.setOnClickListener(this)

        backButton = goBackButton

        rl.addView(goBackButton)

    }

    fun setRelativeLayoutParams( rules : Map<Int, Int> ) : RelativeLayout.LayoutParams {
        var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        rules.forEach { verb, subject ->  params.addRule(verb, subject)}
        return params
    }

    fun goBack( ) {
        finish( )
        overridePendingTransition(R.anim.slide_from_top, 0)
    }

    override fun onClick(v: View?) {
        if (v != null && v == backButton) {
            goBack()
        }
    }

}