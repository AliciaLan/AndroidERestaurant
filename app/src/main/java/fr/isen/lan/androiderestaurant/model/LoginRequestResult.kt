package fr.isen.lan.androiderestaurant.model

import java.io.Serializable

data class LoginRequestResult(
    val data : User,
    val code : Number
) : Serializable
