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
        // String url =
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        // //IMDB
        String url = "https://api.nasa.gov/planetary/apod?api_key=MMfRXCXqSuDvebU3Nh6Voa8T3WDXclToGvpxfouK&date"; // NASA

        URI endereco = URI.create(url);
        var apiImdb = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = apiImdb.send(request, BodyHandlers.ofString());
        String body = response.body();
        // System.out.println(body);

        var parser = new JsonParser();
        List<Map<String, String>> listaConteudo = parser.parse(body);
        // System.out.println(listaDeFilmes.size());
        // System.out.println(listaDeFilmes.get(0));

        var criaDiretorio = new File("alura_stickers/assets/imagemSaida/");
        criaDiretorio.mkdir();

        var geradora = new GeradoraDeStickers();

        for (Map<String, String> conteudo : listaConteudo) {

            // String urlImagem = conteudo.get("image").replaceAll("(@+)(.*).jpg$",
            // "$l.jpg");
            String urlImagem = conteudo.get("url");
            // .replaceAll("(@+)(.*).jpg$", "$l.jpg");

            String titulo = conteudo.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "alura_stickers/assets/imagemSaida/" + titulo + ".png";

            GeradoraDeStickers.criaSticker(inputStream, nomeArquivo);

            System.out.println(conteudo.get("title"));
            // System.out.println(filme.get("image"));
            // System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}