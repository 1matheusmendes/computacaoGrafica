import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Interface extends JFrame {
    JPanel screen;
    boolean[] isControler = new boolean[4];
    boolean isStarting = true;
    int FPS = 1000 / 50;
    Objeto player;
    Color corPlayer = new Color(210, 140, 20);
    ArrayList<Objeto> grupo_shot;
    public Interface() {
        super.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                setKey(e.getKeyCode(), true);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                setKey(e.getKeyCode(), false);
            }
        });
        player = new Objeto(0, 0, 40, 40);
        player.velocidade = 10;
        grupo_shot = new ArrayList<>();
        screen = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                player.drawObjeto(g, corPlayer);
                g.drawString("COOR: " + player.x + " x " +
                        player.y, 10, 100);
                for (Objeto s : grupo_shot) {
                    s.velocidade = 5;
                    s.drawObjeto(g, corPlayer);
                }
            }
        };
        super.getContentPane().add(screen);
        super.setVisible(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(500, 500);
        player.y = 440;
        player.x = screen.getWidth() / 2 + player.largura /
                2;
    }
    public void controler() {
        if (isControler[0]) {
            player.x -= player.velocidade;
        }
        if (isControler[1]) {
            player.x += player.velocidade;
        }
        if (isControler[2]) {
            Objeto new_shot = new Objeto(player.x +
                    player.largura / 2, player.y, 5, 5);
            grupo_shot.add(new_shot);
        }
    }
    public void setKey(int key, boolean isPress) {
        switch (key) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_LEFT:
                isControler[0] = isPress;
                break;
            case KeyEvent.VK_RIGHT:
                isControler[1] = isPress;
                break;
            case KeyEvent.VK_SPACE:
                isControler[2] = isPress;
                break;
        }
    }
    public void start() {
        long prx = 0;
        while (isStarting) {
            if (System.currentTimeMillis() >= prx) {
                screen.repaint();
                update();
                prx = System.currentTimeMillis() + FPS;
            }
        }
    }
    public void update() {
        controler();
        System.out.println(grupo_shot.size());
        for (Objeto s : grupo_shot) {
            s.y -= s.velocidade;
        }
    }
}