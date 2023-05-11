package com.example.androidfinalprojectcmsc436

class Assignment {
    private var title : String = ""
    private var weight : Float = 0.0f
    private var score : Float = 0.0f

    constructor(title : String, weight : Float, score : Float) {
        this.title = title
        this.weight = weight
        this.score = score
    }

    fun getTitle( ) : String {
        return title
    }

    fun getWeight( ) : Float {
        return weight
    }

    fun getScore( ) : Float {
        return score
    }

    fun setTitle(title : String) {
        this.title = title
    }

    fun setWeight(weight : Float) {
        this.weight = weight
    }

    fun setScore(score : Float) {
        this.score = score
    }

}