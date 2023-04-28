package com.example.androidfinalprojectcmsc436

class Student {
    private var uid : String = ""
    private  var username :  String = ""
    private var password : String = ""
    private var dob :  String = ""
    private var firstname : String = ""
    private var middlename : String = ""
    private var lastname : String = ""

    constructor(uid: String,
                username: String,
                password: String,
                dob: String,
                firstname: String,
                middlename: String,
                lastname: String
        ) {
        this.uid = uid
        this.username = username
        this.password = password
        this.dob = dob
        this.firstname = firstname
        this.middlename = middlename
        this.lastname = lastname
    }

    fun getUid() : String {
        return this.uid
    }

    fun getUsername() : String {
        return this.username
    }

    fun getDob() : String {
        return this.dob
    }

    fun getFirstName() : String {
        return this.firstname
    }

    fun getMiddleName() : String {
        return this.middlename
    }

    fun getLastName() : String {
        return this.lastname
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun setUsername(username : String) {
        this.username = username
    }

    fun setDob(dob : String) {
        this.dob = dob
    }

    fun setFirstName(firstname: String)  {
        this.firstname =  firstname
    }

    fun setMiddleName(middlename: String)  {
        this.middlename = middlename
    }

    fun setLastName(lastname: String) {
        this.lastname = lastname
    }

}