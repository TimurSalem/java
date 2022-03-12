import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.Scanner;

public class Gifts {

    Image img;

    boolean act;
    int w, h;

    int x = (int) (Math.random() * (1200 - w));
    int y = (int) (Math.random() * -(1200 - h));

    Timer spawn;
    int speed = 1;

    Gifts(Image img) {

        this.act = true;

        this.img = img;

        this.w = img.getWidth(null);
        this.h = img.getHeight(null);

        spawn = new Timer(2, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                drop();


            }
        });

        spawn.start();

        System.out.println(y);

    }


    void drop() {

        y += speed;


    }

}



