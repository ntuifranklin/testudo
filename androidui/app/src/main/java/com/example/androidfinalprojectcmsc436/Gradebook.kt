package com.example.androidfinalprojectcmsc436

class Gradebook {

    private val classes = arrayOf("CMSC131", "CMSC132", "CMSC216", "CMSC250", "CMSC330", "CMSC351", "CMSC412", "CMSC420", "CMSC430", "CMSC433", "CMSC436", "CMSC451", "CMSC456")

    constructor( ) {

    }

    fun getCourses( ) : Array<String> {
        var courses = getAllCourseObjects()
        var dbclasses : Array<String> = courses.toArray() as Array<String>

        return dbclasses
    }

    fun getAllCourseObjects() : ArrayList<Course> {
        var courses : ArrayList<Course> = Backend().get_all_courses_from_database()
        return Backend().get_all_courses_from_database()
    }

    fun setCourses( ) : Unit {

    }


}