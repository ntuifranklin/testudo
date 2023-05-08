package com.example.androidfinalprojectcmsc436

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.InputStream
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection


class Backend {
    /* Handles calls to back end */
    var loginUrl : String = MainActivity.SERVER_BASE_URL
    constructor() {
        //dummy construct
    }
    /* Returns a list of all courses in the database */
    fun get_all_courses_from_database() :ArrayList<Course> {
        var arrayListCourses : ArrayList<Course> = ArrayList<Course>()
        try {
            loginUrl = "$loginUrl?action=courses"

            Log.w(MainActivity.MA,"Getting all courses from DB through url " + loginUrl)
            var urlObject: URL = URL(loginUrl)

            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            var result: String  = ""
            while (scan.hasNext())
            {
                result += scan.nextLine()
            }
            Log.w(MainActivity.MA,"Result from $loginUrl : " + result)

            var array_of_courses : JSONArray = JSONArray(result)
            for (i in 0 until array_of_courses.length()) {

                var courseObject : JSONObject = array_of_courses.optJSONObject(i)
                var courseid : String = courseObject.getString("CID")

                var coursetitle: String = courseObject.getString("COURSETITLE")
                var semester : String = courseObject.getString("SEMESTER")
                var year : String = courseObject.getString("YEAR")
                var credit : Double = courseObject.getDouble("CREDIT")
                var course : Course =
                    Course (courseid,
                        coursetitle,
                        semester,
                        year,
                        credit
                    )
                arrayListCourses.add(course)
            }

            return arrayListCourses
        } catch(e :JSONException) {
            Log.w(MainActivity.MA, "There was an issue grabbing courses from the database")
            return ArrayList<Course>()
        }
    }

    /* Returns a list of all courses in the database */
    fun get_one_courses_from_database(courseid: String="") : Course {
        try {
            loginUrl = MainActivity.SERVER_BASE_URL

            loginUrl = "$loginUrl?action=course&courseid=$courseid"


            var urlObject: URL = URL(loginUrl)


            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            var result = ""

            while (scan.hasNext())
            {
                result += scan.nextLine()
            }

            Log.w(MainActivity.MA,"Result from $loginUrl : " + result)

            var array_of_courses : JSONArray = JSONArray(result)


            var courseObject : JSONObject = array_of_courses.optJSONObject(0)
            var courseid : String = courseObject.getString("CID")

            var coursetitle: String = courseObject.getString("COURSETITLE")
            var semester : String = courseObject.getString("SEMESTER")
            var year : String = courseObject.getString("YEAR")
            var credit : Double = courseObject.getDouble("CREDIT")
            var course : Course =
                Course (courseid,
                    coursetitle,
                    semester,
                    year,
                    credit
                )

            return course
        } catch(e :JSONException) {
            return Course()
        }
    }

    /* Returns a list of all courses in the database */
    fun get_all_students_from_database() :ArrayList<Student> {
        var arrayListStudents : ArrayList<Student> = ArrayList<Student>()



        loginUrl = "$loginUrl?action=users"

        Log.w(MainActivity.MA, "Accessing login URL : " + loginUrl)

        var urlObject: URL = URL(loginUrl)

        val iStream: InputStream = urlObject.openStream()
        // read from input stream, accumulate into result
        val scan: Scanner = Scanner(iStream)
        var result = ""

        while (scan.hasNext())
        {
            result += scan.nextLine()
        }


        Log.w(MainActivity.MA,"Result from $loginUrl : " + result)
        var d : JSONArray = JSONArray(result)
        for (i in 0..d.length()) {

            var jsa : JSONObject = d.optJSONObject(i)
            var uid : String = jsa.getString("UID")
            var username = jsa.getString("USERNAME")
            var password = jsa.getString("PASSWORD")
            var dob : String = jsa.getString("DOB")
            var firstname : String = jsa.getString("FIRSTNAME")
            var middlename : String = jsa.getString("MIDDLENAME")
            var lastname : String = jsa.getString("LASTNAME")
            var student =
                Student(uid,
                    username,
                    password,
                    dob,
                    firstname,
                    middlename,
                    lastname
                )
            arrayListStudents.add(student)

        }


        return arrayListStudents
    }


    fun get_one_student_from_database(username : String = "", password : String = "") :Student {


        try {


            loginUrl = "$loginUrl?action=user&username=$username"
            // now add password
            loginUrl = "$loginUrl&password=$password"
            Log.w(MainActivity.MA, "Accessing login URL : " + loginUrl)

            var urlObject: URL = URL(loginUrl)

            val iStream: InputStream = urlObject.openStream()
            // read from input stream, accumulate into result
            val scan: Scanner = Scanner(iStream)
            var result = ""
            while (scan.hasNext())
            {
                result += scan.nextLine()
            }

            Log.w(MainActivity.MA,"Result from $loginUrl : " + result)
            var d : JSONArray = JSONArray(result)

            var jsa : JSONObject = d.optJSONObject(0)
            var uid : String = jsa.getString("UID")
            var username = jsa.getString("USERNAME")
            var password = jsa.getString("PASSWORD")
            var dob : String = jsa.getString("DOB")
            var firstname : String = jsa.getString("FIRSTNAME")
            var middlename : String = jsa.getString("MIDDLENAME")
            var lastname : String = jsa.getString("LASTNAME")
            var student =
                Student(uid,
                    username,
                    password,
                    dob,
                    firstname,
                    middlename,
                    lastname
                )

            return student

        } catch(e : JSONException) {
            //returns an empty student
            return Student()
        }
    }
}