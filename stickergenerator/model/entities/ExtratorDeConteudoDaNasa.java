import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
    
   public List<Conteudo> extraiConteudo(String json) {

        // extrair dados  (titulo , poster, classificação, ano) 
        var jsonParser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = jsonParser.parse(json);

        return listaDeAtributos.stream().map(atributos -> 
            new Conteudo(atributos.get("title"), atributos.get("url"))
        )
        .toList();

    }
}
