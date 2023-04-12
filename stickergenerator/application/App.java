

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        API api = API.IMDB_TOP_MostPopularTVs;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();
        
        var http =  new ClienteHttp();
        String json = http.buscaDados(url);
        
        // exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudo(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < conteudos.size() ; i++) {


            Conteudo conteudo = conteudos.get(i);
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();

            String nomeArquivo = conteudo.titulo() + ".png";
            System.out.print("Informe a frase do sticker: ");
            String frase = sc.nextLine();
            geradora.cria(inputStream, nomeArquivo, frase);

            System.out.println(conteudo.getTitulo());
            System.out.println(); 
        }
        sc.close();
    }
}

