package br.com.alura.alurasticker.aula1

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ImdbClient {

    fun buscaDadosImdb(url: String): List<Map<String, String>>? {
        readApiKey()?.let { apiKey ->
            val client = HttpClient.newHttpClient()
            val request = HttpRequest
                .newBuilder(URI.create(url + apiKey))
                .GET()
                .build()
            val response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            )
            return JsonParser().parse(response.body())
        }
        return null
    }

    private fun readApiKey(): String? {
        var apiKey: String? = null
        File(".env").forEachLine {
            if (it.contains("API_KEY")) {
                it.replace("API_KEY=", "")
                    .replace("\"", "").run {
                        apiKey = this
                    }
            }
        }
        return apiKey;
    }

}