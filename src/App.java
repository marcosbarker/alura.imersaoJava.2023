import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = " https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";

        URI endereco = URI.create(url);
        var apiImdb = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = apiImdb.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        var criaDiretorio = new File("alura_stickers/assets/imagemSaida/");
        criaDiretorio.mkdir();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "alura_stickers/assets/imagemSaida/" + titulo + ".png";

            var geradora = new GeradoraDeStickers();
            GeradoraDeStickers.criaSticker(inputStream, nomeArquivo);

            // System.out.println(filme.get("title"));
            // System.out.println(filme.get("image"));
            // System.out.println(filme.get("imDbRating"));
            // System.out.println();
        }
    }
}