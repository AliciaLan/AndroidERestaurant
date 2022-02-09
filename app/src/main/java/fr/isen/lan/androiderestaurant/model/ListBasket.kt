package fr.isen.lan.androiderestaurant.model

import java.io.Serializable

data class ListBasket(
    val data : List<DishBasket>
) : Serializable
