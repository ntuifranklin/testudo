package com.example.androidfinalprojectcmsc436

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RegisterClassesActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var relativeLayout : RelativeLayout
    lateinit var title : TextView
    lateinit var backButton : Button
    lateinit var addButton : Button
    lateinit var gradebook: Gradebook
    private lateinit var courses: Array<String>
    private lateinit var courseOptions : ArrayList<CheckBox>
    lateinit var registerCourseThread: PostRegisterCourseThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_courses)
        title = findViewById(R.id.Title)
        relativeLayout = findViewById(R.id.relLay)

        courses = StudentDashboardActivity.ALL_COURSE_CODES
        addCoursesGUI( )
    }

    fun showCourseRegistersuccess(s:String) {
        /*
        * ["{'courseid':'CMSC430','status':'success'}"]
        * */
        Log.w(MainActivity.MA, "Result From Registeration : " +s)

        Toast.makeText(this,s,Toast.LENGTH_SHORT)
        goBack(backButton)

    }

    fun addCoursesGUI() : Unit {

        if (courses == null )
            return

        var courseIdMappings : HashMap<String, Int> = HashMap<String, Int>()

        var currPosInArray : Int = 0
        //initialize course options array list
        courseOptions = ArrayList<CheckBox>()
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
            courseOptions.add(courseOption)
        }
        addButton  = Button(this)
        addButton.text="Add Selected Course(s)"

        var buttonRules : HashMap<Int, Int> = HashMap<Int, Int>()
        buttonRules[RelativeLayout.BELOW] = courseIdMappings[courses[courses.size-1]]!!
        var params : RelativeLayout.LayoutParams = setRelativeLayoutParams(buttonRules)
        params.topMargin = 30

        addButton.layoutParams = params


        addButton.id = View.generateViewId()

        addButton.setOnClickListener(this)



        relativeLayout.addView(addButton)

        var goBackButton : Button = Button(this)
        goBackButton.text="Go Back"

        buttonRules[RelativeLayout.BELOW] = addButton.id
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
            if (courseOptions != null ) {
                var coursesR: ArrayList<String> = ArrayList<String>()
                for ( courseOption in courseOptions) {
                    if (courseOption.isChecked) {
                        Log.w(MainActivity.MA,"Checked Course ID value : " + courseOption.text)
                        coursesR.add(courseOption.text.toString())
                    }
                }

                //need student id and all the course ids
                var studentId : String = StudentDashboardActivity.LOGGED_IN_STUDENT.getUid()
                registerCourseThread = PostRegisterCourseThread(this, StudentDashboardActivity.LOGGED_IN_STUDENT, coursesR)
                registerCourseThread.start()
            } else {
                Log.w(MainActivity.MA, "All checkboxes null for now")
            }
        }
    }

    fun setRelativeLayoutParams( rules : Map<Int, Int> ) : RelativeLayout.LayoutParams {
        var params : RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        rules.forEach { verb, subject ->  params.addRule(verb, subject)}
        return params
    }

    fun goBack(v : View) : Unit {
        finish( )
        overridePendingTransition( R.anim.fade_in_and_scale, 0 )
    }

    companion object {
        lateinit var registerErrorMessage : String
    }
}