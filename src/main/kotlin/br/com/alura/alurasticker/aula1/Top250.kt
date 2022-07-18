package br.com.alura.alurasticker.aula1

private const val BASE_URL = "https://imdb-api.com/en/API/Top250Movies/"

class Top250(
    private val client: ImdbClient = ImdbClient()
) {

    fun buscaTitulos() =
        buscaFilmesPor("title")

    fun buscaImagens() =
        buscaFilmesPor("image")

    private fun buscaFilmesPor(campo: String) =
        buscaFilmes()?.mapNotNull {
            it[campo]
        }

    fun buscaFilmes() = client
        .buscaDadosImdb(BASE_URL)


}