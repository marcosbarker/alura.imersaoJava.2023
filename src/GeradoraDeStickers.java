import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GeradoraDeStickers {

    public static void criaSticker(InputStream inputStream, String nomeArquivo) throws Exception {

        // BufferedImage imagemOriginal = ImageIO.read(new
        // File("/assets/imagemOriginal/filme.jng"));
        // InputStream inputStream = new URL(
        // "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_7.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        int altura = imagemOriginal.getHeight();
        int largura = imagemOriginal.getWidth();
        int novaAltura = altura + 200;
        BufferedImage imagemFinal = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics2d = (Graphics2D) imagemFinal.getGraphics();
        graphics2d.drawImage(imagemOriginal, 0, 0, null);

        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics2d.setColor(Color.YELLOW);
        graphics2d.setFont(fonte);

        String textoImg = "TESTE";
        FontMetrics fontMetrics = graphics2d.getFontMetrics();
        Rectangle2D retangulo = fontMetrics.getStringBounds(textoImg, graphics2d);

        int larguraTexto = (int) retangulo.getWidth();
        int posicaoTextoX = (largura - larguraTexto) / 2;

        graphics2d.drawString(textoImg, posicaoTextoX, novaAltura - 100);
        ImageIO.write(imagemFinal, "png", new File(nomeArquivo));
    }

    // public static void main(String[] args) throws Exception {
    // var GeradoraStickers = new GeradoraDeStickers();
    // GeradoraDeStickers.criaSticker();
    // }
}