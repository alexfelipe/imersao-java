package br.com.alura.alurasticker.aula1

import kotlinx.serialization.Serializable

@Serializable
class Top250Data(
    val items: List<Filme>
)

@Serializable
data class Filme(
    val title: String,
    val image: String,
    val imDbRating: Double
)