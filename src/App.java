import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        var key = "your_key";
        String url = "https://imdb-api.com/en/API/Top250Movies/" + key;
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        JsonParser parser = new JsonParser();

        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
        }
    }
}
