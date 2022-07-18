package br.com.alura.alurasticker.aula1

private const val BASE_URL = "https://imdb-api.com/en/API/MostPopularMovies/"

class MaisPopulares(private val client: ImdbClient = ImdbClient()) {

    fun buscaTitulos() = buscaFilmesPor("title")

    fun buscaNota() = buscaFilmesPor("imDbRating")

    private fun buscaFilmesPor(campo: String) =
        buscaFilmes()?.mapNotNull {
            it[campo]
        }

    fun buscaFilmes() = client.buscaDadosImdb(BASE_URL)

}