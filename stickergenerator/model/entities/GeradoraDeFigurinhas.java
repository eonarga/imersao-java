import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;


import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas  {

    String texto;
    
    public void cria(InputStream inputStream, String nomeArquivo, String frase) throws Exception {

        // Leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies_1.jpg").openStream();
        BufferedImage imagemOriginal =  ImageIO.read(inputStream);

        // Cria nova imgagem em memória com transparência e com tamanho novo

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 100;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copia a imagem original pra novo imagem (em memória)

        Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte 
        // Definir a fonte com tamanho relativo à altura da imagem
        float tamanhoFonte = altura * 0.1f; // 10% da altura da imagem
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, (int)tamanhoFonte);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        // Escrever uma frase na nova imgaem
        //String texto = "Imersão JAVA ✌";
        FontMetrics metrics = graphics.getFontMetrics(fonte);
        int x = (largura - metrics.stringWidth(frase)) / 2;
        int y = novaAltura - 70;
        graphics.drawString(frase, x, y);
       
        /*Criando uma nova subpasta no diretorio informado */
        var path = new File("c:alura-stickers\\stickergenerator");
        if(path != null) {
           new File( path + "//saida").mkdir();
        }
        
        // Escrever a nova imagem em um arquivo
        nomeArquivo = nomeArquivo.replace(":", "-");
        ImageIO.write(novaImagem, "png", new File( path + "/saida/" + nomeArquivo));


    }

  
}
