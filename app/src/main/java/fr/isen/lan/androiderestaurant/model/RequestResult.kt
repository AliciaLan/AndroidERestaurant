package fr.isen.lan.androiderestaurant.model

import java.io.Serializable

data class RequestResult(
    val data : List<Category>
) : Serializable