package fr.isen.lan.androiderestaurant.model

import java.io.Serializable

data class DishModel(val image : Int, val title : String, val description : String, val price : String) : Serializable {

}
