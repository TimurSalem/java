import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;

public class MyPanel extends JPanel implements ActionListener {

    Image bg; // обьявляем переменную типа Image: bg
    Image bg_2; // обьявляем переменную типа Image: bg_2
    Image podarok_1; // обьявляем переменную типа Image: podarok_1

    Timer timer;
    Timer presentOnfield;

    int idx = 0;

    int x;
    int y;

    Image game_over = null;

    int bg_h;
    int bg_w;

    int directon_x;

    Gifts[] gifts = new Gifts[7];

    MyPanel() {

        timer = new Timer(1, this);
        timer.start();

        presentOnfield = new Timer(1000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                presentStart();
                System.out.println(1);
            }
        });

        presentOnfield.start();

        for (int i = 0; i < gifts.length; i++) {
            String path = "All_images (extract/p" + i + ".png";
            gifts[i] = new Gifts(load_image(path));
        }

        try { // Пытаемся выполнить
            bg = load_image("All_images (extract/fon.png"); // Загружаем
            bg_2 = load_image("All_images (extract/shapka.png");
            podarok_1 = load_image("All_images (extract/p4.png");
            game_over = load_image("All_images (extract/end_game.png");

            bg_h = bg_2.getHeight(null);
            bg_w = bg_2.getWidth(null);

        } catch (Exception ignored) {
        }

    }

    Image load_image(String path) { // Создаем функцию которая возращает Image

        Image image = null;

        try {
            image = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.printf("Путь: %s - не найден", path); // Подставляет path вместо %s
        }

        return image;
    }

    void presentStart() {
        while (true) {
            Gifts gift = gifts[idx];

            if (gift.y <= -gift.h) {
                gift.x = (int) (Math.random() * (800 - gift.w));
                gift.spawn.start();
                break;
            }
            idx++;
            if (idx == gifts.length) idx = 0;
        }
    }

    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(bg, 0, 0, null); // Отрисовывает
        g.drawImage(bg_2, x, y, null);

        for (Gifts gifts : gifts) {

            g.drawImage(gifts.img, gifts.x, gifts.y, null);

            if (gifts.y + gifts.h >= y && gifts.y <= y + bg_h && gifts.x + gifts.w >= x && gifts.x <= x + bg_w) {
                gifts.y = -gifts.h;

                gifts.spawn.stop();

            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();

        if (directon_x > 0)
            if (x + bg_w < 800) {
                x += directon_x;
            }
        if (directon_x < 0)
            if (x > 0) {
                x += directon_x;
            }

    }

}

