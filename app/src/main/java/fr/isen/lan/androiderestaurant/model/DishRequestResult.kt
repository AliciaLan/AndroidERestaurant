package fr.isen.lan.androiderestaurant.model

import java.io.Serializable

data class DishRequestResult(
    val data : List<Category>
) : Serializable