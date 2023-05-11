package com.example.androidfinalprojectcmsc436

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast

class CustomToast {
    private lateinit var toast : Toast
    private lateinit var activity : Activity
    constructor(a: Activity, message: String) {
        activity = a

        val layout = activity.layoutInflater.inflate (
            R.layout.custom_toast,
            activity.findViewById(R.id.custom_toast_container)
        )
        layout.findViewById<TextView>(R.id.toast_message).text = message

        toast = Toast.makeText(activity.baseContext,"${message}",Toast.LENGTH_LONG)
        toast.view = layout
        toast.setGravity(0,0,0)


    }


    fun getCustomToast(): Toast {

        return toast

    }



}