import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url =
        // "https://api.nasa.gov/planetary/apod?api_key=MMfRXCXqSuDvebU3Nh6Voa8T3WDXclToGvpxfouK&date&start_day=2022-01-01&end_day=2022-03-01";
        // // NASA

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD-JamesWebbSpaceTelescope.json";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var criaDiretorio = new File("alura_stickers/assets/imagemSaida/");
        criaDiretorio.mkdir();

        var geradora = new GeradoraDeStickers();

        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "alura_stickers/assets/imagemSaida/" + conteudo.getTitulo() + ".png";

            GeradoraDeStickers.criaSticker(inputStream, nomeArquivo);
            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}