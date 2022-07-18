package br.com.alura.alurasticker.aula1

import java.util.regex.Pattern

class JsonParser {
    fun parse(json: String): List<Map<String, String>> {
        val matcher = REGEX_ITEMS.matcher(json)
        require(matcher.find()) { "Não encontrou items." }
        val items = matcher.group(1).split("},\\{".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val dados: MutableList<Map<String, String>> = ArrayList()
        for (item in items) {
            val atributosItem: MutableMap<String, String> = HashMap()
            val matcherAtributosJson = REGEX_ATRIBUTOS_JSON.matcher(item)
            while (matcherAtributosJson.find()) {
                val atributo = matcherAtributosJson.group(1)
                val valor = matcherAtributosJson.group(2)
                atributosItem[atributo] = valor
            }
            dados.add(atributosItem)
        }
        return dados
    }

    companion object {
        private val REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*")
        private val REGEX_ATRIBUTOS_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"")
    }
}