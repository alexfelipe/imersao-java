package br.com.alura.alurasticker

import br.com.alura.alurasticker.aula1.Filme
import br.com.alura.alurasticker.aula1.Top250Data
import br.com.alura.alurasticker.aula1.WebClient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.math.roundToInt

private val JsonInstance = Json {
    ignoreUnknownKeys = true
}

fun main() {
//    println(MaisPopulares().buscaTitulos())
//    println(Top250().buscaTitulos())
//    Top250().buscaFilmes()?.forEach {
//        println("${it["title"]} - ${it["year"]}")
//        val nota = it["imDbRating"]?.toDouble().formataParaEstrela() ?: 0.0
//        println(formatador.formataParaEstrela(nota))
//    }
    val json = WebClient()
        .requisicao("https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json")
    JsonInstance.decodeFromString<Top250Data>(json)
        .items.forEach {
            mostraFilmeFormatado(it)
        }
}

private fun mostraFilmeFormatado(
    filme: Filme,
) {
    println("\u001B[35mTítulo: ${filme.title}\u001B[0m")
    println("Nota: \u001B[33m${filme.imDbRating.formataParaEstrela()}\u001B[0m (${filme.imDbRating})")
    println("\u001B[32mPoster: ${filme.image}\u001B[0m")
}

fun Double.formataParaEstrela(): String {
    val notaPermitida = if (this >= 10.0) {
        10
    } else this.roundToInt()
    val sb = StringBuilder()
    repeat(notaPermitida) {
        sb.append("\u2B50")
    }
    return sb.toString()
}
