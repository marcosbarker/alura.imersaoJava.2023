import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoImdb {

    public List<Conteudo> extraiConteudos(String json) {

        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String, String> atributos : listaDeAtributos) {
            Conteudo conteudo = new Conteudo(atributos.get("title"),
                    atributos.get("image").replaceAll("(@+)(.*).jpg$", "$l.jpg"));
            conteudos.add(conteudo);
        }
        return conteudos;
    }
}