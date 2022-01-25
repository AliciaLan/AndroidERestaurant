package fr.isen.lan.androiderestaurant

import java.io.Serializable

data class DishViewModel(val image : Int, val title : String, val description : String, val price : String) : Serializable {

}
