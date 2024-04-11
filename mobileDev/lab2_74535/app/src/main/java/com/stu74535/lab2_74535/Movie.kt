package com.stu74535.lab2_74535

import kotlin.random.Random

class Movie (
    val name: String,
    val image: String,
    var certification : String,
    val description : String,
    val starring : Array<String>,
    val running_time_mins : Int,
    var seats_remaining : Int ,
    var seats_selected : Int = 0


){
    fun copy(
        name: String = this.name,
        image: String = this.image,
        certification: String = this.certification,
        description: String = this.description,
        starring: Array<String> = this.starring,
        running_time_mins: Int = this.running_time_mins,
        seats_remaining: Int = this.seats_remaining,
        seats_selected: Int = this.seats_selected
    ): Movie {
        return Movie(
            name = name,
            image = image,
            certification = certification,
            description = description,
            starring = starring,
            running_time_mins = running_time_mins,
            seats_remaining = seats_remaining,
            seats_selected = seats_selected
        )
    }
}