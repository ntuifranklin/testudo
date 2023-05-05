package com.example.androidfinalprojectcmsc436

class Gradebook {

    private val classes = arrayOf("CMSC131", "CMSC132", "CMSC216", "CMSC250", "CMSC330", "CMSC351", "CMSC412", "CMSC420", "CMSC430", "CMSC433", "CMSC436", "CMSC451", "CMSC456")

    constructor( ) {

    }

    fun getCourses( ) : Array<String> {
        return classes
    }

    fun setCourses( ) : Unit {

    }


}