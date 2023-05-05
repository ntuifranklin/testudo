package com.example.androidfinalprojectcmsc436

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterClassesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var relativeLayout : RelativeLayout
    lateinit var title : TextView
    lateinit var backButton : Button
    lateinit var addButton : Button
    lateinit var gradebook: Gradebook

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_courses)
        title = findViewById(R.id.Title)
        relativeLayout = findViewById(R.id.relLay)
        gradebook = Gradebook( )
        addCoursesGUI( )
    }

    fun addCoursesGUI() : Unit {

        var courseIdMappings : HashMap<String, Int> = HashMap<String, Int>()
        var courses = gradebook.getCourses()
        var currPosInArray : Int = 0
        for (course in courses) {
            var courseRules : HashMap<Int, Int> = HashMap<Int, Int>()
            if (currPosInArray == 0) {
                courseRules[RelativeLayout.BELOW] = title.id
            } else {
                var previousCourse : String = courses[currPosInArray-1]
                courseRules[RelativeLayout.BELOW] = courseIdMappings[previousCourse]!!
            }

            var courseParams : RelativeLayout.LayoutParams = setRelativeLayoutParams(courseRules)
            var courseOption : CheckBox = CheckBox(this)
            courseOption.text = course
            courseOption.textSize = 30f
            var courseId = View.generateViewId()
            courseIdMappings[course] = courseId
            courseOption.id = courseId
            courseParams.topMargin = 30
            courseParams.leftMargin = 30
            courseOption.layoutParams = courseParams
            relativeLayout.addView(courseOption)
            currPosInArray++
        }

        var addCoursesButton : Button = Button(this)
        addCoursesButton.text="Add Selected Course(s)"

        var buttonRules : HashMap<Int, Int> = HashMap<Int, Int>()
        buttonRules[RelativeLayout.BELOW] = courseIdMappings[courses[courses.size-1]]!!
        var params : RelativeLayout.LayoutParams = setRelativeLayoutParams(buttonRules)
        params.topMargin = 30

        addCoursesButton.layoutParams = params

        var addCoursesId = View.generateViewId()
        addCoursesButton.id = addCoursesId

        addCoursesButton.setOnClickListener(this)

        addButton = addCoursesButton

        relativeLayout.addView(addCoursesButton)

        var goBackButton : Button = Button(this)
        goBackButton.text="Go Back"

        buttonRules[RelativeLayout.BELOW] = addCoursesId
        params = setRelativeLayoutParams(buttonRules)
        params.topMargin = 30

        goBackButton.layoutParams = params

        goBackButton.id= View.generateViewId()

        goBackButton.setOnClickListener(this)

        backButton = goBackButton

        relativeLayout.addView(goBackButton)

    }

    override fun onClick(v: View?) {
        if (v != null && v == backButton) {
            goBack(backButton)
        } else if (v != null && v == addButton) {
            //To be replaced with functionality for adding courses for the given student
            goBack(addButton)
        }
    }

    fun setRelativeLayoutParams( rules : Map<Int, Int> ) : RelativeLayout.LayoutParams {
        var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        rules.forEach { verb, subject ->  params.addRule(verb, subject)}
        return params
    }

    fun goBack(v : View) : Unit {
        finish( )
    }
}