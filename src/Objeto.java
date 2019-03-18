import java.awt.*;

public class Objeto {
    public int x;
    public int y;
    public int largura;
    public int altura;
    public int velocidade;
    public Objeto() {
    }
    public Objeto(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }
    public void drawObjeto(Graphics g, Color cor) {
        g.setColor(cor);
        g.fillRect(x, y, largura, altura);
    }
}
