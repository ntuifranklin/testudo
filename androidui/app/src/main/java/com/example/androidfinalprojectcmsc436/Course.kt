package com.example.androidfinalprojectcmsc436

class Course {
    /*
    *  { "CID": "CMSC131",
    * "COURSETITLE": "Introduction To Object Oriented Programming I",
    *  "SEMESTER": "SPRING",
    * "YEAR": "2023-01-01",
    * "CREDIT": 4 }
    * */
    var courseid : String = ""
    var coursetitle: String = ""
    var semester: String = ""
    var year: String = ""
    var credit: Double = 0.0
    constructor(courseid: String,
                coursetitle: String,
                semester: String,
                year: String,
                credit: Double
    ) {
        this.courseid = courseid
        this.coursetitle = coursetitle
        this.semester = semester
        this.year = year
        this.credit = credit
    }

    constructor() : this("","","","",0.0) {

    }


}