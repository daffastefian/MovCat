package com.daffa.movcat

import java.io.Serializable
import kotlin.collections.ArrayList

data class DataModel (
    val title : String, val overview : String, val poster : String, val genres : String, val language : String, val runtime : String, val budget : String, val revenue : String, val rating : String, val director : String, val star : ArrayList<String>, val year : String, val originalName : String, val status : String
) : Serializable

data class TheaterModel(val theater : String, val name : String,val address : String, val schedule :String) : Serializable