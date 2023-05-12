package com.example.androidfinalprojectcmsc436
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AddGradeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_grades)
    }

    override fun onClick(v: View?) {
        var add_grade_button : Button = findViewById<Button>(R.id.add_grade_button)

        if ( v == null )
            return
        /*
        *  post_course_assignment( assignmentTitle:String, weight : Double, studentuid: String, earnedgrade : Double )
        * */
        if  ( v == add_grade_button ) {
            var weight : Double = 0.0
            var studentuid : String = ""
            var earnedgrade : Double = 0.0
            var assignmentTitle : String  = ""
            weight = findViewById<TextView>(R.id.course_ET).text.toString().toDouble()
            studentuid = MainActivity.LOGGED_IN_STUDENT.getUid()
            earnedgrade = findViewById<TextView>(R.id.grade_ET).text.toString().toDouble()
            assignmentTitle = findViewById<TextView>(R.id.assignmentTitle).text.toString()
            var b : Backend = Backend()
            var success : Boolean = b.post_course_assignment(assignmentTitle, weight, studentuid, earnedgrade)
            var m : String = ""
            if ( success == true ) {
                m = "Successfully Added Assignment"
            } else {
                m = "Failed to Add Assignment"
            }
            var c : CustomToast = CustomToast(this, m)
            c.getCustomToast().show()
        }

    }






}