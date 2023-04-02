import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=MMfRXCXqSuDvebU3Nh6Voa8T3WDXclToGvpxfouK&date&start_day=2022-01-01&end_day=2022-03-01";
        // // NASA

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        var criaDiretorio = new File("alura_stickers/assets/imagemSaida/");
        criaDiretorio.mkdir();

        var geradora = new GeradoraDeStickers();

        for (Map<String, String> conteudo : listaConteudo) {

            String urlImagem = conteudo.get("url");

            String titulo = conteudo.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = "alura_stickers/assets/imagemSaida/" + titulo + ".png";

            GeradoraDeStickers.criaSticker(inputStream, nomeArquivo);

            System.out.println(conteudo.get("title"));

            System.out.println();
        }
    }
}