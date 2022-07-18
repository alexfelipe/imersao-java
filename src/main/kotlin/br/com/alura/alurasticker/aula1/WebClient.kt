package br.com.alura.alurasticker.aula1

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class WebClient {

    fun requisicao(url: String) : String {
        val client = HttpClient.newHttpClient()
        val request = HttpRequest
            .newBuilder(URI.create(url))
            .GET()
            .build()
        val response = client.send(
            request,
            HttpResponse.BodyHandlers.ofString()
        )
        return response.body()
    }

}